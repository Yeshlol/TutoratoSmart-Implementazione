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