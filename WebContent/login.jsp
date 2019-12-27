<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
     <link rel="stylesheet" href="css/csspage.css">

</head>

<body>
	<%@ include file="header.jsp" %>
<div class="sidebar-page-container basePage loginPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">

								<div
									class="col-lg-6 col-md-6 col-sm-12 col-xs-12 login-container" style="margin-left:350px; border: 2px solid #232F3E; border-radius:8px; margin-top:50px;">
									<div class="panel">
										<h2 class="text-center">Login</h2>
										<p class="text-center">Compilare username e password per
											accedere</p>
									</div>
			    <form method="POST" action="<%= response.encodeURL("LoginServlet") %>">
			    <div class="form-group">
				<input class="form-control" id="email" type="email" name="email" placeholder="Email" maxlength="45" size="30">
				</div>
				<div class="form-group">
				<input class="form-control" id="password" type="password" name="password" placeholder="Password" maxlength="10">
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
	
	<%@ include file="footer.jsp" %>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script src="<%= request.getContextPath() %>/js/loginScript.js"></script>
	<script src="<%= request.getContextPath() %>/js/validationScript.js"></script>
	
</body>
</html>