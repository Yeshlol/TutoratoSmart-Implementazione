<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Collection<ActivityTutorBean> activitiesCollection= (Collection<ActivityTutorBean>) request.getAttribute("activitiesCollection");  
       
    if (activitiesCollection == null){
		response.sendRedirect(response.encodeRedirectURL("/TutoratoSmart/ActivityTutor"));
		return;
	} %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.ActivityTutorBean" %>

<head>
	<title>Registro Tutor</title>
    <style>
    table,th,td{
    	border: 1px solid black;
    	border-collapse: collapse;
    }
    th {
     background-color:#4d94ff;
     color: white;
    }
    th,td {
    padding:8px;
    text-align:left;
    }
    </style>   
</head>

<body>
	<%@ include file="/partials/header.jsp" %>

  	<h1 style="margin-left:550px;"> Registro Tutor</h1>
	<div id="content">
		<br>
		<br>
		<br>
		<div id="activityDiv">
			<table style="width:70%; margin-left:200px;">
				<tr>					
       			<% if (activitiesCollection.isEmpty()) { %>
					<th>Nessun'attivit&aacute trovata!</th>
				<%  } else  { %>
					<th>Categoria</th>
		    		<th>Data</th>
		    		<th>Stato</th>
		    		<th>Dettagli</th>
		    	</tr>
				<% 	Iterator<?> it = activitiesCollection.iterator();
				 	for(int i = 0 ; i < activitiesCollection.size() && it.hasNext(); i++){ 
						ActivityTutorBean bean = (ActivityTutorBean) it.next(); %>
			 			<tr>
				 			<td><%= bean.getCategory() %></td>
				 			<td><%= bean.getActivityDate() %></td>
				 			<td><%= bean.getState() %></td>
				 			<td><a href ="<%= response.encodeURL(request.getContextPath() + "/Activity?id=" + bean.getIdActivity()) %>">Mostra</a></td>
				 		</tr>
				<%	} 
				} %>						
			</table>
		</div>
		<br>
			 			
	 	<div>
	 		<button type="submit" value="addActivity" style="margin-left:200px;">Aggiungi attivit&aacute lavorativa al registro</button>	 	
	 		<button type="submit" value="generaRegistro" style="margin-left:30px;">Genera registro tutorato</button>
	 	</div>	
	</div>
    
	<%@ include file="/partials/footer.jsp" %>

</body>
</html>