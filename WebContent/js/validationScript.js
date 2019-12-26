var usernameDisp = false;
var emailDisp = false;


function upperCaseF(a){
    setTimeout(function(){
        a.value = a.value.toUpperCase();
    }, 1);
}

function lowerCaseF(a){
    setTimeout(function(){
        a.value = a.value.toLowerCase();
    }, 1);
}

function validateAddress(address) {
	var addressFormat = /^[A-Za-z\s\.]{2,95},\s[0-9]{1,4}\/*[a-zA-Z]*$/;
	
	if (address.val().match(addressFormat))
		return true;
	else
		return false;	
}

function validateCap(cap) {
	var capFormat = /^[0-9]{5}$/;
	
	if (cap.val().match(capFormat))
		return true;
	else
		return false;
}

function validateCity(city) {
	var cityFormat = /^[A-Za-z\s]{2,45}$/;
	
	if (city.val().match(cityFormat))
		return true
	else 		
		return false;
}

function validateDateOfBirth(date) {
	var born = new Date(date.val());
	var now = new Date();    
    var age = Math.floor((now.getTime() - born.getTime()) / (365.25 * 24 * 60 * 60 * 1000));
    
    if (age < 18 || date.val()=="" )    	
    	return false;
    else 
    	return true;
}

function validateEmail(email) {
	var mailFormat = /^\w+([\.-]?\w+)*@studenti.unicampania.it$/;
	
	if (email.val().match(mailFormat))
		return true;
	else 
		return false;
}

function validateFiscalCode(fiscalCode) {
	var fiscalCodeFormat = /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/i;
	
	if (fiscalCode.val().match(fiscalCodeFormat))
		return true;
	else 
		return false;	
}

function validateName(name) {
	var nameFormat = /^[A-Z]{1}[a-zA-Z\s]{2,20}$/;
	
	if (name.val().match(nameFormat))
		return true;
	else 	
		return false;
}

function validatePassword(password) {
	var passwordFormat =  /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,10}$/;
	
	if (password.val().match(passwordFormat))
		return true;
	else 	
		return false;	
}

function validatePhoneNumber(phone) {
	var phoneFormat = /^(\d{0}|\d{3,4}\/\d{7,8})$/;
	
	if (phone.val().match(phoneFormat))
		return true;
	else 
		return false;	
}

function validateProvince(province) {
	var provinceFormat = /^[A-Z]{2}$/;
	
	if (province.val().match(provinceFormat))
		return true;
	else	
		return false;
}

function validateRegistrationNumber(province) {
	var provinceFormat = /^\d{10}$/;
	
	if (province.val().match(provinceFormat))
		return true;
	else	
		return false;
}

function validateSurname(surname) {
	var surnameFormat = /^[A-Z]{1}[a-zA-Z\s]{2,20}$/;
	
	if (surname.val().match(surnameFormat))
		return true;
	else 		
		return false;	
}

function validateUsername(username) {
	var usernameFormat = /^\w{3,14}$/;
	if (username.val().match(usernameFormat))
		return true;
	else
		return false;
}


//Validazione Input
function validateInputs(){
	var valid = true;
	
	var username = $("#usernameReg");
	var password = $("#passwordReg");
	var email = $("#mail"); 
	var numeroTelefono = $("#numeroTelefono");
	var cittaResidenza = $("#cittaResidenza");
	var provincia = $("#provincia");
	var cap = $("#cap");
	var indirizzo = $("#indirizzo");
	
	var fiscalCode = $("#codiceFiscale");
	var name = $("#nome");
	var surname = $("#cognome");
	var date = $("#dataNascita");
	
	var error = "Errore, uno o più campi non validi:\n";
	if(!validateUsername(username)||!usernameDisp){
		error+="Username\n";
		valid=false;
	}
	if(!validatePassword(password)){
		error+="Password\n";
		valid=false;
	}
	if(!validateEmail(email)||!emailDisp){
		error+="Email\n";	
		valid=false;
	}
	if( !validatePhoneNumber(numeroTelefono)){
		error+="Telefono\n";
		valid=false;
	}
	if(!validateCity(cittaResidenza)){
		error+="Città\n";	
		valid=false;
	}	
	if(!validateCap(cap)){
		error+="CAP\n";
		valid=false;
	}	
	if(!validateAddress(indirizzo)){
		error+="Indirizzo\n";
		valid=false;
	}	
	if(!validateFiscalCode(fiscalCode)){
		error+="Codice Fiscale\n";
		valid = false;
	}
	if(!validateName(name)){
		error+="Nome\n";
		valid = false;
	}
	if(!validateSurname(surname)){
		error+="Cognome\n";
		valid = false;
	}
	if(!validateDateOfBirth(date)){
		error+="Data di nascita\n";
		valid = false;
	}
	
	if(valid) {		
		$("#registerForm form").submit();
	}
	else
		alert(error);
}