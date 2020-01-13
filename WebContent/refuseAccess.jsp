<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<% String msg = (String) session.getAttribute("msg"); %>
 
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Accesso Negato</title>
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	
	<div class="alert alert-danger">
		<h1 class="text-center">Hai tentato un accesso non consentito a pagine private.<br>Effettua l'<a href="login.jsp">accesso</a>.</h1>
	</div>							  
	
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>
