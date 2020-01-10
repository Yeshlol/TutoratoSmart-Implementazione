//Funzione di cancellazione prenotazione da parte di uno studente
function deleteRequest(){
	$.post("/TutoratoSmart/Request", {
		"flag":"2",
		"id":$("#requestId").val(),
		"delete":"true",
		},
		function(data){	  
			if (data.result == 1) {
				$('#deleteModal').modal('hide');
				$("#successDeleteDiv").fadeIn(500, function() {
					$("#successDeleteDiv").fadeOut(3000);
					setTimeout(function() {
						  window.location.href = "/TutoratoSmart/student/requestsList.jsp";
					}, 3000);
				})
			}
			else {
				$("#failureDeleteDiv").fadeIn(500, function() {
					$("#failureDeleteDiv").fadeOut(5000)
				})
			}					 
		});
}


// Funzioni di validazione dati per creazione nuova prenotazione
// Controllo data inserita antecedente ad oggi
function isValidDate(date) {
	if(date.val() == '') {
		return false;
	}
	
	var d = new Date(date.val());
	var today = new Date();
		
	if (d < today)
		return false
	else 
		return true;
}


// Controllo data inserita sia mercoledì o giovedì
function validateDate(date) {
	var d = new Date(date.val());
	
	if(d.getDay() === 3 || d.getDay() === 4) {
		return true;
    } else {
    	return false;
    }
}


// Rientra in orario di lavoro
function isValidTime(time) {	
	if (time.val() == '') {
		return false;
	}
	
	var array = time.val().split(':');	
	var min = (+array[0]) * 60 + (+array[1]);
	
	if (min < 540 || min > 765 && min < 870 || min > 990)
		return false;
	else
		return true;
}


// Controllo modifica data
$("#requestDate").on("change",function(){
	var color = "1px solid red";
	
	if (isValidDate($(this)) && validateDate($(this))) 
		color = "1px solid green";
	
	$(this).css("border", color);
		
	$("#requestTime").val("09:00");
	$("#requestTime").change();
});


// Controllo disponibilità appuntamento presso lo sportello
$("#requestTime").on("change",function(){	
	var time = $("#requestTime");
	var date = $("#requestDate");
	
	var color = "1px solid red";
	
	$(this).css("border",color);
	
	if (isValidDate(date) && validateDate(date) && isValidTime(time)) {
		$.post("/TutoratoSmart/Request", {
			"date":$(date).val(),
			"time":$(time).val(),
			"ajax":"true"
			},
			function(data){				
				if (data.disponibile) {
					color = "1px solid green";					
					$(time).css("border",color);
				}
				
				available = data.disponibile;
			});
	}
});

// Verifica campi input prima del post registrazione nuova richiesta di appuntamento
function validateInputsNewRequest() {
	$("#errorDiv").html("");
	$('#errorDiv').hide();
	
	var valid = true;
	var errorMessage = "";
	
	var date = $("#requestDate");
	var time = $("#requestTime");
	var comment = $("#comment");
	
	if (!isValidDate(date)) {
		errorMessage += "<strong>Selezionare una data valida</strong><br/>"
		valid = false;
	}	
	else { 
		if(!validateDate(date)) {
			errorMessage += "<strong>Lo sportello di tutorato è attivo il mercoled&iacute e gioved&iacute, ore 9:00-13:00 e 14:30-17:00</strong><br/>"
				valid = false;
		}
		else {
			if(!isValidTime(time)) {
				errorMessage += "<strong>Selezionare un orario valido. Lo sportello &eacute aperto dalle ore 9:00-13:00 e 14:30-17:00</strong><br/>";	
				valid = false;
			}			
			else if (!available) {
				errorMessage += "<strong>Lo sportello &eacute gi&aacute occupato da un'altra prenotazione</strong><br/>";	
				valid = false;
			}
		}
	}
	
	if (comment.val() == '') {
		errorMessage += "<strong>Inserire un commento riguardante la richiesta di appuntamento</strong><br/>";	
		valid = false;
	}
	
	if (valid) {		
		$.post("/TutoratoSmart/Request", {
			"flag":"1",
			"comment":$("#comment").val(),
			"date":$("#requestDate").val(),
			"time":$("#requestTime").val(),
			},
			function(data) {
				$('#resultModal').modal();
				
				if (data.result == 1) {	
					$("#successDiv").show();
					
					setTimeout(function() {
						$('#resultModal').modal('hide');
						window.location.href = "/TutoratoSmart/student/calendar.jsp";
					}, 3000);
				}				
				else {
					$("#failureDiv").show();
					
					setTimeout(function() {
						$('#resultModal').modal('hide');
					}, 3000);
				}					 
			});		
	}	
	else {
		$("#errorDiv").append(errorMessage);
		$("#errorDiv").show();
	}
}


// Verifica campi input prima della modifica prenotazione
function validateInputsModifyRequest() {
	$("#errorDiv").html("");
	$('#errorDiv').hide();
	
	var valid = true;
	var errorMessage = "";
	
	var date = $("#requestDate");
	var time = $("#requestTime");
	var comment = $("#comment");
	
	if (!isValidDate(date)) {
		errorMessage += "<strong>Selezionare una data valida</strong><br/>"
		valid = false;
	}	
	else { 
		if(!validateDate(date)) {
			errorMessage += "<strong>Lo sportello di tutorato è attivo il mercoled&iacute e gioved&iacute, ore 9:00-13:00 e 14:30-17:00</strong><br/>"
				valid = false;
		}
		else {
			if(!isValidTime(time)) {
				errorMessage += "<strong>Selezionare un orario valido. Lo sportello &eacute aperto dalle ore 9:00-13:00 e 14:30-17:00</strong><br/>";	
				valid = false;
			}			
			else if (!available) {
				errorMessage += "<strong>Lo sportello &eacute gi&aacute occupato da un'altra prenotazione</strong><br/>";	
				valid = false;
			}
		}
	}
	
	if (comment.val() == '') {
		errorMessage += "<strong>Inserire un commento riguardante la richiesta di appuntamento</strong><br/>";	
		valid = false;
	}
	
	if (valid) {
		$.post("/TutoratoSmart/Request", {
			"flag":"3",
			"id":$("#requestId").val(),
			"comment":$("#comment").val(),
			"date":$("#requestDate").val(),
			"time":$("#requestTime").val(),
			},
			function(data) {
				$('#resultModal').modal();
				
				if (data.result == 1) {	
					$("#successDiv").show();
					
					setTimeout(function() {
						$('#resultModal').modal('hide');
						window.location.href = "/TutoratoSmart/student/requestInfo.jsp";
					}, 3000);
				}				
				else {
					$("#failureDiv").show();
					
					setTimeout(function() {
						$('#resultModal').modal('hide');
					}, 3000);
				}					 
			});		
	}	
	else {
		$("#errorDiv").append(errorMessage);
		$("#errorDiv").show();
	}
}