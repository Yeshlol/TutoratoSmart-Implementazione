//Funzioni di convalida e rimozione di un'attività da un registro.
function deleteActivity(){
	$('#deleteButton').attr('disabled','disabled');
	$('#validateButton').attr('disabled','disabled');
	$('#back').attr('disabled','disabled');
	
	$.post("/TutoratoSmart/Activity", {
		"flag":"3",
		"id":$("#activityId").val(),
		"validate":"false",
		},
		function(data){
			$('#deleteModal').modal('hide');
			if (data.result == 1) {				
				$("#successDeleteDiv").fadeIn(500, function() {
					$("#successDeleteDiv").fadeOut(3000);
					setTimeout(function() {
						  window.location.href = "/TutoratoSmart/commission/register.jsp";
					}, 3000);
				})
			}
			else {
				$("#failureDeleteDiv").fadeIn(500, function() {
					$('#deleteButton').prop("disabled", false);
					$('#validateButton').prop("disabled", false);
					$('#back').prop("disabled", false);
					$("#failureDeleteDiv").fadeOut(5000)
				})
			}					 
		});
}

function validateActivity(){
	$('#deleteButton').attr('disabled','disabled');
	$('#validateButton').attr('disabled','disabled');
	$('#back').attr('disabled','disabled');
	
	$.post("/TutoratoSmart/Activity", {
		"flag":"3",
		"id":$("#activityId").val(),
		"validate":"true",
		},
		function(data){	  
			if (data.result == 1) {
				$("#successValidateDiv").fadeIn(500, function() {
					$("#successValidateDiv").fadeOut(3000);
					setTimeout(function() {
						  window.location.href = "/TutoratoSmart/commission/register.jsp";
					}, 3000);
				})
			}
			else {
				$("#failureValidateDiv").fadeIn(500, function() {
					$('#deleteButton').prop("disabled", false);
					$('#validateButton').prop("disabled", false);
					$('#back').prop("disabled", false);
					$("#failureValidateDiv").fadeOut(5000)
				})
			}					 
		});
}
