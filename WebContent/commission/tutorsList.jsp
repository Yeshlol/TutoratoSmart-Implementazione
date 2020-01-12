<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 	Collection<TutorBean> tutorsCollection = (Collection<TutorBean>) request.getAttribute("tutorsCollection"); %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.TutorBean" %>

<head>
	<meta charset="UTF-8">
	<title>Lista Tutor</title>
	
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
    
    <div class="content text-center" style="width: 70%;margin: 0 auto; padding-bottom: 25px; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<% if (tutorsCollection == null || tutorsCollection.isEmpty()) { %>
			<h2 align="center">Nessun tutor trovato</h2>
		<% } else { %>
			<div class="panel text-center">
				<h2>Tutor trovati</h2>
			</div>
			
			<table style="width: 95%;margin: 0 auto;">
				<tr>						
					<th class="text-center">Nome</th>
		    		<th class="text-center">Cognome</th>
		    		<th class="text-center">Matricola</th>
		    		<th class="text-center">Data inizio tutorato</th>
		    		<th class="text-center">Data termine tutorato</th>
		    		<th class="text-center">Email</th>
		    		<th class="text-center">Dettagli</th>
	    		</tr>
				<% 	Iterator<?> it = tutorsCollection.iterator();
				 	for(int i = 0 ; i < tutorsCollection.size() && it.hasNext(); i++){ 
						TutorBean bean = (TutorBean) it.next(); %>
			 			<tr>
				 			<td><%= bean.getFirstName() %></td>
				 			<td><%= bean.getLastName() %></td>
				 			<td><%= bean.getRegistrationNumber() %></td>
				 			<td><%= bean.getStartDate() %></td>
				 			<td><%= bean.getFinishDate() %></td>
				 			<td><%= bean.getEmail() %></td>
				 			<td><a href ="<%= response.encodeURL(request.getContextPath() + "/ShowRegister?Email=" + bean.getEmail()) %>&flag=2">Mostra</a></td>
				 		</tr>		 
				<%	} %>			
			</table>
		<%	} %>
		
		<div class="panel"></div>
		<input class="btn btn-primary" type="button" value="Indietro" onClick="history.go(-1);return true;">			
	 </div>
        
    <%@ include file="/partials/footer.jsp" %>        
</body>
</html>
