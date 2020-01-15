package project.Test.WhiteBox;

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
import project.Model.AppointmentBean;
import project.Model.AppointmentDAO;
import project.Model.RequestBean;
import project.Model.RequestDAO;
import project.Model.UserBean;
import project.Model.UserDAO;
import project.Test.DatabaseHelper;

class AppointmentServletTest {
	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static AppointmentServlet servlet;

	@BeforeEach
	void setUp() throws Exception {
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new AppointmentServlet();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	
    //Registrazione nuovo appuntamento
	@Test
	public void testflag1success() throws ServletException, IOException, JSONException, SQLException{
		request.addParameter("flag", "1");
		request.addParameter("comment", "Appuntamento effettuato con lo studente");
		
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		RequestDAO requestDAO = new RequestDAO();
		RequestBean req = requestDAO.doRetrieveById(3);
		
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("request", req);
		
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
	
	//Modifica appuntamento
	@Test
	public void testflag2success() throws ServletException, IOException, JSONException, SQLException{
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
	
	//Cancellazione appuntamento
	@Test
	public void testflag3success() throws ServletException, IOException, JSONException, SQLException{
		request.addParameter("flag", "3");
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		AppointmentBean appointment = appointmentDAO.doRetrieveById(1);
		
		request.getSession().setAttribute("appointment", appointment);
		
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
}
