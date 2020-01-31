package project.Test.WhiteBox.User;

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
import project.Control.User.RegistrationServlet;
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
	
	// Lunghezza cognome non valida < 3
	@Test
	public void testErrorLenghtLastName() throws ServletException, IOException {
		request.addParameter("LastName", "a");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Lunghezza cognome non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// Formato cognome non valido
	@Test
	public void testErrorFormatLastName() throws ServletException, IOException {
		request.addParameter("LastName", "olivieri1");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");
		
		final String message = "Formato cognome non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//Registrazione nuovo studente
	@Test
	public void testRegistrationSuccess() throws ServletException, IOException, JSONException {
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
	public void testRegistrationFail() throws ServletException, IOException, JSONException, SQLException {
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