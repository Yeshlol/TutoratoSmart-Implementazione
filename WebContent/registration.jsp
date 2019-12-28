<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registrazione Studente</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/csspage.css">    
</head>

<body>
	<%@ include file="/partials/header.jsp" %>

	<div class="sidebar-page-container basePage signUpPage">
		<div class="auto-container">
			<div class="row clearfix">
				<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="content">
						<div class="news-block-seven">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 signUp-container" style="margin-left:350px; border: 2px solid #232F3E; border-radius:8px; margin-top:50px; ">
								<div class="panel">
									<h2 class="text-center">Registrazione</h2>
									<p class="text-center">Compila tutti i campi per registrarti</p>
								</div>
								<form method="POST" action="<%= response.encodeURL("/TutoratoSmart/Registration") %>">
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<input type="email" class="form-control" id="Email" placeholder="Email" maxlength="45" onkeydown="lowerCaseF(this)" required>
									</div>	
										
							        <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<input type="password" class="form-control" id="Password" placeholder="Password" name="Password" maxlength="10" required>
									</div>		
									
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<input type="text" class="form-control" id="FirstName" name="FirstName" placeholder="Nome" maxlength="20" required>
									</div>		
					                
					                <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<input class="form-control" id="LastName" type="text" name="LastName" placeholder="Cognome" maxlength="20" required> 
					                </div>
					                
					                <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<input class="form-control" id="TelephoneNumber" type="tel" name="TelephoneNumber" maxlength="10" placeholder="Numero di Telefono" required>
									</div>
									
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<input class="form-control" id="RegistrationNumber" type="text" name="RegistrationNumber" maxlength="10" placeholder="Matricola" required>
									</div>
									
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<label class="radio-inline"> <input type="radio" name="Sex" id="M" value="M"> M </label>
										<label	class="radio-inline"> <input type="radio" name="Sex" id="F" value="F"> F </label>
									</div>
					                								
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<label for="AcademicYear">Anno Accademico: </label>
										<select id="AcademicYear" name="AcademicYear">
											<option value="1">Primo Anno - Triennale</option>
											<option value="2">Secondo Anno - Triennale</option>
											<option value="3">Terzo Anno - Triennale</option>
											<option value="4">Primo Anno - Magistrale</option>
											<option value="5">Secondo Anno - Magistrale</option>
										</select>
									</div>
									
					 				<div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12" >
					 					<input id="registra" type="submit" name="registraButton" value="Registra">
									</div>
									<div class="clearfix"></div>
								</form>	
		          			</div>
              			</div>
          			</div>
        		</div>
      		</div>
		</div>
	</div>
	
	<%@ include file="/partials/footer.jsp" %>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script src="../js/validationScript.js"></script>
	<script src="../js/registrationScript.js"></script>
</body>
</html>
		
