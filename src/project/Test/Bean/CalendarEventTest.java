package project.Test.Bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.Model.CalendarEvent;


class CalendarEventTest {
	@Test
	void testCalendarEvent() {
		CalendarEvent ce = new CalendarEvent();
		assertNotNull(ce);		
	}

	@Test
	void testTitle() {
		CalendarEvent ce = new CalendarEvent();
		ce.setTitle("Titolo");		
		assertEquals("Titolo", ce.getTitle());
	}
	
	@Test
	void testStart() {
		CalendarEvent ce = new CalendarEvent();
		ce.setStart("20:01");		
		assertEquals("20:01", ce.getStart());
	}
	
	@Test
	void testEnd() {
		CalendarEvent ce = new CalendarEvent();
		ce.setEnd("20:01");		
		assertEquals("20:01", ce.getEnd());
	}
	
	@Test
	void testColor() {
		CalendarEvent ce = new CalendarEvent();
		ce.setColor("blue");		
		assertEquals("blue", ce.getColor());
	}
	
	@Test
	void testTextColor() {
		CalendarEvent ce = new CalendarEvent();
		ce.setTextColor("blue");		
		assertEquals("blue", ce.getTextColor());
	}
	
	@Test
	void testUrl() {
		CalendarEvent ce = new CalendarEvent();
		ce.setUrl("showEvent?id=2");		
		assertEquals("showEvent?id=2", ce.getUrl());
	}
	
	@Test
	void testDescription() {
		CalendarEvent ce = new CalendarEvent();
		ce.setDescription("Un evento importante");		
		assertEquals("Un evento importante", ce.getDescription());
	}
}
