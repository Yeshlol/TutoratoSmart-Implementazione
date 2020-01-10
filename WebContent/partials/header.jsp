<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% UserBean user = (UserBean) session.getAttribute("user"); %>

<!DOCTYPE html>
<html>

<%@ page import="project.Model.UserBean" %>

<head>
	<meta charset="UTF-8">
	<title>Header</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/csspage.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mycss.css">
	<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="text-center">
  		<a href="<%=request.getContextPath() %>/index.jsp"><img src="<%=request.getContextPath() %>/img/LogoTutoratoSmart.png" style="border-radius:2px; height:20%; width:20%;"></a>
	</div>
	          
	<%if(user != null) { %> 
	
	<nav class="navbar navbar-inverse" style="background:#232F3E;">
		<div class="container-fluid" style="border-right:1px solid #bbb;">
			<div class="navbar-header">
		        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar"></button>
			    <a class="navbar-brand" style="border-right:1px solid #bbb; float:left;">Ciao, <%= user.getFirstName() %></a>
			</div>
        	<div class="nav navbar-nav navbar-right">
				<form method="POST" action="/TutoratoSmart/LogoutServlet">
					<button type="submit" class="btn btn-default btn-sm" style="margin-right:30px; margin-top:8px;"><span class="glyphicon glyphicon-log-out"></span>Log out</button>   		
        		</form>
			</div>	
		               
		<% if (user.getRole()==1) { %>
	        <div class="collapse navbar-collapse" id="myNavbar">
	        	<ul class="nav navbar-nav">
	          		<li style="border-right:1px solid #bbb; float:left;"><a href="/TutoratoSmart/commission/tutorRegistration.jsp" style="font-weight:bold;">Registra Tutor</a></li>
	          		<li style="border-right:1px solid #bbb; float:left;"><a href="/TutoratoSmart/commission/searchTutors.jsp" style="font-weight:bold;">Ricerca Tutor</a></li>
	          		<li><a href="/TutoratoSmart/commission/searchStudents.jsp" style="font-weight:bold;">Ricerca Studenti</a>
	          	</ul>
          	</div>     
		<% } else if(user.getRole()==2) { %>
	        <div class="collapse navbar-collapse" id="myNavbar">
	        	<ul class="nav navbar-nav">
	          		<li style="border-right:1px solid #bbb; float:left;"><a href="/TutoratoSmart/tutor/register.jsp" style="font-weight:bold;">Registro Tutorato</a></li>
	          		<li><a href="/TutoratoSmart/tutor/calendar.jsp" style="font-weight:bold;">Calendario Appuntamenti</a></li>
	          	</ul>
          	</div>
	    <% } else if(user.getRole()==3) { %>
	         <div class="collapse navbar-collapse" id="myNavbar">
        		<ul class="nav navbar-nav">
        			<li style="border-right:1px solid #bbb; float:left;"><a href="/TutoratoSmart/student/calendar.jsp" style="font-weight:bold;">Orari Sportello</a></li>
        			<li style="border-right:1px solid #bbb; float:left;"><a href="/TutoratoSmart/student/request.jsp" style="font-weight:bold;">Prenota Appuntamento</a></li>
          			<li><a href="/TutoratoSmart/student/requestsList.jsp" style="font-weight:bold;">Storico Prenotazioni</a></li>
          		</ul>
          	</div>
	      
		<% } %>
		</div>
	</nav>
		 
	<%} else { %>
		<nav class="navbar navbar-inverse" style="background:#232F3E;">
			<div class="container-fluid" style="border-right:1px solid #bbb; float:left;">
				<div class="navbar-header">
			        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar"></button>
			        <a class="navbar-brand" style="font-weight:bold;" href="<%= response.encodeURL(request.getContextPath() + "/index.jsp") %>">Effettua l'accesso</a>
				</div>
			</div>
		</nav>
	<% } %>
</body>
     