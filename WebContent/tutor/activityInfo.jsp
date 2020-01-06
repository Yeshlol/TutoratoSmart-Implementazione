<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% ActivityTutorBean activity = (ActivityTutorBean) request.getAttribute("activity"); %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.ActivityTutorBean" %>

<head>
	<meta charset="UTF-8">
	<title>Attivit&aacute</title>
    
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
	
	<div id="content text-center" style="width: 70%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2 align="center">Dettagli attivit&aacute del <%= activity.getActivityDate() %></h2>
		</div>
		
		<div id="activityDiv" style="margin: 25px;">
			<table style="width: 95%;margin: 0 auto;margin-bottom: 25px">
				<tr>					
	       			<th class="text-center">Categoria</th>
		    		<th class="text-center">Data</th>
		    		<th class="text-center">Orario di inizio attività</th>
		    		<th class="text-center">Orario di fine attività</th>
		    		<th class="text-center">Ore di attivit&aacute svolte</th>
		    		<th class="text-center">Stato</th>
	    		</tr>
				<tr>
				 	<td><%= activity.getCategory() %></td>				 			
				 	<td><%= activity.getActivityDate() %></td>
				 	<td><%= activity.getStartTimeString() %></td>				 			
				 	<td><%= activity.getFinishTimeString() %></td>
				 	<td><%= activity.getHours() %></td>
				 	<td><%= activity.getState() %></td>
				</tr>			
			</table>
		</div>
				
		<div class="text-center" style="margin-bottom: 25px;">
			<a href="<%= response.encodeURL(request.getContextPath() +  "/ModifyActivity?cod=" + activity.getIdActivity()) %>"><input class="btn btn-primary" id="modificaActivityButton" type="button" value="ModificaActivity"></a>
		</div>
	</div>
		
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>
