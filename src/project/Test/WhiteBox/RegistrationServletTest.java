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

import project.Control.DBConnection;
import project.Control.RegistrationServlet;
import project.Model.UserBean;
import project.Model.UserDAO;
import project.Test.DatabaseHelper;

class RegistrationServletTest {
	
	static MockHttpServletRequest request;
	static MockHttpServletResponse response;
	static RegistrationServlet servlet;

	@BeforeEach
	void setUp() throws Exception {
		request= new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		servlet= new RegistrationServlet();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		request=null;
		response=null;
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}
	
	//Registrazione nuovo studente
	@Test
	public void testFlag1Success() throws ServletException, IOException, JSONException {
		request.addParameter("flag", "1");
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");
		request.addParameter("Sex", "F");
		request.addParameter("AcademicYear", "6");
		
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
	
	//Registrazione nuovo Tutor
	@Test
	public void testFlag2Success() throws ServletException, IOException, JSONException, SQLException {
		request.addParameter("flag", "2");
		request.addParameter("LastName", "Pagano");
		request.addParameter("FirstName", "Francesco");
		request.addParameter("Email", "f.pagano@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");
		request.addParameter("TotalHours", "60");
		request.addParameter("StartDate", "2020-01-20");
		request.addParameter("Sex", "M");
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("d.molinaro@commissione.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
	
	// Controllo disponibilità email inserita
	@Test
	public void testFlagAjaxSuccess() throws ServletException, IOException, JSONException, SQLException {
		request.addParameter("ajax", "true");
		request.addParameter("mail", "m.pisciotta@studenti.unicampania.it");			
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		boolean result =  (boolean) jsonObj.get("disponibile");
		
		assertEquals(result, false);
	}
	
	// Eccezione SQL 
	@Test
	public void testFlag1Unsuccess() throws ServletException, IOException, JSONException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");
		request.addParameter("Sex", "X");
		request.addParameter("AcademicYear", "6");
		
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 2);
	}
}
