<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%  Collection<RequestBean> requestsCollection = (Collection<RequestBean>) request.getAttribute("requestsCollection");  

	if (requestsCollection == null){
		response.sendRedirect(response.encodeRedirectURL("/TutoratoSmart/ShowRequest?flag=1"));
		return;
	}
%>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Utils.Utils,project.Model.RequestBean" %>

<head>
	<meta charset="UTF-8">
	<title>Richieste di appuntamento effettuate</title>

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
	
	<div class="content text-center" style="width: 70%;margin: 0 auto; padding-bottom: 25px; border: 2px solid #232F3E; border-radius:8px;">
		<% if (requestsCollection == null || requestsCollection.isEmpty()) { %>
			<h2 align="center">Nessuna richiesta di appuntamento trovata</h2>
		<% } else { %>
			<div class="panel text-center">
				<h2>Elenco prenotazioni</h2>
			</div>
			
			<table style="width: 95%;margin: 0 auto;margin-bottom: 25px">
				<tr>
					<th class="text-center">Data</th>
					<th class="text-center">Orario</th>
					<th class="text-center">Commento</th>
					<th class="text-center">Dettagli</th>
				</tr>
				<% 	Iterator<?> it = requestsCollection.iterator();
				 	for(int i = 0 ; i < requestsCollection.size() && it.hasNext(); i++) { 
			 			RequestBean bean = (RequestBean) it.next(); %>
			 			<tr>
				 			<td><%= bean.getRequestDate() %></td>
				 			<td><%= Utils.getTimeAsString(bean.getRequestTime()) %></td>
				 			<td><%= bean.getStudentComment() %></td>
				 			<td><a href ="<%= response.encodeURL(request.getContextPath() + "/ShowRequest?Id=" + bean.getIdRequest()) %>&flag=2">Mostra</a></td>
				 		</tr>		 
				<%	} %>
			</table>
			<% } %>	
	</div>	
	
	<%@ include file="/partials/footer.jsp" %>     
</body>
</html>
