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
	<link rel="stylesheet" href="/TutoratoSmart/css/csspage.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<style>
		.navbar-nav > li > .dropdown-menu { background-color: #232F3E; }
		
		.navbar-default .navbar-nav>.open>a, .navbar-default .navbar-nav>.open>a:focus, .navbar-default .navbar-nav>.open>a:hover {
		    background-color: #CC0000;
		}
		.dropdown-menu > li > a:hover {
    		background-color: #CC0000;
 		}
 		
 		#myNavbar a:hover {
 			background-color: #CC0000;
 		}
	</style>
</head>

<body>
	<div class="text-center" id="header" style="margin-bottom:25px; width: 100%;">
		<% if (user != null) { %>
			<a href="<%=request.getContextPath() %>/View/home.jsp"><img src="<%=request.getContextPath() %>/img/LogoTutoratoSmart.png" style="border-radius:2px; height:20%; width:20%;"></a>
		<% } else { %>
	 		<a href="<%=request.getContextPath() %>/View/index.jsp"><img src="<%=request.getContextPath() %>/img/LogoTutoratoSmart.png" style="border-radius:2px; height:20%; width:20%;"></a>
		<% } %>
			
		<nav role="navigation" class="navbar navbar-default" style="background-color:#232F3E;">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" style="background-color:#232F3E;" data-toggle="collapse" data-target="#myNavbar" aria-expanded="false">
			      		<span class="sr-only">Opzioni</span>
			        	<span class="icon-bar"></span>
			        	<span class="icon-bar"></span>
			        	<span class="icon-bar"></span>
			      	</button>
			      	
					<% if (user != null) { %>
			    		<a class="navbar-brand" style="color: white;font-weight:bold;border-right:1px solid #bbb; float:left;">Ciao, <%= user.getFirstName() %></a>
					<% } else { %>
			    		<a class="navbar-brand" style="color: white;font-weight:bold; float:left;">Benvenuto </a>
					<% } %>
			    </div>
	    
			    <div class="collapse navbar-collapse" id="myNavbar">
			      	<% if(user != null) { %>
			      		<ul class="nav navbar-nav">
					        <li style="border-right:1px solid #bbb;">
					        	<a href="<%=request.getContextPath() %>/View/home.jsp" style="color: white; font-weight:bold;">Home</a>
					        </li>
					        
				        <% if(user.getRole()==1) { %>
				        	 <li style="border-right:1px solid #bbb;">
					         	<a href="/TutoratoSmart/View/commission/tutorRegistration.jsp" style="color: white; font-weight:bold;">Registra Tutor</a>
					         </li>
					         <li style="border-right:1px solid #bbb;">
					         	<a href="/TutoratoSmart/View/commission/searchTutors.jsp" style="color: white; font-weight:bold;">Ricerca Tutor</a>
					         </li>
					         <li>
					         	<a href="/TutoratoSmart/View/commission/searchStudents.jsp" style="color: white; font-weight:bold;">Ricerca Studenti</a>
					         </li>
				        <% } else if(user.getRole() == 2) { %>
				        	 <li style="border-right:1px solid #bbb;">
					         	<a href="/TutoratoSmart/View/tutor/register.jsp" style="color: white; font-weight:bold;">Registro Tutorato</a>
					         </li>
					         
					         <li class="dropdown">
				          	 	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" style="color: white; border-right:1px solid #bbb;font-weight:bold;">Gestione Sportello<span class="caret"></span></a>
				          		<ul class="dropdown-menu">
				          			<li class="text-center"><a href="/TutoratoSmart/View/tutor/calendar.jsp" style="color: white; font-weight:bold;">Calendario Appuntamenti</a></li>
				            		<li class="text-center"><a href="/TutoratoSmart/View/tutor/requestsList.jsp" style="color: white; font-weight:bold;">Nuove Prenotazioni</a></li>
				            		<li class="text-center"><a href="/TutoratoSmart/View/tutor/acceptedRequestsList.jsp" style="color: white; font-weight:bold;">Richieste Accettate</a></li>
				            	</ul>
				       	 	 </li>
				       	 	 <li>
				       	 		<a href="/TutoratoSmart/View/tutor/appointmentsList.jsp" style="color: white; font-weight:bold;">Storico Appuntamenti</a>
				       	 	 </li>	         
				       	<% } else if(user.getRole() == 3) { %>
				        	 <li style="border-right:1px solid #bbb;">
				        	 	<a href="/TutoratoSmart/View/student/calendar.jsp" style="background-color:#232F3E; color: white; font-weight:bold;">Orari Sportello</a>
				        	 </li>
				        	 <li style="border-right:1px solid #bbb;">
				        	 	<a href="/TutoratoSmart/View/student/request.jsp" style="background-color:#232F3E; color: white; font-weight:bold;">Prenota Appuntamento</a>
				        	 </li>
				          	 <li>
				          	 	<a href="/TutoratoSmart/View/student/requestsList.jsp" style="background-color:#232F3E; color: white; font-weight:bold;">Storico Prenotazioni</a>
				          	 </li>
				        <% } %>
					</ul>
				
				    <ul class="nav navbar-nav navbar-right">
				       	<li>
				       		<form method="POST" action="/TutoratoSmart/Logout">
								<button type="submit" class="btn btn-default btn-sm navbar-right" style="margin-right:20px; margin-top:8px;"><span class="glyphicon glyphicon-log-out"></span>Log out</button>   		
					       	</form>
				       	</li>
				    </ul>
			  <% } else { %>
			  		<ul class="nav navbar-nav navbar-right">
			        	<li><a href="/TutoratoSmart/View/registration.jsp" style="color: white; border-right:1px solid #bbb;"><span class="glyphicon glyphicon-user"></span> Registrati</a></li>
			        	<li><a href="/TutoratoSmart/View/login.jsp" style="color: white;"><span class="glyphicon glyphicon-log-in"></span>  Accedi</a></li>
			      	</ul>
		      <% } %>		  
		  		</div>
		  	</div>
		</nav>
	</div>	
</body>
     