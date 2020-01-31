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
				$("#date").val(info.startStr.substring(0,10));
			    $("#time").val(info.startStr.substring(11,16));
			    $("#requestModal").modal();
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
	    slotDuration: '00:15:00',
	    slotLabelFormat: [{
	    	  hour: 'numeric',
	    	  minute: '2-digit',
	    	  omitZeroMinute: false,
	    }],
	    weekNumbers: true,
	    navLinks: true,
	    height: 450,
	    contentHeight: 'auto',
	    editable: false,
	    droppable: false,
	    selectOverlap: false,	    	    
	    eventClick: function( eventClickInfo ) { 
	    	eventClickInfo.jsEvent.preventDefault();
	    },
	    events: {
		    url: '/TutoratoSmart/CalendarTutoringRequest',
		    method: 'POST',
		    data: {
                start: 'start',
                end: 'end',
                id: 'id',
                title: 'title',
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
	$('#confirm').attr('disabled','disabled');
	
	$.post("/TutoratoSmart/RequestTutoringRequest", {
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
						$('#confirm').prop("disabled", false);
					}, 3000);
				});
			}
			
			else {
				$("#failureDiv").fadeIn(500, function() {
					$("#failureDiv").fadeOut(4000);					
					setTimeout(function() {
						$('#resultModal').modal('hide');
						$('#confirm').prop("disabled", false);
					}, 4000);
				})
			}					 
		});	
}
