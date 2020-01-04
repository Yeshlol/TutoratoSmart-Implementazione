<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.ActivityTutorBean"%>

<head>
	<meta charset="UTF-8">
	<title>Modifica Attivit&aacute Lavorativa</title>
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	    
   	<div class="form-general" style="margin-left:20px;margin-right:20px;">
   	<h3 align="center">Modifica Dati Attivit&aacute</h3>
   
   	<form method="POST" action="<%= response.encodeURL("ModifyActivityServlet") %>">
       
        <h3 align="center">Inserisci i campi che si desiderano modificare:</h3>  
         
        Categoria <input type="text" class="form-control" name="categoria">
       
        <div class="form-group">Data
        	<input type="date"   class="form-control" name="data">
        </div>
        
        <div class="form-group">Ora di inizio
        	<input type="number" min="1" max="24" class="form-control" name="oraInizio" >
        </div>
        
		<div class="form-group">Ora di fine
			<input type="number" min="1" max="23" class="form-control" name="oraFine" >
        </div>
        
        <div class="form-group">Dettagli
        	<input type="text" class="form-control" name="dettagli">
        </div>
         
        <input type = "hidden" name ="idActivity">          
        <input id="button" type="button" name="modificaButton" value="Modifica">
	</form> 
	</div>     
      		
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>