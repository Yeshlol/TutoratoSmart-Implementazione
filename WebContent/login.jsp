<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<% String msg = (String) session.getAttribute("msg"); %>
 
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="shortcut icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon">
	<link rel="icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon">
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	
	<div class="content text-center" style="width:50%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2 class="text-center"><%= (msg!=null && !msg.equals("")) ? msg : "Login" %></h2>
			<p class="text-center">Compilare email e password per accedere</p>
		</div>
		
		<div id="loginForm" class="container-fluid" style="margin: 25px; margin-bottom: 0px;">
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
						<div class="alert alert-warning" id="errorDiv" role="alert" style="display:none;"></div>
					</div>
				</div>	
			</form>
		</div>
		
		<div class="row">
			<div class="alert alert-success text-center" id="successDiv" role="alert" style="display:none;margin:25px;margin-top:0px;">Autenticazione riuscita</div>
			<div class="alert alert-danger" id="failureDiv" role="alert" style="display:none;margin:25px;margin-top:0px;">Autenticazione fallita</div>		
		</div>				
				
		<div class="panel" style="margin-top: 0px"></div>
		<div class="row text-center" style="margin-bottom: 25px;">
        	<button type="button" class="btn btn-primary" id="login" onclick="validateInputs()">Accedi</button>
        </div>
	</div>								  
	
	<%@ include file="/partials/footer.jsp" %>
	
	<script src="<%= request.getContextPath() %>/js/validationScript.js"></script>
	<script>
		$(document).keypress(function(event){
		    var keycode = (event.keyCode ? event.keyCode : event.which);
		    if(keycode == '13'){
		        $("#login").click();
		    }
		});
		
		function validateInputs() {
			$("#errorDiv").hide();
			$("#errorDiv").html("");
			
			$('#login').attr('disabled','disabled');
			
			var valid = true;
			var errorMessage = "";
						
			var email = $("#email");
			var password = $("#password");
			
			if (email.val() == "") {
				valid = false;
				errorMessage += "<strong>Inserire l'email</strong><br>";
			}
			
			if (password.val() == "") {
				valid = false;
				errorMessage += "<strong>Inserire la password</strong><br>";
			}
			
			if (email.val() != "" && !validateEmail(email)){
				valid = false;
				errorMessage += "<strong>Formato email non valido</strong> (<i>Es: mrossi@studenti.unicampania.it</i>)<br>";
			}
			
			if (password.val() != "" && !validatePassword(password)){
				valid = false;
				errorMessage += "<strong>Formato password non valido</strong>";
			}
			
			if (valid) {		
				$.post("/TutoratoSmart/Login", {
					"email":$("#email").val(),
					"password":$("#password").val(),
					},
					function(data){	  
						if (data.result == 1) {
							$("#successDiv").fadeIn(500, function() {
								$("#successDiv").fadeOut(3000);
								setTimeout(function() {
									  window.location.href = "/TutoratoSmart/home.jsp";
								}, 3000);
							})
						}
						else {
							$("#failureDiv").fadeIn(500, function() {
								$("#failureDiv").fadeOut(5000);
								$('#login').prop("disabled", false);
							})
						}					 
					});		
			}
			else {
				$("#errorDiv").append(errorMessage);
				$("#errorDiv").show();
				setTimeout(function() {
					$("#errorDiv").hide();
					$('#login').prop("disabled", false);
				}, 4500);
			}
		}
	</script>
</body>
</html>
