<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Login</title>
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	
	<div class="content text-center" style="width: 50%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2 class="text-center">Login</h2>
			<p class="text-center">Compilare email e password per accedere</p>
		</div>
		
		<div id="loginForm" class="container-fluid" style="margin: 25px;">
			<form method="POST" action="<%= response.encodeURL("Login") %>">
				<div class="row row-space">
					<div class="row">
				  		<div  class="form-group col-lg-6">
				  			<label for="email" class="control-label">Inserire l'email</label>
							<input type="email" class="form-control" id="email" name="email" placeholder="Email" maxlength="45" onkeydown="lowerCaseF(this)">
						</div>
						
						<div class="form-group col-lg-6">	
							<label for="password" class="control-label">Inserire la password</label>
							<input type="password" style="height: 35px;" class="form-control" id="password" name="password" placeholder="Password" maxlength="10">
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-lg-6">
							<div class="alert alert-warning" id="errorEmail" role="alert" style="display:none;">
								<strong>Errore!</strong> Formato email non valido! (<i>Es: mariorossi@studenti.unicampania.it</i>)
							</div>
						</div>
						
						<div class="form-group col-lg-6">
							<div class="alert alert-warning" id="errorPassword" role="alert" style="display:none;">
								<strong>Errore!</strong> Formato password non valido!
							</div>
						</div>
					</div>
				</div>	
				
				<div >		        	
		        	<button type="submit" class="btn btn-primary btn-md btn-block" onclick="validateInputs()">Accedi</button>
		        </div>				
			</form>
		</div>
	</div>								  
	
	<%@ include file="/partials/footer.jsp" %>
	
	<script>
		function validateInputs() {
			var valid = true;
			
			var email = $("#email");
			var password = $("#password");
			
			if (email.val() == "" || password.val() == "")
				return;
			
			if (!validateEmail(email)){
				valid = false;
				$("#errorEmail").fadeIn(500, function() {
					$("#errorEmail").fadeOut(4000);
				})
			}
			
			if (!validatePassword(password)){
				valid = false;
				$("#errorPassword").fadeIn(500, function() {
					$("#errorPassword").fadeOut(4000);
				})
			}
			
			if (valid)
				$("#loginForm form").submit();
		}
	</script>
	<script src="<%= request.getContextPath() %>/js/validationScript.js"></script>
</body>
</html>
