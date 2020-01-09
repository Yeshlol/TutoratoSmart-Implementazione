<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%  RequestBean req = (RequestBean) session.getAttribute("request"); %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.RequestBean"%>

<head>
	<meta charset="UTF-8">
	<title>Modifica Prenotazione</title>
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	
	<div class="content text-center" style="width: 50%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2 align="center">Modifica dati prenotazione</h2>
		</div>
		
		<div class="container-fluid" style="margin: 25px;">
			<form method="POST" action="<%= response.encodeURL("/TutoratoSmart/Request") %>">
				<div class="row row-space">
					<div class="row">
						<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
				  			<label for="data" class="control-label">Selezionare la data</label>
				  			<input type="date" class="form-control" name="date">
						</div>
						<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
				  			<label for="date" class="control-label">Selezionare l'orario</label>
				  			<input type="time" min="09:00" max="16:45" class="form-control" name="startTime" step="900">
				  		</div>
				  	</div>
				</div>	
									
		  		<label for="comment" class="control-label">Commento</label>
		  		<textarea class="form-control" id="description" name="description" rows="3"></textarea>
		  		<br>
								
				<div class="panel"></div>
				<div>
					<input class="btn btn-primary" id="modifyActivity" type="submit" onclick="validateInputsRequest()" name="modifyActivity" value="Modifica">		        	
		        </div>			
			</form>
		</div>
	</div>

	<%@ include file="/partials/footer.jsp" %>
</body>
</html>