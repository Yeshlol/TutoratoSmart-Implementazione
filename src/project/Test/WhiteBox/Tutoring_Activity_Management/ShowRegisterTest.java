package project.Test.WhiteBox.Tutoring_Activity_Management;

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

import project.Control.Tutoring_Activity_Management.ShowRegisterServlet;
import project.Control.DBConnection;
import project.Model.ActivityTutorBean;
import project.Model.TutorBean;
import project.Model.UserBean;
import project.Model.UserDAO;
import project.Test.DatabaseHelper;

class ShowRegisterTest {
	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static ShowRegisterServlet servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new ShowRegisterServlet();
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
	void testShowRegister () throws ServletException, IOException, SQLException {
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		request.getSession().setAttribute("user", user);
		
		servlet.doGet(request, response);
		
		@SuppressWarnings("unchecked")
		Collection<ActivityTutorBean> activitiesCollection = (Collection<ActivityTutorBean>) request.getAttribute("activitiesCollection");
		TutorBean tutor = (TutorBean) request.getAttribute("tutor");
				
		assertNotNull(activitiesCollection);
		assertNotNull(tutor);
	}
}
