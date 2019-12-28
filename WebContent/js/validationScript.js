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
