<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% ActivityTutorBean activity = (ActivityTutorBean) session.getAttribute("activity"); %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Utils.Utils,project.Model.ActivityTutorBean" %>

<head>
	<meta charset="UTF-8">
	<title>Dettagli attivit&aacute</title>
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon">
	<link rel="icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon">
	
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
		  width: 33%;
		}
		.vertical-alignment-helper {
		    display:table;
		    height: 100%;
		    width: 100%;
		    pointer-events:none;
		}
		.vertical-align-center {
		    display: table-cell;
		    vertical-align: middle;
		    pointer-events:none;
		}
		.modal-content {
		    width:inherit;
		    max-width:inherit;
		    height:inherit;
		    margin: 0 auto;
		    pointer-events: all;
		}
    </style>   
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	
	<div id="content text-center" style="width: 70%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
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
		
		<div class="alert alert-success" id="successDeleteDiv" role="alert" style="display:none;margin-top: 25px;">Attivit&aacute rimossa con successo!</div>
		<div class="alert alert-danger" id="failureDeleteDiv" role="alert" style="display:none;margin-top: 25px;">Rimozione attivit&aacute fallita!</div>
				
		<div class="panel"></div>
		<% if (!activity.getState().equals("Convalidata")) {%>
			<div class="row text-center" style="margin-bottom: 25px;">
				<div class="column">
					<a href="/TutoratoSmart/View/tutor/activityModify.jsp"><input class="btn btn-warning" id="modifyActivity" type="button" value="Modifica attivit&aacute"></a>
				</div>
				<div class="column">
					<button type="button" class="btn btn-danger" id="deleteButton">Elimina attivit&aacute</button>
				</div>
				<div class="column">
					<input class="btn btn-primary" type="button" id="back" value="Indietro" onClick="location.href='View/tutor/register.jsp';">
				</div>
	 		</div>
	 	<% } else { %>
	 		<div class="row text-center" style="margin-bottom: 25px;">
	 			<div class="column" style="width: 100%;">
					<input class="btn btn-primary" type="button" id="back" value="Indietro" onClick="location.href='View/tutor/register.jsp';">
				</div>
	 		</div> 		
	 	<% } %>
	 	
	 	<div class="modal fade" id="deleteModal" role="dialog">
			 <div class="vertical-alignment-helper">
	        	<div class="modal-dialog vertical-align-center">
	            	<div class="modal-content">
	            		<div class="modal-header">
			          		<button type="button" class="close" data-dismiss="modal">&times;</button>
			          		<h4 class="modal-title">Elimina attivit&aacute</h4>
			       		</div>
				        <div class="modal-body">
				          	<div class="modal-body">
			          			<p>Procedere con l'eliminazione?</p>
			        		</div>
			        	</div>
			        	<div class="modal-footer">
			        		<div class="row text-center">
						  		<div class="column" style="width: 50%;"><input class="btn btn-danger" type="button" onclick="deleteActivity()" value="Elimina attivit&aacute"></div>
						  		<div class="column" style="width: 50%;"><button type="button" class="btn btn-primary" data-dismiss="modal">Annulla</button></div>
							</div>
				    	</div>
			    	</div>
			  	</div>
			</div>
		</div>
	</div>
		
	<%@ include file="/partials/footer.jsp" %>
	
	<script src="<%= request.getContextPath() %>/js/tutorScript.js"></script>
	<script>
		$(document).ready(function(){
			$("#deleteButton").click(function(){
				$("#deleteModal").modal();
			});
		});
	</script>
</body>
</html>
