<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Collection<StudentBean> studentsCollection = (Collection<StudentBean>) request.getAttribute("studentsCollection"); %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.StudentBean,project.Model.RequestDAO,project.Model.RequestBean,project.Model.AppointmentDAO,project.Model.AppointmentBean" %>

<head>
	<meta charset="UTF-8">
	<title>Visualizzazione lista studenti</title>
	
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
    
    <div id="content text-center" style="width: 70%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2 align="center">Visualizzazione lista studenti</h2>
		</div>
		
		<div id="tutorsDiv">
			<% if (studentsCollection == null || studentsCollection.isEmpty()) { %>
				<table style="width: 95%;margin: 0 auto;margin-bottom: 25px"><tr><th class="text-center">Nessuno studente trovato!</th></tr></table>
			<%  } else  {
				Iterator<?> it = studentsCollection.iterator();
			 	for(int i = 0 ; i < studentsCollection.size() && it.hasNext(); i++){ 
		 			StudentBean student = (StudentBean) it.next(); 
		 	%>
		 	<div class="panel">
				<h4 align="center">Studente</h4>
			</div>	 			
			 	<table style="width: 95%;margin: 0 auto;margin-bottom: 25px">
			 		<tr>
			 			<th class="text-center">Nome</th>
		    			<th class="text-center">Cognome</th>
		    			<th class="text-center">Matricola</th>
		    			<th class="text-center">Anno accademico</th>
		    			<th class="text-center">Email</th>
	    			</tr>
		 			<tr>
			 			<td><%= student.getFirstName() %></td>
			 			<td><%= student.getLastName() %></td>
			 			<td><%= student.getRegistrationNumber() %></td>
			 			<td><%= student.getAcademicYear() %></td>
			 			<td><%= student.getEmail() %></td>
			 		</tr>
			 	</table>
			 	<div class="panel">
					<h4 align="center">Richieste di appuntamento/Appuntamenti effettuati</h4>
				</div>
			 	
			 	<table style="width: 95%;margin: 0 auto;margin-bottom: 25px">		
					<tr>
						<th class="text-center">Descrizione richiesta di appuntamento</th>
						<th class="text-center">Commento tutor</th>
					</tr>
			 	
				<%		RequestDAO requestDAO = new RequestDAO();
						Collection<RequestBean> requestsCollection = requestDAO.doRetrieveAllByMail(null, student.getEmail());
						AppointmentDAO appointmentDAO = new AppointmentDAO();
						
						Iterator<?> it2 = requestsCollection.iterator();
						for(int j = 0; j < requestsCollection.size() && it2.hasNext(); j++) {
							RequestBean req = (RequestBean) it2.next(); 
							AppointmentBean appointment = appointmentDAO.doRetrieveByRequestId(null, req.getIdRequest());
				%>	
					<tr>
						<td><%= req.getStudentComment() %></td>
						<td><%= appointment.getDetails() %></td>
					</tr>
				<%		} %>
					</table>
				<%} 
			} %> 			
	 	</div> 			
	</div>
        
    <%@ include file="/partials/footer.jsp" %>        
</body>
</html>
