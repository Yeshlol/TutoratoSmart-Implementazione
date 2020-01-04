<!DOCTYPE html>
<html>
<%@ page import="java.util.*,project.Model.ActivityTutorBean"%>
<head>
<meta charset="ISO-8859-1">
<title>Modifica attività lavorativa</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/csspage.css">
</head>
<body>
<%@ include file="/partials/header.jsp" %>
	
    
    
   <div class="form-general" style="margin-left:20px;">
   <h3>Modifica Dati Attività</h3>
   <form method="POST" action="<%= response.encodeURL("ModifyActivityServlet") %>">
       
        <h3>Inserisci i campi che si desiderano modificare:</h3>  
         
        Categoria <input type="text" class="form-control" name="categoria">
       
        <div class="form-group">
        Data	<input type="date"   class="form-control" name="data"  >
        </div>
        <div class="form-group">
        Ora di inizio	<input type="number" min="1" max="24" class="form-control" name="oraInizio" >
        </div>
		<div class="form-group">
        Ora di fine  <input type="number" min="1" max="23" class="form-control" name="oraFine" >
        </div>
		<div class="form-group">
        Ore di attività svolte  <input type="number" min="1" max="10" class="form-control" name="oreAttivitàSvolte">
        </div>
        <div class="form-group">
        Dettagli  <input type="text" class="form-control" name="dettagli">
        </div> 
        <input type = "hidden" name ="idActivity">          
        <input id="button" type="button" name="modificaButton" value="Modifica">
        </form> 
        </div>
     
      		
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>