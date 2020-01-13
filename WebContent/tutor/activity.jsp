<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.TutorDAO,project.Model.TutorBean,project.Model.RegisterDAO,project.Model.RegisterBean" %>

<head>
	<meta charset="UTF-8">
	<title>Aggiunta attivit&aacute lavorativa</title>
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
    </style>
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	
	<div class="content text-center" style="width: 50%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2>Aggiunta attivit&aacute lavorativa</h2>
			<p>Compila tutti i campi relativi all'attivit&aacute svolta</p>
		</div>
		
		<div class="container-fluid" style="margin: 25px;">
			<div class="row row-space">
				<div class="row">
					<div class="alert alert-warning" id="errorDiv" role="alert" style="display:none;"></div>
				</div>
			
				<div class="row">
					<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
			  			<label for="category" class="control-label">Specificare la categoria</label>
						<select id="category" name="category">
							<option selected disabled="disabled">--</option>
							<option value="Sportello Tutorato">Sportello Tutorato</option>
							<option value="Assistenza Esame">Assistenza Esame</option>
							<option value="Organizzazione Seminario">Organizzazione Seminario</option>
							<option value="Seminario">Seminario</option>
							<option value="Organizzazione Evento">Organizzazione Evento</option>
							<option value="Evento">Evento</option>
						</select>
					</div>
					<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
			  			<label for="date" class="control-label">Specificare la data</label>
			  			<input type="date" class="form-control" name="date" id="date">
			  		</div>
			  	</div>
			  	
			  	<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
			  		<label for="startTime" class="control-label">Specificare l'orario di inizio attivit&aacute</label>
			  		<input type="time" id="startTime" class="form-control" min="07:30" max="22:00" step="600">
				</div>
			  	
			  	<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
			  		<label for="finishTime" class="control-label">Specificare l'orario di fine attivit&aacute</label>
			  		<input type="time" id="finishTime" class="form-control" min="07:30" max="22:00" step="600">
				</div>
			</div>	
								
	  		<label for="description" class="control-label">Inserire la descrizione dell'attivit&aacute</label>
	  		<textarea class="form-control" id="description" name="description" rows="3"></textarea>
	  		<br>
			
			<div class="panel" id="appointmentsPanel" style="display: none;"></div>
			<div id="appointments"></div>		        
		</div>
		
		<div style="margin-top: 25px;">
			<div class="alert alert-success" id="successDiv" role="alert" style="display:none;">Attivit&aacute aggiunta con successo!</div>
			<div class="alert alert-danger" id="failureDiv" role="alert" style="display:none;">Aggiunta attivit&aacute fallita!</div>
		</div>
							
		<div class="panel"></div>
		<div style="margin-bottom: 25px;">
			<input class="btn btn-success" id="addActivity" type="button" onclick="validateInputsActivity()" value="Aggiungi">		        	
		</div>
	</div>
     
    <script src="<%= request.getContextPath() %>/js/tutorScript.js"></script>        
	<script>
		$(document).ready( function() {
			var now = new Date();			
			document.getElementById("date").valueAsDate = now;
			
			var timeString = now.toISOString().substr(11, 8);
									
			document.getElementById("startTime").value = timeString;
			document.getElementById("startTime").stepDown(1);
			
			now.setHours(now.getHours() + 1 );
			
			timeString = now.toISOString().substr(11, 8);
			
			document.getElementById("finishTime").value = timeString;
			document.getElementById("finishTime").stepDown(1);
			
			$("#addActivity").click();
		});
	</script>
     	
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>