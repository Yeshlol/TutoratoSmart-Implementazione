<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Visualizzazione lista tutor</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/csspage.css">
    <style>
    table,th,td{
    border: 1px solid black;
    border-collapse: collapse;
    }
    </style>
   
</head>
<body>
     <%@ include file="/partials/header.jsp" %>
     <%@ page import="java.util.*,project.Model.TutorBean" %>

     
        <h1 style="margin-left:450px;">Visualizzazione lista tutor</h1>
        
         <%
            
         Collection<TutorBean> tutorCollection= (Collection<TutorBean>) request.getAttribute("tutorCollection");     
         
         if(tutorCollection==null ){
     		response.sendRedirect(response.encodeRedirectURL("/TutoratoSmart/ServletTutor"));
     		return;
     	}
       
       %>

               <form method="POST" action="<%= response.encodeURL("/TutoratoSmart/ServletTutor") %>">
                    <div style="margin-left:120px;">Inserisci la data inizio attività:<input type="date" name="startActivity"></div><br><br>
                      
                    <div style="margin-left:120px;">Inserisci la data fine attività:<input type="date" name="finishActivity"></div><br><br> 
                      
                    <input type="submit" style="margin-left:120px;">
                                    
                    </form>
                    
                    
          <div id="content">
          <% 
            if (tutorCollection.isEmpty()) { %>
			<h1>Nessun Tutor Trovato!</h1>
			
		<% } else  { %>		
		<br>
		<br>
		<br>
		
		<div id="ordiniDiv">
			<table style="width:70%; margin-left:120px;">
				<tr>
    			<th>FirstName</th>
    			<th>LastName</th>
    			<th>Registration Number</th>
    			<th>Start Date</th>
    			<th>Finish Date</th>
    			<th>Email</th>
    			</tr>
			<% 	Iterator<?> it = tutorCollection.iterator();
			 	for(int i = 0 ; i < tutorCollection.size() && it.hasNext(); i++){ 
			 		TutorBean bean = (TutorBean) it.next(); %>
		 		<tr>
		 			<td ><%= bean.getFirstName() %></td>
		 			<td><%= bean.getLastName() %></td>
		 			<td><%= bean.getRegistrationNumber() %></td>
		 			<td><%= bean.getStartDate() %></td>
		 			<td><%= bean.getFinishDate() %></td>
		 			<td><%= bean.getEmail() %></td>
		 		</tr>		 
			<%	 } %>			
			</table> 			
 		</div>
 		<% } %> 		
	</div>
    
    
     <%@ include file="/partials/footer.jsp" %>         
</body>
</html>