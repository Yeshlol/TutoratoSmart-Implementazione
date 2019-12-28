<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Richiesta appuntamento</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/csspage.css">
</head>
<body>
          <%@ include file="/partials/header.jsp" %>
          
          <h1>Richiesta di appuntamento</h1>
          
           <div style="margin-left:120px;">Inserisci la data di prenotazione:<input type="date" name="dateAppointment"></div><br><br>
                      
           <div style="margin-left:120px;">Inserisci la data fine attività:<input type="date" name="finishActivity"></div><br><br> 
                      
           <input type="submit" style="margin-left:120px;">
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          <%@ include file="/partials/footer.jsp" %>  
</body>
</html>