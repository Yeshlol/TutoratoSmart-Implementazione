
<!DOCTYPE html>
<html>
<%@ page import="java.util.*,project.Model.ActivityTutorBean" %>
<head>
<meta charset="ISO-8859-1">
<title>Attività</title>
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

  <h1 style="margin-left:550px;"> Dettagli attività</h1>
  
    
      <div id="content">
		<br>
		<br>
		<br>
		<div id="tutorsDiv">
			<table style="width:70%; margin-left:200px;">
				<tr>					
						<th>Categoria</th>
		    			<th>Data</th>
		    			<th>Ora di inizio</th>
		    			<th>Ora di fine</th>
		    			<th>Ore di attività svolte</th>
		    			<th>Stato</th>		    					    			
	    			</tr>
	    			 <% ActivityTutorBean activity= (ActivityTutorBean) request.getAttribute("activity"); %>
			 			<tr>
				 			<td><%= activity.getCategory() %></td>				 			
				 			<td><%= activity.getActivityDate() %></td>
				 			<td><%= activity.getStartTime() %></td>				 			
				 			<td><%= activity.getFinishTime() %></td>
				 			<td><%= activity.getHours() %></td>
				 			<td><%= activity.getState() %></td>
				 		</tr>
			</table>
			</div>
			</div>
			<br>
			<div id="content">
		<div id="tutorsDiv">
			<table style="width:70%; margin-left:200px;">
				<tr>										
		    			<th>Dettagli</th>		    			
	    			</tr>
			 			<tr>				 		
				 			<td><%= activity.getDetails() %></td>
				 		</tr>
			</table>
			</div>
			</div>
			<br>
			<br>
			<div id="modificaDati">
			<a href="<%= response.encodeURL("/TutoratoSmart/modifyActivity.jsp") %>"><button style="margin-left:200px;">Modifica</button></a>
			</div>

<%@ include file="/partials/footer.jsp" %>
</body>
</html>