<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <% 
 	String msg = (String) session.getAttribute("msg"); 
 
 %>
    
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>Login</title>

</head>

<body>
	<%@ include file="header.jsp" %>

	<div id="content">
	
		<div id="loginFormPage">
			<h2><%= (msg!=null && !msg.equals("")) ? msg : "Login" %></h2>
			<form method="POST" action="<%= response.encodeURL("LoginServlet") %>">
				<input id="email" type="email" name="email" placeholder="Email" maxlength="45" size="30"><br>
				<input id="password" type="password" name="password" placeholder="Password" maxlength="10">
				<input id="login" type="submit" name="login" value="Login">			
			</form>
		</div>		
	
	</div>
	
	<%@ include file="footer.jsp" %>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script src="<%= request.getContextPath() %>/js/loginScript.js"></script>
	<script src="<%= request.getContextPath() %>/js/validationScript.js"></script>
	
</body>
</html>