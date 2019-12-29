<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="css/csspage.css">
<link rel="stylesheet" href="css/mycss.css">
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
<%@ page import="project.Model.UserBean" %>
<div class="jumbotron" style="background-color:white; height:250px; padding-top: 0px; padding-bottom:0px;margin-left:0px;">
   <a href="index.jsp"><img src="img/LogoTutoratoSmart.png" style="padding-left:40%;"></a>
    <div class="container text-center">
    </div>
</div>

              <nav class="navbar navbar-inverse" style="background:#232F3E;">
	<div class="container-fluid" style="border-right:1px solid #bbb; float:left;">
		<div class="navbar-header">
	        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	          <span class="icon-bar"></span>
	          <span class="icon-bar"></span>
	          <span class="icon-bar"></span>                        
	        </button>
	        <a class="navbar-brand" href="index.jsp" style="font-weight:bold;">Benvenuto in TutoratoSmart!</a>
		</div>
	</div>
</nav> 
 
        <% UserBean user= (UserBean) session.getAttribute("user"); %>
          
        <%if(user!=null){ %>
       
               
            <nav class="navbar navbar-inverse" style="background:#232F3E;">
	<div class="container-fluid" style="border-right:1px solid #bbb; float:left;">
		<div class="navbar-header">
	        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	          <span class="icon-bar"></span>
	          <span class="icon-bar"></span>
	          <span class="icon-bar"></span>                        
	        </button>
	        <a class="navbar-brand" href="index.jsp" style="font-weight:bold;">Benvenuto <%= user.getFirstName() %></a>
		</div>
	</div>
</nav>
 
     
      <%if(user.getRole()==1) { %>
     
        
        
      <% } %>  
     
     
      <%if(user.getRole()==2){ %>
     
     
     
      <% } %>
       
     
      <%if(user.getRole()==3){ %>
       
       
      <% } %>
      
       
      <% } %>
     