<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%  RequestBean req = (RequestBean) session.getAttribute("request"); %>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Utils.Utils,project.Model.RequestBean"%>

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
			<div class="row row-space">
				<div class="row">
					<div class="alert alert-warning" id="errorDiv" role="alert" style="display:none;"></div>
				</div>
				
				<div class="row">
					<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
			  			<label for="requestDateM" class="control-label">Selezionare la data</label>
			  			<input type="date" id="requestDateM" class="form-control" value="<%= req.getRequestDate() %>">
					</div>
					<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
			  			<label for="requestTimeM" class="control-label">Selezionare l'orario</label>
			  			<input type="time" id="requestTimeM" class="form-control" min="09:00" max="16:40" step="900" value="<%= Utils.getTimeAsString(req.getRequestTime()) %>">
			  		</div>
			  	</div>
			</div>	
								
	  		<label for="comment" class="control-label">Commento</label>
	  		<textarea class="form-control" id="comment" rows="3"><%= req.getStudentComment() %></textarea>
	  		<br>
							
			<div class="panel"></div>
			<div class="alert alert-success" id="successDiv" role="alert" style="display:none;margin-top: 25px;">Modifica prenotazione completata con successo!</div>
			<div class="alert alert-danger" id="failureDiv" role="alert" style="display:none;margin-top: 25px;">Modifica prenotazione fallita!</div>
			      	
			<div>
				<input class="btn btn-primary" id="modifyRequest" type="button" onclick="validateInputsModifyRequest()" value="Modifica">		        	
	        </div>
		</div>
	</div>
	
	<input type="hidden" id="requestId" value="<%= req.getIdRequest() %>">
	
	<script src="<%= request.getContextPath() %>/js/studentScript.js"></script>
	<script type="text/javascript">
		$(document).ready( function() {
			var now = new Date();
			now.setHours(now.getHours() + 1 );
						
			var dd = now.getDate();
			var mm = now.getMonth() + 1;
			var yyyy = now.getFullYear();
			if (dd < 10){
			   dd='0'+dd
			} 
			if (mm < 10){
			   mm = '0'+mm
			} 
			today = yyyy + '-' + mm + '-' + dd;
			
			document.getElementById("requestDateM").setAttribute("min", today);
									
			$("#requestTimeM").change();
		});
	</script>

	<%@ include file="/partials/footer.jsp" %>
</body>
</html>