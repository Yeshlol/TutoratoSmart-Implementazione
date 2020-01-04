<!DOCTYPE html>
<html>
<%@ page import="java.util.*,project.Model.ActivityTutorBean" %>
<head>
<title>Registro Tutor</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/csspage.css"> 
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

  <%   Collection<ActivityTutorBean> activityCollection= (Collection<ActivityTutorBean>) request.getAttribute("activityCollection");  
       
  if (activityCollection == null){
		response.sendRedirect(response.encodeRedirectURL("/TutoratoSmart/ActivityTutorServlet"));
		return;
	}        
  %>
  
  
      <div id="content">
		<br>
		<br>
		<br>
		<div id="tutorsDiv">
			<table style="width:70%; margin-left:200px;">
				<tr>					
       			<% if (activityCollection.isEmpty()) { %>
					<th>Nessun'attività trovata!</th>
				<%  } else  { %>
						<th>Categoria</th>
		    			<th>Data</th>
		    			<th>Stato</th>
		    			
	    			</tr>
					<% 	Iterator<?> it = activityCollection.iterator();
					 	for(int i = 0 ; i < activityCollection.size() && it.hasNext(); i++){ 
				 			ActivityTutorBean bean = (ActivityTutorBean) it.next(); %>
			 			<tr>
				 			<td><%= bean.getCategory() %></td>
				 			<td><a href ="<%= response.encodeURL(request.getContextPath() + "/ActivityServlet?id=" + bean.getIdActivity()) %>"><%= bean.getActivityDate() %></a></td>
				 			<td><%= bean.getState() %></td>
				 			
				 		</tr>
				 			
					<%	} 
				} %>
						
			</table>
			</div>
			<br>
			 			
	 	<div>
	 	<button type="submit" value="addActivity" style="margin-left:200px;">Aggiungi attività lavorativa al registro</button>	 	
	 	<button type="submit" value="generaRegistro" style="margin-left:30px;">Genera registro tutorato</button>
	 	</div>	
	</div>
          


 <%@ include file="/partials/footer.jsp" %>

</body>
</html>