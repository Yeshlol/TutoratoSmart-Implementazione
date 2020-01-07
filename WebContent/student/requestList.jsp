<%   Collection<RequestBean> requestCollection = (Collection<RequestBean>) request.getAttribute("requestCollection");  

if (requestCollection == null){
	response.sendRedirect(response.encodeRedirectURL("/TutoratoSmart/ShowRequestList"));
	return;
} %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.RequestBean" %>

<head>
<meta charset="ISO-8859-1">
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
	
	<div class="content text-center" style="width: 70%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2 align="center">Appuntamenti effettuati - <%= user.getFirstName() %> <%= user.getLastName() %></h2>
		</div>
				
		<div id="registerDiv">
			<table style="width: 95%;margin: 0 auto;margin-bottom: 25px">
				<tr>					
       			<% if (requestCollection.isEmpty()) { %>
					<th class="text-center">Nessun appuntamento registrato!</th>
				<%  } else  { %>
						<th class="text-center">Commento</th>
		    			<th class="text-center">Data</th>
		    			<th class="text-center">Orario</th>
	    		</tr>
					<% 	Iterator<?> it = requestCollection.iterator();
					 	for(int i = 0 ; i < requestCollection.size() && it.hasNext(); i++) { 
				 			RequestBean bean = (RequestBean) it.next(); %>
				 			<tr>
					 			<td><%= bean.getStudentComment() %></td>
					 			<td><%= bean.getRequestDate() %></td>
					 			<td><%= bean.getRequestTime() %></td>
					 		</tr>		 
					<%	} 
				} %>			
			</table>
		</div>
	</div>	
	
	<%@ include file="/partials/footer.jsp" %>
     
</body>
</html>