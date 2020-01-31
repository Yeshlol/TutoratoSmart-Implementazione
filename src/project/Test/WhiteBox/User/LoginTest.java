package project.Test.WhiteBox.User;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import project.Control.DBConnection;
import project.Control.User.LoginServlet;
import project.Test.DatabaseHelper;

public class LoginTest {
	private LoginServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	

	@BeforeEach
	public void setUp() throws Exception {
		servlet = new LoginServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}

	// Lunghezza email non valida = 0
	@Test
	public void testErrorLenghtEmail() throws ServletException, IOException {
		request.addParameter("email", "");
		request.addParameter("password", "M12345678");
		
		final String message = "Lunghezza email non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
		
	// Formato email non valido
	@Test
	public void testErrorFormatEmail() throws ServletException, IOException {
		request.addParameter("email", "a.olivieri@studenti.unisa.it");
		request.addParameter("password", "M12345678");

		final String message = "Formato email non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}

	// Lunghezza password non valida < 8
	@Test
	public void testErrorLenghtPassword() throws ServletException, IOException {
		request.addParameter("email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("password", "M1234567890");

		final String message = "Lunghezza password non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}


	// Formato password non corretto
	@Test
	public void testErrorFormatPassword() throws ServletException, IOException {
		request.addParameter("email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("password", "123456789");

		final String message = "Formato password non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// Login completata con successo
	@Test
	public void testLoginSuccess() throws ServletException, IOException, JSONException {
		request.addParameter("email", "m.pisciotta@studenti.unicampania.it");
		request.addParameter("password", "M12345678");

		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
}