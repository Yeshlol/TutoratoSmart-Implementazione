$(document).ready(function(){
	$("#deleteButton").click(function(){
		$("#deleteModal").modal();
	});
});

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