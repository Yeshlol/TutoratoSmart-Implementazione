$("#Email").on('keyup', function() {
	$.post("/TutoratoSmart/Registration", { 
		"mail":$(this).val(),
		"ajax":"true"
		},
		function(data){			  
			var color;
			  
			if (validateEmail($("#Email")) && data.disponibile)
				color = "1px solid green"; 
			else
				color = "1px solid red";
						 
			$("#Email").css("border",color);
			emailDisp = data.disponibile;
		});
});


$("#Password").on('keyup',function(){
	var color;
	if (validatePassword($(this)))
		color = "1px solid green";
	else
		color = "1px solid red";
	
	if ($(this).val() == "")
		color = "0";
	
	$(this).css("border",color);
});


$("#VerifyPassword").on('keyup',function(){
	var color;
	if (validatePassword($(this)) && checkPasswords($(this), $("#Password")))
		color = "1px solid green";
	else
		color = "1px solid red";
	
	if ($(this).val() == "")
		color = "0";
	
	$(this).css("border",color);
});


$("#FirstName").on('keyup',function(){
	var color;
	if (validateFirstName($(this)))
		color = "1px solid green";
	else
		color = "1px solid red";
	
	if ( $(this).val() == "")
		color = "0";
	
	$(this).css("border",color);
});

$("#LastName").on('keyup ',function(){
	var color;
	if (validateLastName($(this)))
		color = "1px solid green";
	else
		color = "1px solid red";
	
	if ($(this).val() == "")
		color = "0";
	
	$(this).css("border",color);
});


$("#TelephoneNumber").on('keyup',function(){
	var color;
	if (validateTelephoneNumber($(this)))
		color = "1px solid green";
	else
		color = "1px solid red";
	
	if ($(this).val() == "")
		color = "0";
	
	$(this).css("border",color);
});


$("#RegistrationNumber").on('keyup',function(){
	var color;
	if (validateRegistrationNumber($(this)))
		color = "1px solid green";
	else
		color = "1px solid red";
	
	if ($(this).val() == "")
		color = "0";
	
	$(this).css("border",color);
});


//Validazione Dati Registrazione
function validateInputs(){
	$("#errorDiv").html("");
	
	var valid = true;
	var errorMessage = "";
	
	var email = $("#Email");
	var password = $("#Password");
	var verifyPassword = $("#VerifyPassword");
	var firstName = $("#FirstName");
	var lastName = $("#LastName");
	var telephoneNumber = $("#TelephoneNumber");
	var registrationNumber = $("#RegistrationNumber");
	
	if (email.val() == '') {
		errorMessage += "<strong>Inserire l'indirizzo email</strong><br/>"
		valid = false;
	}
	if (password.val() == '') {
		errorMessage += "<strong>Inserire la password</strong><br/>"
		valid = false;
	}
	if (verifyPassword.val() == '') {
		errorMessage += "<strong>Confermare la password</strong><br/>"
		valid = false;
	}
	if (firstName.val() == '') {
		errorMessage += "<strong>Inserire il nome</strong><br/>"
		valid = false;
	}
	if (lastName.val() == '') {
		errorMessage += "<strong>Inserire il cognome</strong><br/>"
		valid = false;
	}
	if (telephoneNumber.val() == '') {
		errorMessage += "<strong>Inserire il numero di telefono</strong><br/>"
		valid = false;
	}
	if (registrationNumber.val() == '') {
		errorMessage += "<strong>Inserire la matricola</strong><br/>"
		valid = false;
	}
	
	if (email.val() != '' && !validateEmail(email)){
		errorMessage += "<strong>Errore!</strong> Formato email non valido! (<i>Es: mariorossi@studenti.unicampania.it</i>)<br/>";
		valid = false;
	}
	if (email.val() != '' && !emailDisp) {
		errorMessage += "<strong>Errore!</strong> <i>Email già registrata!</i><br/>";	
		valid = false;
	}
	if (password.val() != '' && !validatePassword(password)){
		errorMessage += "<strong>Errore!</strong> La password non rispetta il formato! (<i>Lunghezza minima 8 caratteri, almeno un numero</i>)<br/>";
		valid = false;
	}
	if (verifyPassword.val() != '' && !validatePassword(verifyPassword)){
		errorMessage += "<strong>Errore!</strong> La password di conferma non rispetta il formato! (<i>Lunghezza minima 8 caratteri, almeno un numero</i>)<br/>";
		valid = false;
	}
	if (password.val() != '' && verifyPassword.val() != '' && !checkPasswords(password, verifyPassword)){
		errorMessage += "<strong>Errore!</strong> <i>Le due password inserite non coincidono!</i><br/>";
		valid = false;
	}	
	if (firstName.val() != '' && !validateFirstName(firstName)){
		errorMessage += "<strong>Errore!</strong> Nome non consentito! (<i>2-30 caratteri con iniziale maiuscola</i>)<br/>";
		valid = false;
	}
	if (lastName.val() != '' && !validateLastName(lastName)){
		errorMessage += "<strong>Errore!</strong> Cognome non consentito! (<i>2-30 caratteri con iniziale maiuscola</i>)<br/>";
		valid = false;
	}	
	if (telephoneNumber.val() != '' && !validateTelephoneNumber(telephoneNumber)) {
		errorMessage += "\n<strong>Errore!</strong> Il numero di telefono non rispetta il formato! (<i>Es: 3332211000</i>)<br/>";
		valid = false;
	}		
	if (registrationNumber.val() != '' && !validateRegistrationNumber(registrationNumber)) {
		errorMessage += "\n<strong>Errore!</strong> Il numero di matricola non rispetta il formato! (<i>Es: 0512102493</i>)<br/>";
		valid = false;
	}
	
	if (valid) {		
		$.post("/TutoratoSmart/Registration", {
			"flag":"1",
			"AcademicYear":$("#AcademicYear").val(),
			"Email":$("#Email").val(),
			"RegistrationNumber":$("#RegistrationNumber").val(),
			"Password":$("#Password").val(),
			"FirstName":$("#FirstName").val(),
			"LastName":$("#LastName").val(),
			"Sex":$("input:radio[name='Sex']:checked").val(),
			"TelephoneNumber":$("#TelephoneNumber").val(),
			},
			function(data){	  
				if (data.result == 1) {
					$("#successDiv").fadeIn(500, function() {
						$("#successDiv").fadeOut(5000);
						setTimeout(function() {
							  window.location.href = "/TutoratoSmart/home.jsp";
						}, 5000);
					})
				}
				else {
					$("#failureDiv").fadeIn(500, function() {
						$("#failureDiv").fadeOut(5000)
					})
				}					 
			});		
	}
	
	else {
		$("#errorDiv").append(errorMessage);
		$("#errorDiv").fadeIn(500, function() {
			$("#errorDiv").fadeOut(5000)
		})
	}
}