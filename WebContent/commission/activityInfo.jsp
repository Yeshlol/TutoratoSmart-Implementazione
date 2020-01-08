<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% ActivityTutorBean activity = (ActivityTutorBean) session.getAttribute("activity"); %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.ActivityTutorBean,project.Utils.Utils" %>

<head>
	<meta charset="UTF-8">
	<title>Dettagli Attivit&aacute</title>
    
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
	
	<div class="content text-center" style="width: 70%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2 align="center">Dettagli attivit&aacute del <%= activity.getActivityDate() %></h2>
		</div>
		
		<div id="activityDiv" style="margin: 25px;">
			<table style="width: 95%;margin: 0 auto;margin-bottom: 25px">
				<tr>					
	       			<th class="text-center">Categoria</th>
		    		<th class="text-center">Data</th>
		    		<th class="text-center">Orario di inizio attività</th>
		    		<th class="text-center">Orario di fine attività</th>
		    		<th class="text-center">Ore di attivit&aacute svolte</th>
		    		<th class="text-center">Stato</th>
	    		</tr>	    		
				<tr>
				 	<td><%= activity.getCategory() %></td>				 			
				 	<td><%= activity.getActivityDate() %></td>
				 	<td><%= Utils.getTimeAsString(activity.getStartTime()) %></td>				 			
				 	<td><%= Utils.getTimeAsString(activity.getFinishTime()) %></td>
				 	<td><%= activity.getHours() %></td>
				 	<td><%= activity.getState() %></td>
				</tr>
				
				<tr>
					<th class="text-center" colspan="6">Descrizione attivit&aacute</th>
				</tr>				
				<tr>
					<td colspan="6"><%= activity.getDetails() %></td>
				</tr>
			</table>
		</div>
		
		<% if(activity.getState().equalsIgnoreCase("In valutazione")) { %>
			<div class="container-fluid" style="margin: 25px; margin-bottom: 10px;">
				<div class="row row-space">
					<div class="row">
						<div class="form-group col-lg-6">
							<a href="<%= response.encodeURL(request.getContextPath() +  "/Activity?flag=3&validate=true&id=" + activity.getIdActivity()) %>"><input class="btn btn-primary"  id="validateActivityButton" type="button" value="Convalida attivit&aacute"></a>
						</div>
						<div class="form-group col-lg-6">
							<button type="button" class="btn btn-primary" id="deleteButton">Elimina attivit&aacute</button>
	 
							<div class="modal fade" id="deleteModal" role="dialog">
							    <div class="modal-dialog">
							        <div class="modal-content">
								        <div class="modal-header">
								          	<button type="button" class="close" data-dismiss="modal">&times;</button>
								          	<h4 class="modal-title">Elimina attivit&aacute</h4>
								        </div>
								        <div class="modal-body">
								          	<p>Procedere con l'eliminazione?</p>
								        </div>
								        <div class="modal-footer">
								        	<button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
								    	</div>
							    	</div>						      
								</div>
							</div>
						</div>
						<div class="form-group col-lg-6">
							<input class="btn btn-primary" type="button" value="Indietro" onClick="history.go(-1);return true;">
						</div>
					</div>
				</div>
			</div>
		<% } else {%>
			<div class="panel"></div>
			<div style="margin-bottom: 25px;">
				<input class="btn btn-primary" type="button" value="Indietro" onClick="history.go(-1);return true;">
			</div>		
		<% } %>
		
	</div>
		
	<%@ include file="/partials/footer.jsp" %>
	
	<script src="<%= request.getContextPath() %>/js/commissionScript.js"></script>
	<script>
		$(document).ready(function(){
		  $("#deleteButton").click(function(){
		    $("#deleteModal").modal();
		  });
		});
	</script>
</body>
</html>
