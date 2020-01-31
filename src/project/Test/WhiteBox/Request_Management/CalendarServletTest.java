package project.Test.WhiteBox.Request_Management;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import project.Control.Request_Management.CalendarServlet;
import project.Control.DBConnection;
import project.Test.DatabaseHelper;

class CalendarServletTest {
	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static CalendarServlet servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new CalendarServlet();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		request = null;
		response = null;
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	
	// Visualizzazione storico richieste di appuntamento 
	@Test
	void testFlag1ShowRequests () throws ServletException, IOException, SQLException, JSONException {
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		System.out.println("Content: " + content);
		
		assertEquals(true, content.contains("{\"title\":\"Appuntamento\",\"start\":\"2019-11-24T12:00\",\"end\":\"2019-11-24T13:20\",\"color\":\"green\",\"textColor\":\"\",\"url\":\"/TutoratoSmart/ShowRequestRequestManagement?flag\\u003d1\\u0026id\\u003d1\",\"description\":\"Appuntamento con Luongo Gianluca\"}"));
	}
}