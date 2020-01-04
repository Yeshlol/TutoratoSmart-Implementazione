<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.Calendar,project.Model.RequestBean"%>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>Calendario Appuntamenti</title>
	<link href='/TutoratoSmart/fullcalendar/core/main.css' rel='stylesheet' />
    <link href='/TutoratoSmart/fullcalendar/daygrid/main.css' rel='stylesheet' />
    <link href='/TutoratoSmart/fullcalendar/timegrid/main.css' rel='stylesheet' />
	<link href='/TutoratoSmart/fullcalendar/list/main.css' rel='stylesheet' />	
		
    <script src='/TutoratoSmart/fullcalendar/core/main.js'></script>
    <script src='/TutoratoSmart/fullcalendar/daygrid/main.js'></script>
    <script src='/TutoratoSmart/fullcalendar/timegrid/main.js'></script>
    <script src='/TutoratoSmart/fullcalendar/list/main.js'></script>  
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	
	<div id='calendar' style="width: 65%; margin: 0 auto;"></div>
	
	<div id='info' style="position: absolute;z-index:999;"></div>
	
	<%@ include file="/partials/footer.jsp" %>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script src="/TutoratoSmart/js/calendarScript.js"></script>
</body>
</html>