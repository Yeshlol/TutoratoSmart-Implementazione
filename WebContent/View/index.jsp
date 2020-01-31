<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	
<head>
	<meta charset="UTF-8">
	<title>TutoratoSmart</title>
	<link rel="shortcut icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon">
	<link rel="icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon">
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
		
	<div class="content text-center" style="width: 40%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2>Benvenuto in TutoratoSmart</h2>
			<p>Effettua l'accesso oppure registrati alla piattaforma</p>
		</div>
		
		<div class="container-fluid" style="margin: 25px;">
			<div class="row row-space">
				<div class="row">
					<div style="margin-bottom: 25px;">	
						<a href="/TutoratoSmart/View/login.jsp" class="btn btn-primary btn-md btn-block" role="button" aria-pressed="true">Accedi</a>
					</div>
					<div>	
						<a href="/TutoratoSmart/View/registration.jsp" class="btn btn-primary btn-md btn-block" role="button" aria-pressed="true">Registrati</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/partials/footer.jsp" %>	
</body>
</html>