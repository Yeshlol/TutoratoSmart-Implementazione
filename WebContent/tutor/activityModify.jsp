<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% ActivityTutorBean activity = (ActivityTutorBean) session.getAttribute("activity"); %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Utils.Utils,project.Model.ActivityTutorBean,project.Model.TutorDAO,project.Model.TutorBean,project.Model.RegisterDAO,project.Model.RegisterBean" %>

<head>
	<meta charset="UTF-8">
	<title>Modifica attivit&aacute lavorativa</title>
	<style>
	    table,th,td{
	    	border: 1px solid black;
	    	border-collapse: collapse;
	    	text-align:center;
	    }
	    th {
	    	background-color:#4d94ff;
	     	color: white;
	    }
	    th,td {
	    	padding:8px;
	    }
	    .column {
		  float: left;
		  width: 50%;
		}
	</style>
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	
	<div class="content text-center" style="width: 50%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2>Modifica attivit&aacute lavorativa</h2>
			<p>Compila tutti i campi relativi all'attivit&aacute svolta</p>
		</div>
		
		<div class="container-fluid" style="margin: 25px;">
			<div class="row row-space">
				<div class="row">
					<div class="alert alert-warning" id="errorDiv" role="alert" style="display:none;"></div>
				</div>
			
				<div class="row">
					<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
			  			<label for="categoryM" class="control-label">Specificare la categoria</label>
						<select id="categoryM" name="categoryM">
							<option value="Sportello Tutorato">Sportello Tutorato</option>
							<option value="Assistenza Esame">Assistenza Esame</option>
							<option value="Organizzazione Seminario">Organizzazione Seminario</option>
							<option value="Seminario">Seminario</option>
							<option value="Organizzazione Evento">Organizzazione Evento</option>
							<option value="Evento">Evento</option>
						</select>
					</div>
					<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
			  			<label for="dateM" class="control-label">Specificare la data</label>
			  			<input type="date" class="form-control" name="dateM" id="dateM" value="<%= activity.getActivityDate() %>">
			  		</div>
			  	</div>
			  	
			  	<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
			  		<label for="startTimem" class="control-label">Specificare l'orario di inizio attivit&aacute</label>
			  		<input type="time" id="startTimeM" class="form-control" min="07:30" max="22:00" step="600" value="<%= Utils.getTimeAsString(activity.getStartTime()) %>">
				</div>
			  	
			  	<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
			  		<label for="finishTimem" class="control-label">Specificare l'orario di fine attivit&aacute</label>
			  		<input type="time" id="finishTimeM" class="form-control" min="07:30" max="22:00" step="600" value="<%= Utils.getTimeAsString(activity.getFinishTime()) %>">
				</div>
			</div>	
								
	  		<label for="description" class="control-label">Inserire la descrizione dell'attivit&aacute</label>
	  		<textarea class="form-control" id="description" name="description" rows="3"><%= activity.getDetails() %></textarea>
	  		<br>
			
			<div class="panel" id="appointmentsPanel" style="display: none;"></div>
			<div id="appointments"></div>
			
						
			<div class="panel"></div>
			<div class="row text-center" style="margin-bottom: 25px;">
				<div class="column">
					<input class="btn btn-success" id="modifyActivity" type="button" onclick="modifyActivity()" value="Modifica">		        	
	        	</div>
	 			<div class="column">
					<input class="btn btn-primary" id="back" type="button" value="Indietro" onClick="location.href='tutor/register.jsp';">
				</div>
	 		</div>
			
	        
	        <div class="alert alert-success" id="successDiv" role="alert" style="display:none;margin-top: 25px;">Attivit&aacute modificata con successo!</div>
									
			<div class="alert alert-danger" id="failureDiv" role="alert" style="display:none;margin-top: 25px;">Modifica attivit&aacute fallita!</div>
		</div>
	</div>
     
    <input type="hidden" id="idActivity" value="<%= activity.getIdActivity() %>">
     
   	<%@ include file="/partials/footer.jsp" %>
	
	<script src="<%= request.getContextPath() %>/js/tutorScript.js"></script>
	<script>
		$(document).ready( function() {
			$("#modifyActivity").click();
		});
	</script>
</body>
</html>