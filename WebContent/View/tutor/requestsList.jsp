<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Collection<RequestBean> requestsCollection = (Collection<RequestBean>) request.getAttribute("requestsCollection");  
       
    if (requestsCollection == null){
		response.sendRedirect(response.encodeRedirectURL("/TutoratoSmart/ShowRequestRequestManagement?flag=2"));
		return;
	}    
%>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Utils.Utils,project.Model.RequestBean" %>

<head>
	<meta charset="UTF-8">
	<title>Richieste Appuntamento</title>
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
			<h2 align="center">Richieste di appuntamento da valutare</h2>
		</div>
										
		<div id="registerDiv">
			<table style="width: 95%;margin: 0 auto;margin-bottom: 25px">
				<tr>					
       			<% if (requestsCollection.isEmpty()) { %>
					<th class="text-center">Nessuna nuova prenotazione!</th>
				<%  } else  { %>
						<th class="text-center">Data</th>
		    			<th class="text-center">Orario</th>
		    			<th class="text-center">Stato</th>
		    			<th class="text-center">Dettagli</th>
	    		</tr>
					<% 	Iterator<?> it = requestsCollection.iterator();
					 	for(int i = 0 ; i < requestsCollection.size() && it.hasNext(); i++) { 
				 			RequestBean bean = (RequestBean) it.next(); %>
				 			<tr>
					 			<td><%= bean.getRequestDate() %></td>
					 			<td><%= Utils.getTimeAsString(bean.getRequestTime()) %></td>
					 			<td><%= bean.getState() %></td>
					 			<td><a href ="<%= response.encodeURL(request.getContextPath() + "/ShowRequestRequestManagement?id=" + bean.getIdRequest() + "&flag=1") %>">Mostra</a></td>
					 		</tr>		 
					<%	} 
				} %>			
			</table>
		</div>		
	</div>
    
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>