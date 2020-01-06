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

<%@ page import="java.util.*,project.Model.ActivityTutorBean" %>

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
		
		<div class="container-fluid" style="margin: 25px; margin-bottom: 10px;">
			<div class="row row-space">
				<div class="row">
					<div class="form-group col-lg-6">
						<a class="btn btn-primary" href="tutor/activity.jsp">Aggiungi attivit&aacute lavorativa al registro</a>
					</div>
					<div class="form-group col-lg-6">
						<input class="btn btn-primary" id="generateRegister" type="button" onclick="generateRegister()" name="generateRegister" value="Genera registro tutorato">
					</div>
				</div>
			</div>
		</div>	
	</div>
    
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>