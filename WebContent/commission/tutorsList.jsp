<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.TutorBean" %>

<head>
	<meta charset="UTF-8">
	<title>Visualizzazione lista tutor</title>
	
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
    
    <h1 align="center">Visualizzazione lista tutor</h1>
        
	<% Collection<TutorBean> tutorsCollection = (Collection<TutorBean>) request.getAttribute("tutorsCollection"); %>
          
	<div id="content">
		<br>
		<div id="tutorsDiv">
			<table style="width:70%; margin: 0 auto">
				<tr>					
       			<% if (tutorsCollection.isEmpty()) { %>
					<th class="text-center">Nessun Tutor Trovato!</th>
				<%  } else  { %>
						<th class="text-center">FirstName</th>
		    			<th class="text-center">LastName</th>
		    			<th class="text-center">Registration Number</th>
		    			<th class="text-center">Start Date</th>
		    			<th class="text-center">Finish Date</th>
		    			<th class="text-center">Email</th>
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
				 		</tr>		 
					<%	} 
				} %>			
			</table> 			
	 	</div> 			
	</div>
        
    <%@ include file="/partials/footer.jsp" %>        
</body>
</html>
