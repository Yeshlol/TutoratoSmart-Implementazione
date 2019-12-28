//Listener submit
$("#loginForm form").submit(function(event){
	var valid = true;
	var errormsg = "Errore:";
	
	if(!validateEmail($("#loginForm #email"))){
		valid = false;
		errormsg+="\n-Formato email non valido";
	}

	if(!validatePassword($("#loginForm #password"))){
		valid=false;
		errormsg+="\n-Formato password non valido";
	}

	if(valid)
		$("#loginForm form").submit();
	else{
		alert(errormsg);
		event.preventDefault();
	}
});

$("#loginFormPage form").submit(function(event){
	var valid = true;
	var errormsg = "Errore:";
	
	if(!validateEmail($("#loginFormPage #email"))){
		valid = false;
		$(".errorEmail").fadeIn(2000, function() {
			$(".errorEmail").fadeOut(2000)
		  })
	}

	if(!validatePassword($("#loginFormPage #password"))){
		valid = false;
		errormsg+="\n-Formato password non valido";
	}

	if(valid)
		$("#loginFormPage form").submit();
	else{
		event.preventDefault();
	}
});
