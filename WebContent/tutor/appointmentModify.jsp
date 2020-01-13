<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%  RequestBean req = (RequestBean) session.getAttribute("request"); 
	AppointmentBean appointment = (AppointmentBean) session.getAttribute("appointment");
%>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.RequestBean,project.Model.AppointmentBean" %>

<head>
	<meta charset="UTF-8">
	<title>Modifica Appuntamento</title>
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
			<h2>Modifica appuntamento</h2>
			<p>Inserisci un commento relativo all'appuntamento svolto</p>
		</div>
		
		<div class="container-fluid" style="margin: 25px;">
			<div class="row row-space">
				<div class="row">
					<div class="alert alert-warning" id="errorDiv" role="alert" style="display:none;"></div>
				</div>
			</div>
											
	  		<label for="appointmentComment" class="control-label">Inserire un commento all'appuntamento</label>
	  		<textarea class="form-control" id="appointmentComment" rows="3"></textarea>
	  		<br>
						
			<div style="margin-top: 25px;">
				<div class="alert alert-success" id="successDiv" role="alert" style="display:none;margin-top: 25px;">Appuntamento modificato con successo</div>
				<div class="alert alert-danger" id="failureDiv" role="alert" style="display:none;margin-top: 25px;">Modifica appuntamento fallita</div>
			</div>
									
			<div class="panel"></div>
			<div>
				<input class="btn btn-success" id="modifyAppointment" type="button" onclick="validateModifyAppointment()" value="Modifica">		        	
	        </div>
	    </div>
	</div>
          
    <script src="<%= request.getContextPath() %>/js/tutorScript.js"></script>
     	
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>