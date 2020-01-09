document.addEventListener('DOMContentLoaded', function() {	
	/* initialize the calendar
	-----------------------------------------------------------------*/
	var calendarEl = document.getElementById('calendar');
	
	var calendar = new FullCalendar.Calendar(calendarEl, {
		plugins: [ 'interaction', 'dayGrid', 'timeGrid' ],
		selectable: true,
		selectConstraint: "businessHours",
	    select: function(info) {
			var selected = info.start.getTime();
			var now = new Date().getTime();
						
			if(selected > now) {
				$("#requestModal").modal();
			    $("#date").val(info.startStr.substring(0,10));
			    $("#time").val(info.startStr.substring(11,16));
			}		
	    },
		header: {
			left: 'prev,next today',
		    center: 'title',
		    right: 'timeGridWeek,timeGridDay'
		},
		buttonText: {
			prev: 'Precedente',
			next: 'Successivo',
			today: 'Oggi',
			month: 'Mese',
			timeGridWeek: 'Settimana',
			day: 'Giorno',
			listWeek: 'Agenda',
		},
		locale: 'it',
		defaultView: 'timeGridWeek',		
		allDaySlot: false,
		allDayDefault: false,
		firstDay: 1,
		hiddenDays: [ 0 ],
		minTime: '09:00:00',
		maxTime: '17:00:00',
		businessHours: true,
		businessHours: [
			{
				daysOfWeek: [ 3, 4 ],
				startTime: '09:00',
				endTime: '13:00'
			},
			{
				daysOfWeek: [ 3, 4 ],
				startTime: '14:30',
				endTime: '17:00'
			}
		],
		weekNumbers: true,
	    navLinks: true,	    
	    
	    slotDuration: '00:15:00',
	    slotLabelFormat: [{
	    	  hour: 'numeric',
	    	  minute: '2-digit',
	    	  omitZeroMinute: false,
	    }],
	    height: 450,
	    contentHeight: 'auto',	    
	    
	    selectOverlap: false,
	    	    
	    eventClick: function( eventClickInfo ) { 
	    	eventClickInfo.jsEvent.preventDefault();
	    },
	    
	 	events: {
		    url: '/TutoratoSmart/Calendar',
		    method: 'POST',
		    extraParams: {
		        flag: '1'
		    },
		    data: {
                start: 'start',
                end: 'end',
                id: 'id',
                title: 'title',
                description: 'description'
            },
            failure: function() {
		      alert('there was an error while fetching events!');
		    },
		}	 	
	});
	
	$('#resultModal').on('hidden.bs.modal', function() {
		calendar.refetchEvents();
	});
	
	calendar.render();
});


function createRequest() {	
	$.post("/TutoratoSmart/Request", {
		"flag":"1",
		"comment":$("#comment").val(),
		"date":$("#date").val(),
		"time":$("#time").val(),
		},
		function(data) {
			$('#requestModal').modal('hide');
			$('#resultModal').modal();
			
			if (data.result == 1) {	
				$("#successDiv").fadeIn(500, function() {
					$("#successDiv").fadeOut(3000);					
					setTimeout(function() {
						$('#resultModal').modal('hide');
					}, 3000);
				});
			}
			
			else {
				$("#failureDiv").fadeIn(500, function() {
					$("#failureDiv").fadeOut(4000);					
					setTimeout(function() {
						$('#resultModal').modal('hide');
					}, 4000);
				})
			}					 
		});	
}
