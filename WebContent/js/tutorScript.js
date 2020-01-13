// Script aggiunta nuova attività
// Controlla se l'orario di inizio attività inserito è valido, cioè compreso tra le 7:30 e le 22:00
function isValidStartTime() {
	var startTime = $("#startTime");
	
	if (startTime.val() == '') {
		return false;
	}
	
	var array = startTime.val().split(':');	
	var min = (+array[0]) * 60 + (+array[1]);
	
	if (min < 450 || min > 1320)
		return false;
	else {
		var finishTime = $("#finishTime");
		
		var arrayFinish = finishTime.val().split(':');	
		var minFinish = (+arrayFinish[0]) * 60 + (+arrayFinish[1]);
		
		if (minFinish < min)
			return false;
		else
			return true;
	}
}

// Controlla se l'orario di fine attività inserito è valido, cioè compreso tra le 7:30 e le 22:00
function isValidFinishTime() {
	var finishTime = $("#finishTime");
	
	if (finishTime.val() == '') {
		return false;
	}
	
	var array = finishTime.val().split(':');	
	var min = (+array[0]) * 60 + (+array[1]);
	
	if (min < 450 || min > 1320)
		return false;
	else {
		var startTime = $("#startTime");
		
		var arrayStart = startTime.val().split(':');	
		var minStart = (+arrayStart[0]) * 60 + (+arrayStart[1]);
		
		if (min < minStart)
			return false;
		else
			return true;
	}
}

// Controlla se la categoria selezionata è valida
function isValidCategory() {
	if ($( "#category option:selected" ).text() == '--') {
		return false;
	}
	else
		return true;
}

// Controlla se la data immessa è valida
function isValidDate(date) {
	if ($(date).val() == "") {
		return false;
	}
	else
		return true;
}

// Quando si cambia l'orario di inizio attività, imposta l'orario minimo di fine attività di conseguenza e aggiorna la tabella degli appuntamenti
$("#startTime").on("change", function () {
	if($(this).val() != '') {
		document.getElementById("finishTime").setAttribute("min", $(this).val());
		$("#category").change();
	}
});

// Quando si cambia l'orario di fine attività, imposta l'orario massimo di inizio attività di conseguenza e aggiorna la tabella degli appuntamenti
$("#finishTime").on("change", function () {
	if($(this).val() != '') {
		document.getElementById("startTime").setAttribute("max", $(this).val());
		$("#category").change();
	}
});

// Quando cambia la data, resetta le ore iniziali e finali di attività e aggiorna la tabella degli appuntamenti
$("#date").on("change", function () {
	$("#startTime").val("07:30");
	$("#finishTime").val("22:00");
	
	if($(this).val() != '')
		$("#category").change();
});


// Controlla se nel DB il tutor ha già registrato un'attività in quella giornata, tra le ore indicate.
function checkActivityPresent() {
	$.post("/TutoratoSmart/Activity", { 
		"ajax": "true",
		"check": "true",
		"date": $("#date").val(),
		"startTime": $("#startTime").val(),
		"finishTime": $("#finishTime").val(),
		},
		function(data){
			activityRegistered = data.available;			
		});
}

// Ricerca nel db gli appuntamenti fatti dal tutor in quella data tra le ore indicate e mostra la tabella con i dettagli.
$('#category').change(function () {
	$("#appointmentsPanel").hide();
	$("#appointments").html("");
	
	checkActivityPresent();
		
	var optionSelected = $("option:selected", this);
    var valueSelected = this.value;
    
    if(valueSelected == "Sportello Tutorato") {    	
    	$.post("/TutoratoSmart/Activity", {
			"ajax":"true",
			"date":$("#date").val(),
			"startTime":$("#startTime").val(),
			"finishTime":$("#finishTime").val(),			
			},
			function(data){
				$("#appointmentsPanel").show();
				$("#appointments").append("<div class=\"panel\">" +
										      "<h4 align=\"center\">Appuntamenti effettuati in data "+ $("#date").val() + "</h4>" +
										      "<h4 align=\"center\">dalle ore "+ $("#startTime").val() + " alle ore " + $("#finishTime").val() + "</h4>" +
										  "</div>" +
										  "<table style=\"width: 95%;margin: 0 auto;\">" +	
										  "<tr>" +
										  	   "<th class=\"text-center\" style=\"width: 30%;\">Studente</th>" +
										   	   "<th class=\"text-center\" style=\"width: 70%;\">Commenti tutor</th>" +
										  "</tr>");
				for(i=0;i<data.length;i++) {
					$("#appointments").append("<table style=\"width: 95%;margin: 0 auto;\">" +
											  "<tr>" +
											  	   "<td style=\"width: 30%;\">" + data[i].firstName + " " + data[i].lastName + "</td>" +
											       "<td style=\"width: 70%;\">" + data[i].details + "</td>" +
											  "</tr>");
				}
				$("#appointments").append("<div style=\"width: 95%;margin-bottom: 25px\">");
			});
    }
});

// Controlla se i dati inseriti della nuova attività sono validi e procede al salvataggio
function validateInputsActivity() {
	$("#failureDiv").hide();
	$("#errorDiv").html("");
	$("#errorDiv").hide();
	
	checkActivityPresent();
	
	var valid = true;
	var errorMessage = "";
	
	var category = $('#category');
	var date = $("#date");
	var startTime = $("#startTime");
	var finishTime = $("#finishTime");
	var description = $("#description");
	
	if (activityRegistered) {
		errorMessage += "<strong>Hai registrato un'altra attivit&aacute in quelle ore</strong><br/>"
		valid = false;
	}
	$('#addActivity').attr('disabled','disabled');
	if (!isValidCategory(category)) {
		errorMessage += "<strong>Selezionare una categoria</strong><br/>"
		valid = false;
	}
	if (!isValidDate(date)) {
		errorMessage += "<strong>Selezionare una data</strong><br/>"
		valid = false;
	}
	else { 
		if(!isValidStartTime()) {
			errorMessage += "<strong>Selezionare un orario di inizio attivit&aacute compreso tra le 7:30 e le ore 22:00, e non successivo all'orario di fine attivit&aacute</strong><br/>"
			valid = false;
		}
		if(!isValidFinishTime()) {
			errorMessage += "<strong>Selezionare un orario di fine attivit&aacute compreso tra le 7:30 e le ore 22:00, e non precedente all'orario di inizio attivit&aacute</strong><br/>"
			valid = false;
		}
	}
	
	if (description.val() == '') {
		errorMessage += "<strong>Inserire una descrizione riguardante l'attivit&aacute svolta</strong><br/>";	
		valid = false;
	}
	
	if(valid) {
		$.post("/TutoratoSmart/Activity", {
			"flag":"1",
			"category": $( "#category option:selected" ).text(),
			"date": date.val(),
			"startTime": startTime.val(),
			"finishTime": finishTime.val(),
			"description": description.val(),
			},
			function(data){
				if (data.result == 1) {
					$("#successDiv").fadeIn(500, function() {
						$("#successDiv").fadeOut(3000);
						setTimeout(function() {
							  window.location.href = "/TutoratoSmart/tutor/register.jsp";
						}, 3000);
					})
				}
				else {
					$("#failureDiv").fadeIn(500);
					$('#addActivity').prop("disabled", false);
				}					 
			});
	}
	else {
		$("#errorDiv").append(errorMessage);
		$("#errorDiv").show();
		$('#addActivity').prop("disabled", false);
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Controlla se esiste già un'attività registrata, diversa da quella che si vuole modificare.
function checkDifferentActivity() {
	$.post("/TutoratoSmart/Activity", { 
		"ajax": "true",
		"check": "true",
		"date": $("#dateM").val(),
		"startTime": $("#startTimeM").val(),
		"finishTime": $("#finishTimeM").val(),
		"activityId": $("#idActivity").val(),
		"modify": "true",
		},
		function(data){
			otherActivityRegistered = data.available;			
		});
}

function isValidStartTimeM() {
	var startTime = $("#startTimeM");
	
	if (startTime.val() == '') {
		return false;
	}
	
	var array = startTime.val().split(':');	
	var min = (+array[0]) * 60 + (+array[1]);
	
	if (min < 450 || min > 1320)
		return false;
	else {
		var finishTime = $("#finishTimeM");
		
		var arrayFinish = finishTime.val().split(':');	
		var minFinish = (+arrayFinish[0]) * 60 + (+arrayFinish[1]);
		
		if (minFinish < min)
			return false;
		else
			return true;
	}
}

// Controlla se l'orario di fine attività inserito è valido, cioè compreso tra le 7:30 e le 22:00
function isValidFinishTimeM() {
	var finishTime = $("#finishTimeM");
	
	if (finishTime.val() == '') {
		return false;
	}
	
	var array = finishTime.val().split(':');	
	var min = (+array[0]) * 60 + (+array[1]);
	
	if (min < 450 || min > 1320)
		return false;
	else {
		var startTime = $("#startTimeM");
		
		var arrayStart = startTime.val().split(':');	
		var minStart = (+arrayStart[0]) * 60 + (+arrayStart[1]);
		
		if (min < minStart)
			return false;
		else
			return true;
	}
}

//Quando si cambia l'orario di inizio attività, imposta l'orario minimo di fine attività di conseguenza e aggiorna la tabella degli appuntamenti
$("#startTimeM").on("change", function () {
	if($(this).val() != '') {
		document.getElementById("finishTimeM").setAttribute("min", $(this).val());
		$("#categoryM").change();
	}
});

// Quando si cambia l'orario di fine attività, imposta l'orario massimo di inizio attività di conseguenza e aggiorna la tabella degli appuntamenti
$("#finishTimeM").on("change", function () {
	if($(this).val() != '') {
		document.getElementById("startTimeM").setAttribute("max", $(this).val());
		$("#categoryM").change();
	}
});

// Quando cambia la data, resetta le ore iniziali e finali di attività e aggiorna la tabella degli appuntamenti
$("#dateM").on("change", function () {
	$("#startTimeM").val("07:30");
	$("#finishTimeM").val("22:00");
	
	if($(this).val() != '')
		$("#categoryM").change();
});

//Ricerca nel db gli appuntamenti fatti dal tutor in quella data tra le ore indicate e mostra la tabella con i dettagli.
$('#categoryM').change(function () {
	$("#appointmentsPanel").hide();
	$("#appointments").html("");
	
	checkDifferentActivity();
		
	var optionSelected = $("option:selected", this);
    var valueSelected = this.value;
    
    if(valueSelected == "Sportello Tutorato") {    	
    	$.post("/TutoratoSmart/Activity", {
			"ajax":"true",
			"date":$("#dateM").val(),
			"startTime":$("#startTimeM").val(),
			"finishTime":$("#finishTimeM").val(),			
			},
			function(data){
				$("#appointmentsPanel").show();
				$("#appointments").append("<div class=\"panel\">" +
										      "<h4 align=\"center\">Appuntamenti effettuati in data "+ $("#dateM").val() + "</h4>" +
										      "<h4 align=\"center\">dalle ore "+ $("#startTimeM").val() + " alle ore " + $("#finishTimeM").val() + "</h4>" +
										  "</div>" +
										  "<table style=\"width: 95%;margin: 0 auto;\">" +	
										  "<tr>" +
										  	   "<th class=\"text-center\" style=\"width: 30%;\">Studente</th>" +
										   	   "<th class=\"text-center\" style=\"width: 70%;\">Commenti tutor</th>" +
										  "</tr>");
				for(i=0;i<data.length;i++) {
					$("#appointments").append("<table style=\"width: 95%;margin: 0 auto;\">" +
											  "<tr>" +
											  	   "<td style=\"width: 30%;\">" + data[i].firstName + " " + data[i].lastName + "</td>" +
											       "<td style=\"width: 70%;\">" + data[i].details + "</td>" +
											  "</tr>");
				}
				$("#appointments").append("<div style=\"width: 95%;margin-bottom: 25px\">");
			});
    }
});

// Controlla se i nuovi dati inseriti dell'attività sono validi e procede alla modifica
function modifyActivity() {
	$("#failureDiv").hide();
	$("#errorDiv").html("");
	$('#errorDiv').hide();
	
	checkDifferentActivity();
	
	var valid = true;
	var errorMessage = "";
	
	var category = $('#categoryM');
	var date = $("#dateM");
	var startTime = $("#startTimeM");
	var finishTime = $("#finishTimeM");
	var description = $("#description");
	
	if (otherActivityRegistered) {
		errorMessage += "<strong>Hai registrato un'altra attivit&aacute in quelle ore</strong><br/>"
		valid = false;
	}
	
	if (!isValidCategory(category)) {
		errorMessage += "<strong>Selezionare una categoria</strong><br/>"
		valid = false;
	}
	if (!isValidDate(date)) {
		errorMessage += "<strong>Selezionare una data</strong><br/>"
		valid = false;
	}
	else { 
		if(!isValidStartTimeM()) {
			errorMessage += "<strong>Selezionare un orario di inizio attivit&aacute compreso tra le 7:30 e le ore 22:00, e non successivo all'orario di fine attivit&aacute</strong><br/>"
			valid = false;
		}
		if(!isValidFinishTimeM()) {
			errorMessage += "<strong>Selezionare un orario di fine attivit&aacute compreso tra le 7:30 e le ore 22:00, e non precedente all'orario di inizio attivit&aacute</strong><br/>"
			valid = false;
		}
	}
	
	if (description.val() == '') {
		errorMessage += "<strong>Inserire una descrizione riguardante l'attivit&aacute svolta</strong><br/>";	
		valid = false;
	}
	
	if(valid) {
		$.post("/TutoratoSmart/Activity", {
			"flag":"2",
			"delete": "false",
			"category": $( "#categoryM option:selected" ).text(),
			"date": date.val(),
			"startTime": startTime.val(),
			"finishTime": finishTime.val(),
			"description": description.val(),
			"id": $("#idActivity").val(),
			},
			function(data){
				$('#modifyActivity').attr('disabled','disabled');
				$('#back').attr('disabled','disabled');
				if (data.result == 1) {
					$("#successDiv").fadeIn(500, function() {
						$("#successDiv").fadeOut(3000);
						setTimeout(function() {
							  window.location.href = "/TutoratoSmart/tutor/register.jsp";
						}, 3000);
					})
				}
				else {
					$("#failureDiv").fadeIn(500);
					$('#modifyActivity').prop("disabled", false);
					$('#back').prop("disabled", false);
				}					 
			});
	}
	else {
		$("#errorDiv").append(errorMessage);
		$("#errorDiv").show();
	}
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Script cancellazione attività
//Funzione di cancellazione attività da parte di un tutor
function deleteActivity(){
	$.post("/TutoratoSmart/Activity", {
		"flag":"2",
		"delete":"true",
		},
		function(data){
			$('#deleteModal').modal('hide');
			$('#modifyActivity').attr('disabled','disabled');
			$('#deleteButton').attr('disabled','disabled');
			$('#back').attr('disabled','disabled');
			
			if (data.result == 1) {				
				$("#successDeleteDiv").fadeIn(500, function() {
					$("#successDeleteDiv").fadeOut(3000);
					setTimeout(function() {
						  window.location.href = "/TutoratoSmart/tutor/register.jsp";
					}, 3000);
				})
			}
			else {
				$("#failureDeleteDiv").fadeIn(500, function() {
					$('#modifyActivity').prop("disabled", false);
					$('#deleteButton').prop("disabled", false);
					$('#back').prop("disabled", false);
				})
			}					 
		});
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Script accettazione richiesta di appuntamento
//Controlla se la durata inserita è valida
function isValidDuration(duration) {
	if (duration.val() == '') {
		return false;
	}
	
	var min = (+duration.val());
	
	if (min < 10 || min > 120)
		return false;
	else
		return true;
}

// Verifica durata ed esegue post per accettazione richiesta di appuntamento
function acceptRequest() {
	$('#acceptRequest').attr('disabled','disabled');
	$('#back').attr('disabled','disabled');
	
	$("#errorDiv").html("");
	$('#errorDiv').hide();
	
	var valid = true;
	var errorMessage = "";
	
	var duration = $("#requestDuration");
	
	if(!isValidDuration(duration)) {
		errorMessage += "<strong>Inserire una durata valida (10-120 minuti)</strong><br/>"
		valid = false;		
	}
	
	if(valid) {
		$.post("/TutoratoSmart/Request", {
			"flag":"4",
			"id":$("#requestId").val(),
			"duration":$("#requestDuration").val(),
			},
			function(data){
				$('#acceptModal').modal('hide');				
				if (data.result == 1) {
					$("#successDeleteDiv").fadeIn(500, function() {
						$("#successDeleteDiv").fadeOut(3000);
						setTimeout(function() {
							  window.location.href = "/TutoratoSmart/tutor/requestInfo.jsp";
						}, 3000);
					})
				}
				else {
					$("#failureDeleteDiv").fadeIn(500, function() {
						$('#back').prop("disabled", false);
						$('#acceptRequest').prop("disabled", false);
					})
				}					 
			});
	}
	else {
		$("#errorDiv").append(errorMessage);
		$("#errorDiv").show();
		$('#back').prop("disabled", false);
		$('#acceptRequest').prop("disabled", false);
	}	
}

// Funzione che aggiorna lo stato di una richiesta quando uno studente risulta assente
function absentStudent() {
	$("#errorDiv").html("");
	$('#errorDiv').hide();
	
	var valid = true;
	var errorMessage = "";
	
	if(valid) {
		$.post("/TutoratoSmart/Request", {
			"flag":"5",
			},
			function(data){
				$('#absentModal').modal('hide');
				$('#confirmAppointment').attr('disabled','disabled');
				$('#absentStudent').attr('disabled','disabled');
				$('#back').attr('disabled','disabled');
				if (data.result == 1) {
					$("#successAbsentDiv").fadeIn(500, function() {
						$("#successAbsentDiv").fadeOut(3000);
						setTimeout(function() {
							  window.location.href = "/TutoratoSmart/tutor/requestInfo.jsp";
						}, 3000);
					})
				}
				else {
					$("#failureAbsentDiv").fadeIn(500, function() {
						$('#confirmAppointment').prop("disabled", false);
						$('#absentStudent').prop("disabled", false);
						$('#back').prop("disabled", false);
					})
				}					 
			});
	}
	else {
		$("#errorDiv").append(errorMessage);
		$("#errorDiv").show();
	}	
}


//Verifica che sia stato inserito un commento ed esegue post per la registrazione di un nuovo appuntamento
function validateInputsAppointment() {
	$("#errorDiv").html("");
	$('#errorDiv').hide();
	
	var valid = true;
	var errorMessage = "";
	
	var comment = $("#appointmentComment");
		
	if (!$('#appointmentComment').val().trim().length > 0) {
		errorMessage += "<strong>Inserire un commento riguardante l'appuntamento svolto</strong><br/>";	
		valid = false;
	}
	
	if(valid) {
		$.post("/TutoratoSmart/Appointment", {
			"flag":"1",
			"comment":$(comment).val(),
			},
			function(data){
				$('#addAppointment').attr('disabled','disabled');
				if (data.result == 1) {
					$("#successDiv").fadeIn(500, function() {
						$("#successDiv").fadeOut(3000);
						setTimeout(function() {
							  window.location.href = "/TutoratoSmart/tutor/requestInfo.jsp";
						}, 3000);
					})
				}
				else {
					$("#failureDiv").fadeIn(500, function() {
						$('#addAppointment').prop("disabled", false);
					})
				}					 
			});
	}
	else {
		$("#errorDiv").append(errorMessage);
		$("#errorDiv").show();
	}	
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Script cancellazione appuntamento
//Funzione di cancellazione appuntamento da parte di un tutor
function deleteAppointment(){
	$.post("/TutoratoSmart/Appointment", {
		"flag":"3",
		},
		function(data){
			$('#deleteModal').modal('hide');
			$('#modifyAppointment').attr('disabled','disabled');
			$('#deleteButton').attr('disabled','disabled');
			$('#back').attr('disabled','disabled');
			
			if (data.result == 1) {				
				$("#successDeleteDiv").fadeIn(500, function() {
					$("#successDeleteDiv").fadeOut(3000);
					setTimeout(function() {
						  window.location.href = "/TutoratoSmart/tutor/appointmentsList.jsp";
					}, 3000);
				})
			}
			else {
				$("#failureDeleteDiv").fadeIn(500, function() {
					$('#modifyAppointment').prop("disabled", false);
					$('#deleteButton').prop("disabled", false);
					$('#back').prop("disabled", false);
				})
			}					 
		});
}


////////////////////////////////////////////////////////////////////////////////////////////////////
// Funzione di controllo dati per modifica appuntamento.
function validateModifyAppointment() {
	$('#modifyAppointment').attr('disabled','disabled');
	$("#errorDiv").html("");
	$('#errorDiv').hide();
	
	var valid = true;
	var errorMessage = "";
	
	var comment = $("#appointmentComment");
		
	if (!$('#appointmentComment').val().trim().length > 0) {
		errorMessage += "<strong>Inserire un commento riguardante l'appuntamento svolto</strong><br/>";	
		valid = false;
	}
	
	if(valid) {
		$.post("/TutoratoSmart/Appointment", {
			"flag":"2",
			"comment":$(comment).val(),
			},
			function(data){
				$('#modifyAppointment').attr('disabled','disabled');
				if (data.result == 1) {
					$("#successDiv").fadeIn(500, function() {
						$("#successDiv").fadeOut(3000);
						setTimeout(function() {
							window.location.href = "/TutoratoSmart/tutor/appointmentsList.jsp";
						}, 3000);
					})
				}
				else {
					$("#failureDiv").fadeIn(500, function() {
						$('#modifyAppointment').prop("disabled", false);
					})
				}					 
			});
	}
	else {
		$("#errorDiv").append(errorMessage);
		$("#errorDiv").show();
		$('#modifyAppointment').prop("disabled", false);
	}	
}