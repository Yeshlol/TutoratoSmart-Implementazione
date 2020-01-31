<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% AppointmentBean appointment = (AppointmentBean) session.getAttribute("appointment"); %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Utils.Utils,project.Model.AppointmentDAO,project.Model.AppointmentBean,
				project.Model.RequestDAO,project.Model.RequestBean,project.Model.StudentDAO,project.Model.StudentBean" %>

<head>
	<meta charset="UTF-8">
	<title>Dettagli appuntamento</title>
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
	
	<% 
		RequestDAO requestDAO = new RequestDAO();
		RequestBean req = requestDAO.doRetrieveById(appointment.getRequestId());
		session.setAttribute("request", req);
		StudentDAO studentDAO = new StudentDAO();
		StudentBean student = studentDAO.doRetrieveByMail(req.getStudent());
	%>
	
	<div id="content text-center" style="width: 70%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2 align="center">Dettagli appuntamento del <%= req.getRequestDate() %></h2>
		</div>
		
		<div id="activityDiv" style="margin: 25px;">
			<table style="width: 95%;margin: 0 auto;margin-bottom: 25px">
				<tr>					
	       			<th class="text-center">Studente</th>
		    		<th class="text-center">Data</th>
		    		<th class="text-center">Orario</th>
		    		<th class="text-center">Commento</th>
	    		</tr>
	    		<tr>
				 	<td><%= student.getLastName() %> <%= student.getFirstName() %></td>				 			
				 	<td><%= req.getRequestDate() %></td>
				 	<td><%= Utils.getTimeAsString(req.getRequestTime()) %></td>				 			
				 	<td><%= appointment.getDetails() %></td>
				</tr>			
			</table>
		</div>
		
		<div class="alert alert-success" id="successDeleteDiv" role="alert" style="display:none;margin: 25px;">Appuntamento rimosso con successo</div>
		<div class="alert alert-danger" id="failureDeleteDiv" role="alert" style="display:none;margin: 25px;">Rimozione appuntamento fallita</div>
				
		<div class="panel"></div>
			<div class="row text-center" style="margin-bottom: 25px;">
				<div class="column">
					<a href="/TutoratoSmart/View/tutor/appointmentModify.jsp"><input class="btn btn-warning" id="modifyAppointment" type="button" value="Modifica appuntamento"></a>
				</div>
				<div class="column">
					<button type="button" class="btn btn-danger" id="deleteButton">Elimina appuntamento</button>
				</div>
				<div class="column">
					<input class="btn btn-primary" type="button" id="back" value="Indietro" onClick="location.href='View/tutor/appointmentsList.jsp';">
				</div>
	 		</div>
	 		 	
	 	<div class="modal fade" id="deleteModal" role="dialog">
			 <div class="vertical-alignment-helper">
	        	<div class="modal-dialog vertical-align-center">
	            	<div class="modal-content">
				        <div class="modal-body">
				          	<div class="modal-body text-center">
			          			<h3>Procedere con l'eliminazione?</h3>
			        		</div>
			        	</div>
			        	<div class="modal-footer">
			        		<div class="row text-center">
						  		<div class="column" style="width: 50%;"><input class="btn btn-danger" type="button" onclick="deleteAppointment()" value="Elimina appuntamento"></div>
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
