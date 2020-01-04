<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% ActivityTutorBean activity = (ActivityTutorBean) request.getAttribute("activity"); %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.ActivityTutorBean" %>

<head>
	<meta charset="ISO-8859-1">
	<title>Attivit&agrave</title>
    
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

	<h1 align="center"> Dettagli attivit&aacute</h1>
  
    <div id="content">
		<br>
		<div id="activityDiv">
			<table style="width:65%; margin: 0 auto;">
				<tr>					
					<th>Categoria</th>
		    		<th>Data</th>
		    		<th>Ora di inizio</th>
		    		<th>Ora di fine</th>
		    		<th>Ore di attivit&aacute svolte</th>
		    		<th>Stato</th>		    					    			
	    		</tr>
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
			<table style="width:65%; margin: 0 auto;">
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
	<div id="modificaDati" style="text-align: center;">
		<a href="<%= response.encodeURL("/TutoratoSmart/tutor/modifyActivity.jsp") %>"><button>Modifica</button></a>
	</div>

	<%@ include file="/partials/footer.jsp" %>
</body>
</html>
