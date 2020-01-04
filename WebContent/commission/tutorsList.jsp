<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.TutorBean" %>

<head>
	<meta charset="UTF-8">
	<title>Visualizzazione lista tutor</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
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
    
    <h1 style="margin-left:450px;">Visualizzazione lista tutor</h1>
        
	<%           
     	Collection<TutorBean> tutorsCollection = (Collection<TutorBean>) request.getAttribute("tutorsCollection");     
     
     	if (tutorsCollection == null){
 			response.sendRedirect(response.encodeRedirectURL("/TutoratoSmart/Tutors"));
 			return;
 		}       
   	%>

    <form method="POST" action="<%= response.encodeURL("/TutoratoSmart/Tutors") %>">
    	<div style="margin-left:120px;">Inserisci la data iniziale di ricerca: <input type="date" id="startActivity" name="startActivity"></div><br><br>
           
        <div style="margin-left:120px;">Inserisci la data finale di ricerca: <input type="date" id="finishActivity" name="finishActivity"></div><br><br> 
           
        <input type="submit" style="margin-left:120px;">                         
    </form>
        
	<div id="content">
		<br>
		<br>
		<br>
		<div id="tutorsDiv">
			<table style="width:70%; margin-left:120px;">
				<tr>					
       			<% if (tutorsCollection.isEmpty()) { %>
					<th>Nessun Tutor Trovato!</th>
				<%  } else  { %>
						<th>FirstName</th>
		    			<th>LastName</th>
		    			<th>Registration Number</th>
		    			<th>Start Date</th>
		    			<th>Finish Date</th>
		    			<th>Email</th>
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
