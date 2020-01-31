<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%  Collection<AppointmentBean> appointmentsCollection = (Collection<AppointmentBean>) request.getAttribute("appointmentsCollection");  
       
    if (appointmentsCollection == null){
    	response.sendRedirect(response.encodeRedirectURL("/TutoratoSmart/ShowAppointmentRequestManagement?flag=1"));
		return;
	}    
%>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Utils.Utils,project.Model.AppointmentBean,project.Model.RequestDAO,project.Model.RequestBean,project.Model.StudentDAO,project.Model.StudentBean" %>

<head>
	<meta charset="UTF-8">
	<title>Storico Appuntamenti</title>
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
    </style>   
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	
	<div class="content text-center" style="width: 70%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2 align="center">Appuntamenti registrati</h2>
		</div>
										
		<div id="registerDiv">
			<table style="width: 95%;margin: 0 auto;margin-bottom: 25px">
				<tr>					
       			<% if (appointmentsCollection.isEmpty()) { %>
					<th class="text-center">Nessun appuntamento registrato</th>
				<%  } else  { %>
						<th class="text-center">Data</th>
		    			<th class="text-center">Studente</th>
		    			<th class="text-center">Dettagli</th>
	    		</tr>
					<% 	Iterator<?> it = appointmentsCollection.iterator();
					 	for(int i = 0 ; i < appointmentsCollection.size() && it.hasNext(); i++) { 
					 		AppointmentBean bean = (AppointmentBean) it.next(); 
					 		RequestDAO requestDAO = new RequestDAO();
					 		RequestBean req = requestDAO.doRetrieveById(bean.getRequestId());
					 		StudentDAO studentDAO = new StudentDAO();
					 		StudentBean student = studentDAO.doRetrieveByMail(req.getStudent());
					 		%>
				 			<tr>
					 			<td><%= req.getRequestDate() %></td>
					 			<td><%= student.getLastName() %> <%= student.getFirstName() %></td>
					 			<td><a href ="<%= response.encodeURL(request.getContextPath() + "/ShowAppointmentRequestManagement?id=" + bean.getIdAppointment() + "&flag=2") %>">Mostra</a></td>
					 		</tr>		 
					<%	} 
				} %>			
			</table>
		</div>		
	</div>
    
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>