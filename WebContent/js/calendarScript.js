document.addEventListener('DOMContentLoaded', function() {
	var initialLocaleCode = 'it';
	
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
		weekNumbers: true,
	    navLinks: true,
	    editable: false,
	    droppable: false,
	    
	    height: 450,
	    contentHeight: 'auto',
	    eventMouseEnter: function(mouseEnterInfo ) {
	    	$("#info").css({
	    	      top: mouseEnterInfo.jsEvent.pageY + 15,
	    	      left: mouseEnterInfo.jsEvent.pageX + 15,
	    	      background: "white",
	    	      border: "1px solid blue",
	    	});
	        $('#info').html(mouseEnterInfo.event.extendedProps.description);
	    },
	    eventMouseLeave: function(mouseLeaveInfo) {
	    	$('#info').html("");
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