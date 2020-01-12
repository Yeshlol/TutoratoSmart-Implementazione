document.addEventListener('DOMContentLoaded', function() {
	/* initialize the calendar
	-----------------------------------------------------------------*/
	var calendarEl = document.getElementById('calendar');

	var calendar = new FullCalendar.Calendar(calendarEl, {
		plugins: [ 'dayGrid', 'timeGrid', 'list' ],
		header: {
			left: 'prev,next today',
		    center: 'title',
		    right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
		},
		eventLimit: 2,
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
		defaultView: 'dayGridMonth',
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
	    slotLabelInterval: '00:15:00',
		weekNumbers: true,
	    navLinks: true,
	    height: 450,
	    contentHeight: 'auto',
	    editable: false,
	    droppable: false,
	    eventMouseEnter: function(mouseEnterInfo ) {
	    	$("#info").show();
	    	$("#info").css({
	    	      top: mouseEnterInfo.jsEvent.pageY + 10,
	    	      left: mouseEnterInfo.jsEvent.pageX + 10,
	    	      background: "white",
	    	      border: "1px solid #232F3E",
	    	});
	        $('#info').html(mouseEnterInfo.event.extendedProps.description);
	    },
	    eventMouseLeave: function(mouseLeaveInfo) {
	    	$('#info').html("");
	    	$("#info").hide();
	    },
	    eventClick: function(eventClickInfo) {
	    	eventClickInfo.jsEvent.preventDefault();
	    	window.open(eventClickInfo.event.url, "_self");
	    },
	 	events: {
		    url: '/TutoratoSmart/Calendar',
		    method: 'POST',
		    extraParams: {
		        flag: '1'
		    },
		    data: {
		    	color: 'color',
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
		
	calendar.render();
});