<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%  RequestBean req = (RequestBean) session.getAttribute("request"); 
	StudentBean student = (StudentBean) session.getAttribute("student");
	AppointmentBean appointment = (AppointmentBean) session.getAttribute("appointment");
	String accept = (String) session.getAttribute("accept");
%>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Utils.Utils,project.Model.AppointmentBean,project.Model.RequestBean,project.Model.StudentBean"%>

<head>
	<meta charset="UTF-8">
	<title>Dettagli Richiesta di Appuntamento del <%= req.getRequestDate() %> </title>
	
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
	
	<div class="content text-center" style="width: 70%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2 align="center">Dettagli richiesta di appuntamento per il giorno <%= req.getRequestDate() %></h2>
		</div>
		
		<div id="requestDiv" style="margin: 25px;">
			<table style="width: 95%;margin: 0 auto;">
				<tr>
					<th class="text-center">Nome</th>
					<th class="text-center">Cognome</th>
		    		<th class="text-center">Data</th>
		    		<th class="text-center">Orario</th>
		    		<th class="text-center">Stato</th>
	    		</tr>	    		
				<tr>
					<td><%= student.getFirstName() %></td>
					<td><%= student.getLastName() %></td>
				 	<td><%= req.getRequestDate() %></td>				 			
				 	<td><%= Utils.getTimeAsString(req.getRequestTime()) %></td>
				 	<td><%= req.getState() %></td>
				</tr>
				
				<tr>
					<th class="text-center" colspan="5">Commento studente</th>
				</tr>				
				<tr>
					<td colspan="5"><%= req.getStudentComment() %></td>
				</tr>				
			</table>
			<br>
			<% if (req.getState().equals("Appuntamento effettuato")) { %>
			<table style="width: 95%;margin: 0 auto;margin-bottom: 25px">
				<tr>
					<th class="text-center" colspan="5">Commento appuntamento</th>
				</tr>
				<tr>
					<td><%= appointment.getDetails() %></td>
				</tr>
			</table>
			<% } else if(req.getState().equals("Studente assente")) { %>
			<div class="panel"></div>
			<h2 align="center">Studente Assente %></h2>		
			<% } %>
		</div>
		
		<div class="container-fluid" style="margin-top: 25px;">
			<div class="alert alert-success" id="successDeleteDiv" style="display:none;" role="alert">Prenotazione accettata con successo!</div>
			<div class="alert alert-danger" id="failureDeleteDiv" style="display:none;" role="alert">Accettazione fallita!</div>
		</div>
		
		<div class="panel"></div>
		<% if(req.getState().equalsIgnoreCase("In valutazione")) { %>
			<div class="row text-center" style="margin-bottom: 25px;">				
				<div class="column">
					<input class="btn btn-success" id="acceptRequest" type="button" value="Accetta prenotazione">
				</div>
 				<div class="column">
					<div>
						<input class="btn btn-primary" type="button" value="Indietro" onClick="history.go(-1);return true;">
					</div>
				</div>
			</div>			
		<% } else if(req.getState().equalsIgnoreCase("Accettata")) {%>	
			<div class="row text-center" style="margin-bottom: 25px;">				
				<div class="column" style="width: 33%;">
					<a href="/TutoratoSmart/tutor/appointment.jsp"><input class="btn btn-success" id="confirmAppointment" type="button" value="Appuntamento effettuato"></a>
				</div>
				<div class="column" style="width: 33%;">
					<input class="btn btn-warning" id="absentStudent" type="button" value="Studente assente">
				</div>
 				<div class="column" style="width: 33%;">
					<div style="margin-bottom: 25px;">
						<input class="btn btn-primary" type="button" value="Indietro" onClick="history.go(-1);return true;">
					</div>
				</div>
			</div>	
		<% } else { %>	
			<div class="row text-center" style="margin-bottom: 25px;">
				<div class="column" style="width: 100%;">
					<% if(accept != null && accept.equals("true")) {
						session.removeAttribute("accept"); 
					%>
						<input class="btn btn-primary" type="button" value="Indietro" onclick="location.href='calendar.jsp';">
					<% } else { %>	
						<input class="btn btn-primary" type="button" value="Indietro" onClick="history.go(-1);return true;">
					<% } %>
				</div>
			</div>		
		<% } %>
				
		<div class="modal fade" id="acceptModal" role="dialog">
			 <div class="vertical-alignment-helper">
	        	<div class="modal-dialog vertical-align-center">
	            	<div class="modal-content">
				        <div class="modal-body">
				          	<div class="container-fluid" style="margin: 25px;">
								<div class="row row-space">
									<div class="row text-center">
										<label for="requestDate" class="control-label">Indicare la durata prevista (In minuti)</label>
								  		<input type="number" id="requestDuration" class="form-control" value="15" min="10" max="120" step="5">
									</div>
									<br>
									<div class="row">
										<div class="alert alert-warning" id="errorDiv" role="alert" style="display:none;"></div>
									</div>
								</div>
							</div>
				        </div>
				        <div class="modal-footer">
				        	<div class="row text-center">
							  <div class="column"><input class="btn btn-success" type="button" onclick="acceptRequest()" value="Conferma prenotazione"></div>
							  <div class="column"><button type="button" class="btn btn-primary" data-dismiss="modal">Annulla</button></div>
							</div>
					    </div>
			    	</div>
			  	</div>
			</div>
		</div>
	</div>
	
	<input type="hidden" id="requestId" value="<%= req.getIdRequest() %>">
	
	<%@ include file="/partials/footer.jsp" %>
	
	<script src="<%= request.getContextPath() %>/js/tutorScript.js"></script>
	<script>
		$(document).ready(function(){
			$("#acceptRequest").click(function(){
				$("#acceptModal").modal();
			});
		});
	</script>
</body>
</html>