$(document).ready(function(){
	$("#Email").trigger('keyup');
});


$("#Email").on('keyup',function(){
	 if ($(this).val() == "" ){
		 $(this).css("border","0");
		 $("#mailLabel").html('');
	}else
	$.post("/TutoratoSmart/RegistrationServlet",{ 
		"mail":$(this).val(),
		"ajax":"true"
		  },
		  function(data){
			  var color;
			  
			  if( validateEmail($("#mail"))&& data.disponibile ){
				  color = "1px solid green";
				  $("#mailLabel").html('Email non registrata').css("color","green");
			  }
			  else{
				  $("#mailLabel").html('');
				 color = "1px solid red";
				 if(!data.disponibile)
					 $("#mailLabel").html('Email già registrata').css("color","red");
			  }			 
			 
			$("#mail").css("border",color);
			emailDisp = data.disponibile;
		  });
});


$("#Password").on('keyup',function(){
	var color;
	if( validatePassword($(this)))
		color = "1px solid green";
	else
		color = "1px solid red";
	
	if ($(this).val() == "")
		color = "0";
	
	$(this).css("border",color);
});


$("#FirstName").on(' keyup  ',function(){
	var color;
	if( validateName($(this)))
		color = "1px solid green";
	else
		color = "1px solid red";
	
	if ( $(this).val() == "")
		color = "0";
	
	$(this).css("border",color);
});

$("#LastName").on(' keyup  ',function(){
	var color;
	if( validateSurname($(this)))
		color = "1px solid green";
	else
		color = "1px solid red";
	
	if ( $(this).val() == "")
		color = "0";
	
	$(this).css("border",color);
});


$("#TelephoneNumber").on('keyup',function(){
	var color;
	if( validatePhoneNumber($(this)))
		color = "1px solid green";
	else
		color = "1px solid red";
	
	if ( $(this).val() == "")
		color = "0";
	
	$(this).css("border",color);
});


$("#RegistrationNumber").on('keyup',function(){
	var color;
	if( validateRegistrationNumber($(this)))
		color = "1px solid green";
	else
		color = "1px solid red";
	
	if ( $(this).val() == "")
		color = "0";
	
	$(this).css("border",color);
});