<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
<div class="sidebar-page-container basePage loginPage">
	<div class="auto-container">
		<div class="row clearfix">
			<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="content">
					<div class="news-block-seven">
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 login-container" style="margin-left:350px; border: 2px solid #232F3E; border-radius:8px; margin-top:50px;">
							<div class="panel">
								<h2 class="text-center">Login</h2>
								<p class="text-center">Compilare email e password per accedere</p>
							</div>
							<div id="loginFormPage">
							    <form method="POST" action="<%= response.encodeURL("LoginServlet") %>">
								    <div class="form-group">
									<input class="form-control" id="email" type="email" name="email" placeholder="Email" maxlength="45" onkeydown="lowerCaseF(this)" required>
									</div>
									<div class="alert alert-warning" id="errorEmail" role="alert" style="display:none;">
										<strong>Errore!</strong> Formato email non valido! (<i>Es: mariorossi@studenti.unicampania.it</i>)
									</div>
									
									<div class="form-group">
									<input class="form-control" id="password" type="password" name="password" placeholder="Password" maxlength="10" required>
									</div>
									<div class="alert alert-warning" id="errorPassword" role="alert" style="display:none;">
										<strong>Errore!</strong> Formato password non valido!
									</div>
									
									<div class="form-group">
									<input class="form-control" id="login" type="submit" name="login" value="Login">
									</div>	
								</form>
							</div>
        				</div>			
       				</div>
     			</div>
   			</div>
 		</div>
	</div>
</div>	    
	
	<%@ include file="/partials/footer.jsp" %>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script src="<%= request.getContextPath() %>/js/loginScript.js"></script>
	<script src="<%= request.getContextPath() %>/js/validationScript.js"></script>
	
</body>
</html>