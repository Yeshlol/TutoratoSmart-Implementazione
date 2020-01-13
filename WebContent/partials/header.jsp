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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="text-center">
		<% if (user != null) { %>
			<a href="<%=request.getContextPath() %>/home.jsp"><img src="<%=request.getContextPath() %>/img/LogoTutoratoSmart.png" style="border-radius:2px; height:20%; width:20%;"></a>
		<% } else { %>
	 		<a href="<%=request.getContextPath() %>/index.jsp"><img src="<%=request.getContextPath() %>/img/LogoTutoratoSmart.png" style="border-radius:2px; height:20%; width:20%;"></a>
		<% } %>
	</div>
	
	<nav class="navbar navbar-inverse" style="background:#232F3E;">
  		<div class="container-fluid">
    		<div class="navbar-header">
		    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
		        	<span class="icon-bar"></span>
		        	<span class="icon-bar"></span>
		        	<span class="icon-bar"></span>                        
		      	</button>
		      	
		      	<% if(user != null) { %>
		      		<a class="navbar-brand" style="font-weight:bold;border-right:1px solid #bbb; float:left;">Ciao, <%= user.getFirstName() %></a>
      			<% } %>
    		</div>
    		
		    <div class="collapse navbar-collapse" id="myNavbar">
		      <ul class="nav navbar-nav">
		        <li style="border-right:1px solid #bbb; float:left;"><a href="<%=request.getContextPath() %>/home.jsp" style="font-weight:bold;">Home</a></li>
		        
		        <% if(user != null) { %>
			        <% if(user.getRole()==1) { %>
			        	 <li class="nav-item" style="border-right:1px solid #bbb; float:left;">
				         	<a href="/TutoratoSmart/commission/tutorRegistration.jsp" style="font-weight:bold;">Registra Tutor</a>
				         </li>
				         <li class="nav-item" style="border-right:1px solid #bbb; float:left;">
				         	<a href="/TutoratoSmart/commission/searchTutors.jsp" style="font-weight:bold;">Ricerca Tutor</a>
				         </li>
				         <li class="nav-item">
				         	<a href="/TutoratoSmart/commission/searchStudents.jsp" style="font-weight:bold;">Ricerca Studenti</a>
				         </li>
			        <% } else if(user.getRole() == 2) { %>
			        	 <li class="nav-item" style="border-right:1px solid #bbb; float:left;">
				         	<li style="border-right:1px solid #bbb; float:left;"><a href="/TutoratoSmart/tutor/register.jsp" style="font-weight:bold;">Registro Tutorato</a>
				         </li>
				         
				         <li class="dropdown">
			          	 	<a class="dropdown-toggle" data-toggle="dropdown" style="border-right:1px solid #bbb;font-weight:bold;">Gestione Sportello<span class="caret"></span></a>
			          		<ul class="dropdown-menu">
			          			<li><a href="/TutoratoSmart/tutor/calendar.jsp" style="font-weight:bold;">Calendario Appuntamenti</a></li>
			            		<li><a href="/TutoratoSmart/tutor/requestsList.jsp" style="font-weight:bold;">Nuove Prenotazioni</a></li>
			            		<li><a href="/TutoratoSmart/tutor/acceptedRequestsList.jsp" style="font-weight:bold;">Richieste Accettate</a></li>
			            	</ul>
			       	 	 </li>
			       	 	 <li>
			       	 		<a href="/TutoratoSmart/tutor/appointmentsList.jsp" style="font-weight:bold;">Storico Appuntamenti</a>
			       	 	 </li>	         
			       	<% } else if(user.getRole() == 3) { %>
			        	 <li class="nav-item" style="border-right:1px solid #bbb; float:left;">
			        	 	<a href="/TutoratoSmart/student/calendar.jsp" style="font-weight:bold;">Orari Sportello</a>
			        	 </li>
			        	 <li class="nav-item" style="border-right:1px solid #bbb; float:left;">
			        	 	<a href="/TutoratoSmart/student/request.jsp" style="font-weight:bold;">Prenota Appuntamento</a>
			        	 </li>
			          	 <li>
			          	 	<a href="/TutoratoSmart/student/requestsList.jsp" style="font-weight:bold;">Storico Prenotazioni</a>
			          	 </li>
			        <% } %>
				</ul>
			    <ul class="nav navbar-nav navbar-right">
			       	<li>
			       		<form method="POST" action="/TutoratoSmart/LogoutServlet">
							<button type="submit" class="btn btn-default btn-sm navbar-right" style="margin-right:30px; margin-top:8px;"><span class="glyphicon glyphicon-log-out"></span>Log out</button>   		
				       	</form>
			       	</li>
			    </ul>
			  <% } else { %>	      
			      <ul class="nav navbar-nav navbar-right" style="float:right;">
			        <li><a href="/TutoratoSmart/registration.jsp"><span class="glyphicon glyphicon-user"></span> Registrati</a></li>
			        <li><a href="/TutoratoSmart/login.jsp"><span class="glyphicon glyphicon-log-in"></span>  Accedi</a></li>
			      </ul>
		      <% } %>
		  </div>
	  </div>
	</nav>
</body>
     