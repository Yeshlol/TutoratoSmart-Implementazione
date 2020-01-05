<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiungi attività lavorativa</title>
</head>
<body>

<%@ include file="/partials/header.jsp" %>
	    
   	<div class="form-general" style="width: 70%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
   	<h3 align="center">Aggiungi attivit&aacute lavorativa</h3>
   
   	<form method="POST" action="AddActivityServlet">
       
        <h3 align="center">Inserisci i campi:</h3>  
        <div class="panel" style=" margin:25px;"> 
       <div class="form-group">Categoria  
       <label for="categoria"></label>
					<select id="categoria" name="categoria">
						<option value="1">Sportello informativo</option>
						<option value="2">Assistenza esame</option>
						<option value="3">Organizzazione seminario</option>
						<option value="3">Seminario</option>
						<option value="3">Organizzazione evento</option>
						<option value="3">Evento</option>
					</select>
       </div> 
        <div class="form-group">Data
        	<input type="date"   class="form-control" name="data">
        </div>
        
        <div class="form-group">Ora di inizio
        	<input type="number" min="1" max="24" class="form-control" name="oraInizio" >
        </div>
        
		<div class="form-group">Ora di fine
			<input type="number" min="1" max="23" class="form-control" name="oraFine" >
        </div>
        
        <div class="form-group">Ore svolte
			<input type="number" min="1" max="23" class="form-control" name="oreSvolte" >
        </div>
        
        <div class="form-group">Dettagli
        	<input type="text" class="form-control" name="dettagli">
        </div>
         
         
        <input type ="hidden" name ="idActivity">
        <input type="hidden" name="idRegistro">         
        <button type="submit" class="btn btn-primary" style="margin-left:400px;">Aggiungi</button>
        </div>
	</form> 
	
	</div>     
      		
	<%@ include file="/partials/footer.jsp" %>
	
	


</body>
</html>