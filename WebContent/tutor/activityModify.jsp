<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  ActivityTutorBean activity = (ActivityTutorBean) request.getAttribute("activity"); %>
<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.ActivityTutorBean"%>

<head>
	<meta charset="UTF-8">
	<title>Modifica Attivit&aacute Lavorativa</title>
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	    
   	<div class="form-general" style="width: 70%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
   	<h3 align="center">Modifica Dati Attivit&aacute</h3>
   
   <form method="POST" action="<%= response.encodeURL(request.getContextPath() + "/ModifyActivity")%>">
       
        <h3 align="center">Inserisci i campi che si desiderano modificare:</h3>  
        <div class="panel" style="margin: 25px;">
       <div>Categoria<br>
       <select id="categoria" name="categoria">
									<option value="1">Sportello informativo</option>
									<option value="2">Assistenza esame</option>
									<option value="3">Organizzazione seminario</option>
									<option value="4">Seminario</option>
									<option value="5">Organizzazione evento</option>
									<option value="6">Evento</option>
								</select>
					</div>
					<br>		
       
        <div class="form-group">Data
        	<input type="date"   class="form-control" name="data" value="<%= activity.getActivityDate() %> " >
        </div>
        
        <div class="form-group">Ora di inizio
        	<input type="number" min="1" max="24" class="form-control" name="oraInizio" value="<%= activity.getStartTime() %>" >
        </div>
        
		<div class="form-group">Ora di fine
			<input type="number" min="1" max="23" class="form-control" name="oraFine" value="<%= activity.getFinishTime() %>" >
        </div>
        
        <div class="form-group">Ore svolte
			<input type="number" min="1" max="23" class="form-control" name="oreSvolte" value="<%= activity.getHours() %>" >
        </div>
        
        <div class="form-group">Dettagli
        	<input type="text" class="form-control" name="dettagli" value="<%= activity.getDetails() %>" >
        </div>
         
        <input type = "hidden" name ="idActivity" value="<%= activity.getIdActivity() %>">          
        <input id="button" type="submit" name="modificaButton" value="Modifica">
        </div>
        
	</form> 
	</div>     
      		
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>