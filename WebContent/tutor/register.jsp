<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Collection<ActivityTutorBean> activitiesCollection = (Collection<ActivityTutorBean>) request.getAttribute("activitiesCollection");  
       
    if (activitiesCollection == null){
		response.sendRedirect(response.encodeRedirectURL("/TutoratoSmart/ShowRegister?flag=1"));
		return;
	}    
%>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.ActivityTutorBean,project.Model.TutorBean,project.Model.TutorDAO,project.Model.RegisterDAO,project.Model.RegisterBean" %>

<head>
	<meta charset="UTF-8">
	<title>Registro Tutor</title>
	
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
			<h2 align="center">Registro di tutorato - <%= user.getFirstName() %> <%= user.getLastName() %></h2>
			
			<% 	
				TutorDAO tutorDAO = new TutorDAO();
				TutorBean tutor = tutorDAO.doRetrieveByMail(user.getEmail());
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
					 			<td><a href ="<%= response.encodeURL(request.getContextPath() + "/ShowActivity?id=" + bean.getIdActivity() + "&flag=1") %>">Mostra</a></td>
					 		</tr>		 
					<%	} 
				} %>			
			</table>
		</div>
		
		<% if (register.getState().equals("Approvato")) { %>
		
		<div class="container-fluid" style="margin: 25px; margin-bottom: 10px;">
			<div class="panel"></div>
			<div class="row row-space">
				<div class="row">
					<div class="form-group col-lg-6">
						<a href="activity.jsp"><input class="btn btn-primary" type="button" value="Aggiungi attivit&aacute"></a>
					</div>
					<div class="form-group col-lg-6">
						<input class="btn btn-primary" id="addActivity" type="submit" onclick="validateInputsActivity()" name="addActivity" value="Genera registro">
					</div>
				</div>
			</div>
		</div>
		
		<% } else { %>
			
		<div class="panel"></div>
		<div style="margin-bottom: 25px;">
			<a href="tutor/activity.jsp"><input class="btn btn-primary" type="button" value="Aggiungi attivit&aacute"></a>		        	
		</div>
			
		<% } %>
	</div>
    
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>