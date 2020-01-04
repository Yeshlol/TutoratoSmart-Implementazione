<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<%@ page import="project.Model.UserBean" %>

<head>
	<meta charset="UTF-8">
	<title>Header</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/csspage.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mycss.css">
	<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="jumbotron" style="background-color:white; height:250px; padding-top: 0px; padding-bottom:0px;margin-left:0px;">
	   <a href="<%=request.getContextPath() %>/index.jsp"><img src="<%=request.getContextPath() %>/img/LogoTutoratoSmart.png" style="padding-left:40%;"></a>
	</div>
	 
	<% UserBean user = (UserBean) session.getAttribute("user"); %>
	          
	<%if(user != null) { %> 
	
	<nav class="navbar navbar-inverse" style="background:#232F3E;">
		<div class="container-fluid" style="border-right:1px solid #bbb;">
			<div class="navbar-header">
		        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>                        
		        </button>
			    <a class="navbar-brand" >Ciao, <%= user.getFirstName() %></a>
        	</div>
        	<div class="nav navbar-nav navbar-right">
				<form method="POST" action="LogoutServlet">
					<button type="submit" class="btn btn-default btn-sm" style="margin-right:30px; margin-top:8px;"><span class="glyphicon glyphicon-log-out"></span> Log out</button>   		
        		</form>
			</div>	
		               
		<% if (user.getRole()==1) { %>
	        <div class="collapse navbar-collapse" id="myNavbar">
        	<ul class="nav navbar-nav">
          		<li><a href="/TutoratoSmart/commission/tutorRegistration.jsp">Registra Tutor</a></li>
          		<li><a  href="/TutoratoSmart/commission/tutorsList.jsp">Visualizza lista Tutor</a></li>
          	</ul>
          	</div>     
		<% } else if(user.getRole()==2) { %>
	        <div class="collapse navbar-collapse" id="myNavbar">
        	<ul class="nav navbar-nav">
          		<li><a href="/TutoratoSmart/tutor/register.jsp" style="font-weight:bold;">Registro Tutorato</a></li>
          		<li><a href="/TutoratoSmart/tutor/calendar.jsp" style="font-weight:bold;">Calendario Appuntamenti</a></li>
          		</ul>
          	</div>
	    <% } else if(user.getRole()==3) { %>
	      
	      
		<% } %>
		</div>
	</nav>
		 
	<%} else { %>
		<nav class="navbar navbar-inverse" style="background:#232F3E;">
			<div class="container-fluid" style="border-right:1px solid #bbb; float:left;">
				<div class="navbar-header">
			        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
			          <span class="icon-bar"></span>
			          <span class="icon-bar"></span>
			          <span class="icon-bar"></span>                        
			        </button>
			        <a class="navbar-brand" style="font-weight:bold;" href="<%= response.encodeURL(request.getContextPath() + "/login.jsp") %>">Effettua l'accesso</a>
				</div>
			</div>
		</nav>
	<% } %>
</body>
     