package project.Test.WhiteBox.Tutoring_Supervision;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import project.Control.Tutoring_Supervision.ShowActivityServlet;
import project.Control.DBConnection;
import project.Model.ActivityTutorBean;
import project.Test.DatabaseHelper;

class ShowActivityTest {
	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static ShowActivityServlet servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new ShowActivityServlet();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		request = null;
		response = null;
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	
	// Visualizzazione attivita' 
	@Test
	void testShowActivity () throws ServletException, IOException {
		request.addParameter("id", "2");
		
		servlet.doGet(request, response);
		
		ActivityTutorBean activity = (ActivityTutorBean) request.getSession().getAttribute("activity");		
		assertNotNull(activity);
	}
}
