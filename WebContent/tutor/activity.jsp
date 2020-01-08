<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.TutorDAO,project.Model.TutorBean,project.Model.RegisterDAO,project.Model.RegisterBean" %>

<head>
	<meta charset="UTF-8">
	<title>Aggiunta attivit&aacute lavorativa</title>
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	
	<div class="content text-center" style="width: 50%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2>Aggiunta attivit&aacute lavorativa</h2>
			<p>Compila tutti i campi relativi all'attivit&aacute svolta</p>
		</div>
		
		<div class="container-fluid" style="margin: 25px;">
			<form method="POST" action="<%= response.encodeURL("/TutoratoSmart/Activity") %>">
				<div class="row row-space">
					<div class="row">
						<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
				  			<label for="category" class="control-label">Specificare la categoria</label>
							<select id="category" name="category">
								<option value="1">Sportello informativo</option>
								<option value="2">Assistenza esame</option>
								<option value="3">Organizzazione seminario</option>
								<option value="3">Seminario</option>
								<option value="3">Organizzazione evento</option>
								<option value="3">Evento</option>
							</select>
						</div>
						<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
				  			<label for="date" class="control-label">Specificare la data</label>
				  			<input type="date" class="form-control" name="date">
				  		</div>
				  	</div>
				  	
				  	<div class="row">
				  		<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
				  			<label for="startTime" class="control-label">Specificare l'orario di inizio attivit&aacute</label>
				  			<input type="time" min="7:30" max="22:00" class="form-control" name="startTime">
				  		</div>
				  		<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
				  			<label for="finishTime" class="control-label">Specificare l'orario di fine attivit&aacute</label>
				  			<input type="time" min="7:30" max="22:00" class="form-control" name="finishTime">
				  		</div>
				  	</div>
				</div>	
									
		  		<label for="description" class="control-label">Inserire la descrizione dell'attivit&aacute</label>
		  		<textarea class="form-control" id="description" name="description" rows="3"></textarea>
		  		<br>
								
				<div class="panel"></div>
				<div>
					<input class="btn btn-primary" id="addActivity" type="submit" onclick="validateInputsActivity()" name="addActivity" value="Aggiungi">		        	
		        </div>
		        <% 	
		        	TutorDAO tutorDAO = new TutorDAO();
		        	TutorBean tutor = tutorDAO.doRetrieveByMail(user.getEmail());
					RegisterDAO registerDAO = new RegisterDAO();
					RegisterBean register = registerDAO.doRetrieveById(tutor.getRegisterId());
				%>
				<input type="hidden" name="flag" id="flag" value="1">
				<input type="hidden" name="email" id="email" value="<%= tutor.getEmail() %>">
				<input type="hidden" name="registerId" id="registerId" value="<%= register.getIdRegister() %>">
			</form>
		</div>
	</div>
         
    <input type="hidden" name="inserimento" value="true">
    <input type="hidden" name="idRegistro">          
     
	<!--     AGGIUNGERE SCRIPT VALIDAZIONE QUI -->
     	
	<%@ include file="/partials/footer.jsp" %>
</body>
</html>