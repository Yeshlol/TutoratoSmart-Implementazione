package project.Test.WhiteBox.Tutoring_Supervision;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import project.Control.Tutoring_Supervision.StudentsServlet;
import project.Control.DBConnection;
import project.Model.StudentBean;
import project.Test.DatabaseHelper;

class StudentsServletTest {
	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static StudentsServlet servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new StudentsServlet();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		request = null;
		response = null;
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	
	// Visualizzazione registro 
	@Test
	void testSearchStudents () throws ServletException, IOException, SQLException {
		request.getSession().setAttribute("Email", "m.pisciotta@studenti.unicampania.it");
		
		servlet.doPost(request, response);
		
		@SuppressWarnings("unchecked")
		Collection<StudentBean> studentsCollection = (Collection<StudentBean>) request.getAttribute("studentsCollection");
						
		assertNotNull(studentsCollection);
	}
}
