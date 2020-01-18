<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Utils.Utils,project.Model.RequestBean"%>

<head>
	<meta charset="UTF-8">
	<title>Effettua Prenotazione</title>
	<link rel="shortcut icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon">
	<link rel="icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon">
	
	<style>
		.vertical-alignment-helper {
		    display:table;
		    height: 100%;
		    width: 100%;
		    pointer-events:none;
		}
		.vertical-align-center {
		    display: table-cell;
		    vertical-align: middle;
		    pointer-events:none;
		}
		.modal-content {
		    width:inherit;
		    max-width:inherit;
		    height:inherit;
		    margin: 0 auto;
		    pointer-events: all;
		}  	
    </style>
</head>

<body>
	<%@ include file="/partials/header.jsp" %>
	
	<div class="modal fade" id="resultModal" role="dialog">
		 <div class="vertical-alignment-helper">
        	<div class="modal-dialog vertical-align-center">
            	<div class="modal-content">
		      		<div class="modal-body">
			        	<div class="alert alert-success" id="successDiv" role="alert" style="display:none;margin-top: 25px;">Prenotazione completata con successo!</div>
			        	<div class="alert alert-danger" id="failureDiv" role="alert" style="display:none;margin-top: 25px;">Prenotazione fallita!</div>
			      	</div>
		    	</div>
		  	</div>
		</div>
	</div>
	
	<div class="content text-center" style="width: 40%;margin: 0 auto; margin-top: 50px; border: 2px solid #232F3E; border-radius:8px;">
		<div class="panel">
			<h2 align="center">Nuova prenotazione</h2>
			<p>Inserisci i dati della prenotazione, aggiungendo un commento che verr&aacute letto dai tutor</p>
		</div>
		
		<div class="container-fluid" style="margin: 25px;">
			<div class="row row-space">
				<div class="row">
					<div class="alert alert-warning" id="errorDiv" role="alert" style="display:none;"></div>
				</div>
				
				<div class="row">
					<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
			  			<label for="requestDate" class="control-label">Selezionare la data</label>
			  			<input type="date" id="requestDate" class="form-control">
					</div>
					<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
			  			<label for="requestTime" class="control-label">Selezionare l'orario</label>
			  			<input type="time" id="requestTime" class="form-control" min="09:00" max="16:45" step="900">
			  		</div>
			  	</div>
			</div>	
								
	  		<label for="comment" class="control-label">Commento</label>
	  		<textarea class="form-control" id="comment" rows="3"></textarea>
	  		<br>
							
			<div class="panel"></div>
			<div>
				<input class="btn btn-primary" id=create type="button" onclick="validateInputsNewRequest()" value="Prenota">		        	
	        </div>
		</div>
	</div>

	<%@ include file="/partials/footer.jsp" %>
	
	<script src="<%= request.getContextPath() %>/js/studentScript.js"></script>
	<script type="text/javascript">
		$(document).ready( function() {
			var now = new Date();
			now.setHours(now.getHours() + 1 );
			
			$("#requestDate").change();
							
			document.getElementById("requestDate").valueAsDate = now;
					
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
			
			document.getElementById("requestDate").setAttribute("min", today);			
			
			var timeString = now.toISOString().substr(11, 8);
						
			document.getElementById("requestTime").value = timeString;
			document.getElementById("requestTime").stepUp(1);
									
			$("#requestTime").change();
		});
	</script>
</body>
</html>