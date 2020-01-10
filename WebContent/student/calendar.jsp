<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<%@ page import="java.util.*,project.Model.Calendar,project.Model.RequestBean"%>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>Calendario Appuntamenti</title>
	<link href='/TutoratoSmart/fullcalendar/core/main.css' rel='stylesheet' />
	<link href='/TutoratoSmart/fullcalendar/daygrid/main.css' rel='stylesheet' />
    <link href='/TutoratoSmart/fullcalendar/timegrid/main.css' rel='stylesheet' />
	<link href='/TutoratoSmart/fullcalendar/list/main.css' rel='stylesheet' />	
		
    <script src='/TutoratoSmart/fullcalendar/core/main.js'></script>
    <script src='/TutoratoSmart/fullcalendar/interaction/main.js'></script>
    <script src='/TutoratoSmart/fullcalendar/daygrid/main.js'></script>
    <script src='/TutoratoSmart/fullcalendar/timegrid/main.js'></script>
    <script src='/TutoratoSmart/fullcalendar/list/main.js'></script>
    
    <style type="text/css">
    	.fc-event {
    		min-height: 12px !important;
    	}
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
	
	<div id='calendar' style="width: 65%; margin: 0 auto;"></div>
	
		
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
	
	
	<div class="modal fade" id="requestModal" role="dialog">
		 <div class="vertical-alignment-helper">
        	<div class="modal-dialog vertical-align-center">
            	<div class="modal-content">
					<div class="modal-header">
			          	<button type="button" class="close" data-dismiss="modal">&times;</button>
			          	<h4 class="modal-title">Nuova prenotazione</h4>
			        </div>
			        <div class="modal-body">
			          	<div class="container-fluid" style="margin: 25px;">
							<form method="POST" action="<%= response.encodeURL("/TutoratoSmart/Request") %>">
								<div class="row text-center">
								  	<div class="column" style="float: left;width: 50%;">
								  		<label for="date" class="control-label">Giorno selezionato</label>
										<input type="text" value="" class="text-center" readonly id ="date">
								  	</div>
								  	<div class="column" style="float: left;width: 50%;">
								  		<label for="time" class="control-label">Orario selezionato</label>
										<input type="text" value="" class="text-center" readonly id ="time">
								  	</div>
								</div>
								<br>							
								<label for="comment" class="control-label">Inserire un commento alla prenotazione: </label>							
			  					<textarea class="form-control" id="comment" name="comment" rows="3" required></textarea>
							</form>
						</div>
					</div>
		            <div class="modal-footer">
			        	<div class="row text-center">
						  	<div class="column" style="float: left;width: 50%;"><input class="btn btn-success" type="button" onclick="createRequest()" value="Conferma prenotazione"></div>
						  	<div class="column" style="float: left;width: 50%;"><button type="button" class="btn btn-primary" data-dismiss="modal">Annulla</button></div>
						</div>
				    </div>
				</div>
	    	</div>						      
		</div>
	</div>
	
	<%@ include file="/partials/footer.jsp" %>
	
	<script src="/TutoratoSmart/js/studentCalendarScript.js"></script>
</body>
</html>