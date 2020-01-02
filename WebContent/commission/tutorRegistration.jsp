<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Registrazione Tutor</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/TutoratoSmart/css/csspage.css"> 
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
									<h2 class="text-center">Registrazione Tutor</h2>
									<p class="text-center">Compila tutti i campi per registrarti</p>
								</div>
								<div id="registerForm">
									<form method="POST" action="<%= response.encodeURL("/TutoratoSmart/Registration") %>">
										<div class="alert alert-warning" id="errorDiv" role="alert" style="display:none;"></div>
										
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="email" class="form-control" id="Email" name="Email" placeholder="Email" maxlength="45" onkeydown="lowerCaseF(this)">
										</div>
										
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input class="form-control" id="RegistrationNumber" type="text" name="RegistrationNumber" maxlength="10" placeholder="Matricola" onkeydown="upperCaseF(this)">
										</div>
																					
								        <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="password" class="form-control" id="Password" placeholder="Password" name="Password" maxlength="10">
										</div>
										
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="password" class="form-control" id="VerifyPassword" placeholder="ConfermaPassword" name="VerifyPassword" maxlength="10">
										</div>		
										
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input type="text" class="form-control" id="FirstName" name="FirstName" placeholder="Nome" maxlength="30">
										</div>		
						                
						                <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input class="form-control" id="LastName" type="text" name="LastName" placeholder="Cognome" maxlength="30"> 
						                </div>
						                
						                <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<input class="form-control" id="TelephoneNumber" type="text" name="TelephoneNumber" maxlength="10" placeholder="Numero di Telefono">
										</div>
						                
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label class="radio-inline"> <input type="radio" name="Sex" id="M" value="M" checked> M </label>
											<label class="radio-inline"> <input type="radio" name="Sex" id="F" value="F"> F </label>
										</div>
										
						                <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">Ore contrattuali:
											<input class="form-control" id="TotalHours" type="number" name="TotalHours" min="10" placeholder="Ore del Contratto">
										</div>
						                						                						                
						                <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">Data di inizio contratto: 
											<input class="form-control" id="StartDate" type="date" name="StartDate">
										</div>
										
						 				<div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12" >
						 					<input id="registra" type="button" onclick="validateInputsTutor()" name="registraButton" value="Registra">
										</div>										
										<div class="clearfix"></div>
									</form>
									
									<div class="alert alert-success" id="successDiv" role="alert" style="display:none;">Registrazione completata con successo!</div>
									
									<div class="alert alert-danger" id="failureDiv" role="alert" style="display:none;">Registrazione fallita!</div>									
								</div>
		          			</div>
              			</div>
          			</div>
        		</div>
      		</div>
		</div>
	</div>
	
	<%@ include file="/partials/footer.jsp" %>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script src="<%= request.getContextPath() %>/js/validationScript.js"></script>
	<script src="<%= request.getContextPath() %>/js/registrationScript.js"></script>
	<script type="text/javascript">
		$(document).ready( function() {
			document.getElementById("StartDate").valueAsDate = new Date();
		});
	</script>		
</body>
</html>
