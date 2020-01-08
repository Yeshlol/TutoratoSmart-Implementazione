<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% RequestBean req = (RequestBean) request.getAttribute("request"); %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.RequestBean"%>

<head>
	<meta charset="UTF-8">
	<title>Dettagli Richiesta n <%= req.getIdRequest() %> </title>
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>