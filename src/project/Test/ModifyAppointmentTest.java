package project.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import project.Control.AppointmentServlet;
import project.Control.DBConnection;
import project.Model.*;


class ModifyAppointmentTest {
	private AppointmentServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	

	@BeforeEach
	void setUp() throws Exception {
		servlet = new AppointmentServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	
	//TC_7.0_1 Lunghezza commento non valida = 0
	@Test
	public void testCase_7_0_1() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "2");
		request.addParameter("comment", "");
		
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		AppointmentBean appointment = appointmentDAO.doRetrieveById(1);
		
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("appointment", appointment);
				
		final String message = "Lunghezza commento non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_7.0_1 Lunghezza commento non valida > 240
	@Test
	public void testCase_7_0_2() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "2");
		request.addParameter("comment", "Ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
		
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		AppointmentBean appointment = appointmentDAO.doRetrieveById(1);
		
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("appointment", appointment);
				
		final String message = "Lunghezza commento non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_7.0_3 Successo
	@Test
	public void testCase_7_0_3() throws ServletException, IOException, JSONException, SQLException{
		request.addParameter("flag", "2");
		request.addParameter("comment", "Appuntamento effettuato con lo studente");
		
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		AppointmentBean appointment = appointmentDAO.doRetrieveById(1);
		
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("appointment", appointment);
		
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
}
