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
</head>

<body>
	<div class="jumbotron" style="background-color:white; height:250px; padding-top: 0px; padding-bottom:0px;margin-left:0px;">
	   <a href="<%=request.getContextPath() %>/index.jsp"><img src="<%=request.getContextPath() %>/img/LogoTutoratoSmart.png" style="padding-left:40%;"></a>
	   <div class="container text-center">
	   </div>
	</div>
	 
	<% UserBean user = (UserBean) session.getAttribute("user"); %>
	          
	<%if(user != null) { %>               
		<% if (user.getRole()==1) { %>
	        
	           
		<% } else if(user.getRole()==2) { %>
	        
	    
		<% } else if(user.getRole()==3) { %>
	      
	      
		<% } %>
		
		<nav class="navbar navbar-inverse" style="background:#232F3E;">
			<div class="container-fluid" style="border-right:1px solid #bbb; float:left;">
				<div class="navbar-header">
			        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
			          <span class="icon-bar"></span>
			          <span class="icon-bar"></span>
			          <span class="icon-bar"></span>                        
			        </button>
			        <a class="navbar-brand" style="font-weight:bold;">Ciao, <%= user.getFirstName() %></a>
				</div>
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
			        <a class="navbar-brand" style="font-weight:bold;" href="<%= response.encodeURL(request.getContextPath() + "/TutoratoSmart/login.jsp") %>">Effettua l'accesso</a>
				</div>
			</div>
		</nav>
	<% } %>
</body>
     