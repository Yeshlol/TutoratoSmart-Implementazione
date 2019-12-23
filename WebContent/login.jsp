<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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


	<div id="content">
	
		<div id="loginFormPage">
			<h2><%= (msg!=null && !msg.equals("")) ? msg : "Login" %></h2>
			<form method="POST" action="<%= response.encodeURL("LoginServlet") %>">
				<input id="email" type="email" name="email" placeholder="Email" maxlength="15"><br>
				<input id="password" type="password" name="password" placeholder="Password" maxlength="10">
				<input id="login" type="submit" name="login" value="Login">			
			</form>
		</div>		
	
	</div>

</body>
</html>