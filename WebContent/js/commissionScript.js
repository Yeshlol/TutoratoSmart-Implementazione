$(document).ready( function() {
	$("#deleteButton").click(function(){
	    $("#deleteModal").modal();
	});
	
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();
	if(dd<10){
	   dd='0'+dd
	} 
	if(mm<10){
	   mm='0'+mm
	} 
	today = yyyy+'-'+mm+'-'+dd;
	
	document.getElementById("startDate").valueAsDate = new Date("2019-01-01");
	document.getElementById("finishDate").valueAsDate = new Date("2019-12-31");
	document.getElementById("startDate").setAttribute("max", today);
	document.getElementById("finishDate").setAttribute("max", today);
});

$("#startDate").on("change", function () {
    var startDate = new Date($(this).val());

    var dd = startDate.getDate();
	var mm = startDate.getMonth()+1;
	var yyyy = startDate.getFullYear();
	if(dd<10){
	   dd='0'+dd
	} 
	if(mm<10){
	   mm='0'+mm
	} 
	startDate = yyyy+'-'+mm+'-'+dd;
        
    document.getElementById("finishDate").setAttribute("min", startDate);
});

$("#finishDate").on("change", function () {
    var finishDate = new Date($(this).val());

    var dd = finishDate.getDate();
	var mm = finishDate.getMonth()+1; 
	var yyyy = finishDate.getFullYear();
	if(dd<10){
	   dd='0'+dd
	} 
	if(mm<10){
	   mm='0'+mm
	} 
	finishDate = yyyy+'-'+mm+'-'+dd;
        
    document.getElementById("startDate").setAttribute("max", finishDate);
});

//Funzioni di convalida e rimozione di un'attività da un registro.
function deleteActivity(){
	$.post("/TutoratoSmart/Activity", {
		"flag":"3",
		"id":$("#activityId").val(),
		"validate":"false",
		},
		function(data){	  
			if (data.result == 1) {
				$('#deleteModal').modal('hide');
				$("#successDeleteDiv").fadeIn(500, function() {
					$("#successDeleteDiv").fadeOut(3000);
					setTimeout(function() {
						  window.location.href = "/TutoratoSmart/commission/register.jsp";
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

function validateActivity(){
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
					$("#failureValidateDiv").fadeOut(5000)
				})
			}					 
		});
}
