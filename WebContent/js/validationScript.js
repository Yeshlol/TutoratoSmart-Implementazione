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

function checkPasswords(password, verifyPassword){
	boolean = password.val() == verifyPassword.val()
	
	return boolean;
}

function validateEmail(email) {
	var mailFormat1 = /^\w+([\.-]?\w+)*@studenti.unicampania.it$/;
	var mailFormat2 = /^\w+([\.-]?\w+)*@commissione.unicampania.it$/;
	var mailFormat3 = /^\w+([\.-]?\w+)*@unicampania.it$/;
	
	if (email.val().match(mailFormat1) || email.val().match(mailFormat2) || email.val().match(mailFormat3))
		return true;
	else 
		return false;
}

function validateFirstName(firstName) {
	var firstNameFormat = /^[A-Z]{1}[a-zA-Z\s]{2,30}$/;
	
	if (firstName.val().match(firstNameFormat))
		return true;
	else 	
		return false;
}

function validateLastName(lastName) {
	var lastNameFormat = /^[A-Z]{1}[a-zA-Z\s]{2,30}$/;
	
	if (lastName.val().match(lastNameFormat))
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

function validateRegistrationNumber(registrationNumber) {
	var registrationNumberFormat = /^[AB]\d{5,9}$/;
	
	if (registrationNumber.val().match(registrationNumberFormat))
		return true;
	else	
		return false;
}

function validateTelephoneNumber(phone) {
	var phoneFormat = /^\d{9,10}$/;
	
	if (phone.val().match(phoneFormat))
		return true;
	else	
		return false;
}
