package project.Test.Bean;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import project.Model.Calendar;

import org.junit.jupiter.api.Test;


class CalendarTest {	
	@Test
	void testGetRequests() throws SQLException {
		Calendar calendar = Calendar.getInstance();
		calendar.reset();
		assertNotNull(calendar.getRequests());
	}
}
