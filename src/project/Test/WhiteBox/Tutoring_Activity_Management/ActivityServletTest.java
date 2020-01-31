package project.Test.WhiteBox.Tutoring_Activity_Management;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import project.Control.Tutoring_Activity_Management.ActivityServlet;
import project.Control.DBConnection;
import project.Model.ActivityTutorBean;
import project.Model.ActivityTutorDAO;
import project.Model.AppointmentBean;
import project.Model.AppointmentDAO;
import project.Model.RequestBean;
import project.Model.RequestDAO;
import project.Model.UserBean;
import project.Model.UserDAO;
import project.Test.DatabaseHelper;

class ActivityServletTest {
	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static ActivityServlet servlet;
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new ActivityServlet();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		request = null;
		response = null;
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	
	// Controllo altre attività registrate diverse da quella in modifica, disponibilità orario 
	@Test
	void testAjaxCheckModify() throws ServletException, IOException, SQLException, JSONException {
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		request.getSession().setAttribute("user", user);
		
		request.addParameter("ajax","true");
		request.addParameter("date","2019-11-24");
		request.addParameter("startTime","09:30");
		request.addParameter("finishTime", "13:30");
		request.addParameter("check", "true");
		request.addParameter("modify", "true");
		request.addParameter("activityId", "1");
		
		servlet.doPost(request, response);
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		boolean result = (boolean) jsonObj.get("available");
		
		assertEquals(result, false);
	}
	
	// Controllo altre attività registrate, disponibilità orario
	@Test
	void testAjaxCheck() throws ServletException, IOException, SQLException, JSONException {
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		request.getSession().setAttribute("user", user);
		
		request.addParameter("ajax","true");
		request.addParameter("date","2019-11-24");
		request.addParameter("startTime","09:30");
		request.addParameter("finishTime", "13:30");
		request.addParameter("check", "true");
		request.addParameter("modify", "false");
		request.addParameter("activityId", "1");
		
		servlet.doPost(request, response);
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		boolean result = (boolean) jsonObj.get("available");
		
		assertEquals(result, true);
	}
	
	
	// Ricerca appuntamenti in quell'orario indicato
	@Test
	void testAjax() throws ServletException, IOException, SQLException, JSONException {
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		request.getSession().setAttribute("user", user);
		
		request.addParameter("ajax","true");
		request.addParameter("time","10:00");
		request.addParameter("date","2019-11-24");
		request.addParameter("startTime","09:30");
		request.addParameter("finishTime", "13:30");
		request.addParameter("check", "false");
		
		request.addParameter("activityId", "1");
		
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
								
		assertEquals(true, content.contains("{\"firstName\":\"Gianluca\",\"lastName\":\"Luongo\",\"details\":\"Supporto immatricolazione studenti\"}"));
	} 
	
	// Registrazione nuova attività
	@SuppressWarnings("serial")
	@Test
	void testFlag1Success() throws ServletException, IOException, SQLException, JSONException {
		request.addParameter("flag", "1");
		
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		request.getSession().setAttribute("user", user);
		
		RequestDAO requestDAO = new RequestDAO();
		RequestBean requestBean = new RequestBean(6, 600, 20, "", "Mi serve aiuto", "e.merola@studenti.unicampania.it", Date.valueOf("2019-11-25"));
		
		requestDAO.doSave(requestBean);
		
		AppointmentBean appointment = new AppointmentBean(4, "Parlato con studente", 6, "m.pisciotta@studenti.unicampania.it");
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		
		appointmentDAO.doSave(appointment);
		
		Collection<AppointmentBean> appointmentsList = new ArrayList<AppointmentBean>() {{ add(appointment);}};
		
		request.getSession().setAttribute("appointmentsList", appointmentsList);
		
		request.addParameter("category", "Sportello Tutorato");
		request.addParameter("date", "2019-11-25");
		request.addParameter("startTime", "09:00");
		request.addParameter("finishTime", "11:00");
		request.addParameter("description", "Ho ricevuto uno studente");
			
		servlet.doPost(request, response);
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
	
	// Modifica attività
	@SuppressWarnings("serial")
	@Test
	void testFlag3ModifySuccess() throws ServletException, IOException, SQLException, JSONException {
		request.addParameter("flag", "3");
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		request.getSession().setAttribute("user", user);
		
		ActivityTutorBean activity = new ActivityTutorBean(4, 540, 660, 1, "Sportello Tutorato", "In valutazione", "", "m.pisciotta@studenti.unicampania.it", Date.valueOf("2019-11-25"), 2.0f);
		ActivityTutorDAO activityDAO = new ActivityTutorDAO();
		activityDAO.doSave(activity);
		
		RequestDAO requestDAO = new RequestDAO();
		RequestBean requestBean = new RequestBean(6, 600, 20, "", "Mi serve aiuto", "e.merola@studenti.unicampania.it", Date.valueOf("2019-11-25"));
		
		requestDAO.doSave(requestBean);
		
		AppointmentBean appointment = new AppointmentBean(4, "Parlato con studente", 6, "m.pisciotta@studenti.unicampania.it");
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		
		appointmentDAO.doSave(appointment);
		
		Collection<AppointmentBean> appointmentsList = new ArrayList<AppointmentBean>() {{ add(appointment);}};
		
		request.getSession().setAttribute("appointmentsList", appointmentsList);
		
		request.addParameter("id", "4");
		request.addParameter("category", "Sportello Tutorato");
		request.addParameter("date", "2019-11-25");
		request.addParameter("startTime", "09:00");
		request.addParameter("finishTime", "11:00");
		request.addParameter("description", "Ho ricevuto uno studente");
			
		servlet.doPost(request, response);
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
	
	// Cancellazione attività
	@Test
	void testFlag2Delete() throws ServletException, IOException, SQLException, JSONException {
		request.addParameter("flag", "2");
		
		ActivityTutorDAO activityDAO = new ActivityTutorDAO();
		ActivityTutorBean activity = activityDAO.doRetrieveById(1);
		
		request.getSession().setAttribute("activity", activity);
				
		servlet.doPost(request, response);
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}	
}
