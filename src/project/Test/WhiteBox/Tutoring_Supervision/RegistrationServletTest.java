package project.Test.WhiteBox.Tutoring_Supervision;

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
import project.Control.Tutoring_Supervision.RegistrationServlet;
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
	
	// Lunghezza cognome < 3
	@Test
	public void testErrorLenghtLastName() throws ServletException, IOException, JSONException, SQLException {
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("d.molinaro@commissione.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		request.addParameter("flag", "2");
		request.addParameter("LastName", "Pa");
		request.addParameter("FirstName", "Francesco");
		request.addParameter("Email", "f.pagano@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");
		request.addParameter("TotalHours", "60");

		final String message = "Lunghezza cognome non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// Formato cognome non valido
	@Test
	public void testErrorFormatLastName() throws ServletException, IOException, JSONException, SQLException {
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("d.molinaro@commissione.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		request.addParameter("LastName", "pagano1");
		request.addParameter("FirstName", "Francesco");
		request.addParameter("Email", "f.pagano@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");
		request.addParameter("TotalHours", "60");
		
		final String message = "Formato cognome non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// Lunghezza nome < 3
	@Test
	public void testErrorLenghtFirstName() throws ServletException, IOException, JSONException, SQLException {
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("d.molinaro@commissione.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		request.addParameter("flag", "2");
		request.addParameter("LastName", "Pagano");
		request.addParameter("FirstName", "a");
		request.addParameter("Email", "f.pagano@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");
		request.addParameter("TotalHours", "60");

		final String message = "Lunghezza nome non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// Formato nome non valido
	@Test
	public void testErrorFormatFirstName() throws ServletException, IOException, JSONException, SQLException {
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("d.molinaro@commissione.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		request.addParameter("LastName", "Pagano");
		request.addParameter("FirstName", "francesco1");
		request.addParameter("Email", "f.pagano@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");
		request.addParameter("TotalHours", "60");

		final String message = "Formato nome non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// Registrazione nuovo Tutor completata con successo
	@Test
	public void testRegistrationSuccess() throws ServletException, IOException, JSONException, SQLException {
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
}
