<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Collection<ActivityTutorBean> activitiesCollection = (Collection<ActivityTutorBean>) request.getAttribute("activitiesCollection");  
       
    if (activitiesCollection == null){
		response.sendRedirect(response.encodeRedirectURL("/TutoratoSmart/ShowRegister?flag=2"));
		return;
	}
    
    TutorBean tutor = (TutorBean) request.getAttribute("tutor");
    
    String delete = (String) session.getAttribute("delete");
%>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.ActivityTutorBean,project.Model.TutorBean,project.Model.RegisterDAO,project.Model.RegisterBean" %>

<head>
	<meta charset="UTF-8">
	<title>Registro Tutor</title>
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
			<h2 align="center">Registro di tutorato - <%= tutor.getFirstName() %> <%= tutor.getLastName() %></h2>
			<% 	
				RegisterDAO registerDAO = new RegisterDAO();
				RegisterBean register = registerDAO.doRetrieveById(tutor.getRegisterId());
			%>
			<table style="width: 95%;margin: 0 auto;margin-bottom: 25px">
				<tr>
					<th class="text-center">Numero registro</th>
					<th class="text-center">Stato</th>
					<th class="text-center">Ore Totali</th>
					<th class="text-center">Ore Convalidate</th>
					<th class="text-center">Percentuale di completamento</th>
				</tr>
				<tr>
					<td><%= register.getIdRegister() %></td>
					<td><%= register.getState() %></td>
					<td><%= register.getTotalHours() %></td>
					<td><%= register.getValidatedHours() %></td>
					<td><%= register.getPercentageComplete() %> %</td>
				</tr>
			</table>
		</div>
				
		<div id="registerDiv">
			<table style="width: 95%;margin: 0 auto;margin-bottom: 25px">
				<tr>					
       			<% if (activitiesCollection.isEmpty()) { %>
					<th class="text-center">Nessuna attivit&aacute registrata!</th>
				<%  } else  { %>
						<th class="text-center">Categoria</th>
		    			<th class="text-center">Data</th>
		    			<th class="text-center">Stato</th>
		    			<th class="text-center">Dettagli</th>
	    		</tr>
					<% 	Iterator<?> it = activitiesCollection.iterator();
					 	for(int i = 0 ; i < activitiesCollection.size() && it.hasNext(); i++) { 
				 			ActivityTutorBean bean = (ActivityTutorBean) it.next(); %>
				 			<tr>
					 			<td><%= bean.getCategory() %></td>
					 			<td><%= bean.getActivityDate() %></td>
					 			<td><%= bean.getState() %></td>
					 			<td><a href ="<%= response.encodeURL(request.getContextPath() + "/ShowActivity?id=" + bean.getIdActivity()) %>&flag=2">Mostra</a></td>
					 		</tr>		 
					<%	} 
				} %>			
			</table>
		</div>
		
		<div style="margin-bottom: 25px;">
			<div class="panel"></div>
			<% if(delete != null && delete.equals("true")) {
				session.removeAttribute("delete"); 
			%>
				<input class="btn btn-primary" type="button" value="Indietro" onClick="history.go(-3);return true;">
			<% } else { %>	
				<input class="btn btn-primary" type="button" value="Indietro" onClick="history.go(-1);return true;">
			<% } %>
		</div>
	</div>
    
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>