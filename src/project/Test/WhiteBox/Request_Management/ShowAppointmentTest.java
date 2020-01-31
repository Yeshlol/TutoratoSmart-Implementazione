package project.Test.WhiteBox.Request_Management;

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

import project.Control.Request_Management.ShowAppointmentServlet;
import project.Control.DBConnection;
import project.Model.AppointmentBean;
import project.Model.UserBean;
import project.Model.UserDAO;
import project.Test.DatabaseHelper;

class ShowAppointmentTest {
	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static ShowAppointmentServlet servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new ShowAppointmentServlet();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		request = null;
		response = null;
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	
	// Visualizzazione storico appuntamenti effettuati dal tutor
	@Test
	void testFlag1ShowAppointments () throws ServletException, IOException, SQLException {
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", user);
		request.addParameter("flag", "1");
		
		servlet.doGet(request, response);
		
		@SuppressWarnings("unchecked")
		Collection<AppointmentBean> appointmentsCollection = (Collection<AppointmentBean>) request.getAttribute("appointmentsCollection");
		
		assertNotNull(appointmentsCollection);
	}
	
	// Visualizzazione dettagli appuntamento
	@Test
	void testFlag2ShowAppointment () throws ServletException, IOException, SQLException {
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", user);
		request.addParameter("flag", "2");
		request.addParameter("id", "1");
		
		servlet.doGet(request, response);
		
		AppointmentBean appointment = (AppointmentBean) request.getSession().getAttribute("appointment");
		
		assertNotNull(appointment);
	}
}
