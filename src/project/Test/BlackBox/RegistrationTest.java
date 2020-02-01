package project.Test.BlackBox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import project.Control.User.RegistrationServlet;
import project.Test.DatabaseHelper;


public class RegistrationTest {
	private RegistrationServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@BeforeEach
	public void setUp() throws Exception {
		servlet=new RegistrationServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}

	//TC_2.0_1 Lunghezza cognome non valida < 3
	@Test
	public void testCase_2_0_1() throws ServletException, IOException {
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

	//TC_2.0_2 Lunghezza cognome non valida > 30
	@Test
	public void testCase_2_0_2() throws ServletException, IOException {
		request.addParameter("LastName", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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

	//TC_2.0_3 Formato cognome non valido
	@Test
	public void testCase_2_0_3() throws ServletException, IOException {
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

	//TC_2.0_4 Lunghezza nome < 3
	@Test
	public void testCase_2_0_4() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "a");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Lunghezza nome non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}

	//TC_2.0_5 Lunghezza nome non valida > 30
	@Test
	public void testCase_2_0_5() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Lunghezza nome non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}

	//TC_2.0_6 Formato nome non valido
	@Test
	public void testCase_2_0_6() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "alessia1");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Formato nome non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}

	//TC_2.0_7 Lunghezza email non valida = 0
	@Test
	public void testCase_2_0_7() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Lunghezza email non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}	
	
	//TC_2.0_8 Lunghezza email non valida > 45
	@Test
	public void testCase_2_0_8() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieriaaaaaaaaaaaa@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Lunghezza email non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_2.0_9 Formato email non valido
	@Test
	public void testCase_2_0_9() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unisa.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Formato email non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_2.0_10 Lunghezza password non valida < 8
	@Test
	public void testCase_2_0_10() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345");
		request.addParameter("VerifyPassword", "M12345");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Lunghezza password non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_2.0_11 Lunghezza password non valida > 10
	@Test
	public void testCase_2_0_11() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M1234567890");
		request.addParameter("VerifyPassword", "M1234567890");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Lunghezza password non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_2.0_12 Formato password non valido
	@Test
	public void testCase_2_0_12() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "12345678");
		request.addParameter("VerifyPassword", "12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Formato password non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_2.0_13 Le due password inserite non corrispondono
	@Test
	public void testCase_2_0_13() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M123456789");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Le due password inserite non corrispondono";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_2.0_14 Lunghezza matricola non valida < 6
	@Test
	public void testCase_2_0_14() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A5121");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Lunghezza matricola non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_2.0_15 Lunghezza matricola non valida > 10
	@Test
	public void testCase_2_0_15() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A5121012121");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Lunghezza matricola non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_2.0_16 Formato matricola non valido
	@Test
	public void testCase_2_0_16() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "0512105111");
		request.addParameter("TelephoneNumber", "3465891013");

		final String message = "Formato matricola non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_2.0_17 Lunghezza numero di telefono non valida < 9
	@Test
	public void testCase_2_0_17() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "34658910");

		final String message = "Lunghezza numero di telefono non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_2.0_18 Lunghezza numero di telefono non valida > 10
	@Test
	public void testCase_2_0_18() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "34658910321");

		final String message = "Lunghezza numero di telefono non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_2.0_19 Formato numero di telefono non valido
	@Test
	public void testCase_2_0_19() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("VerifyPassword", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "346589101a");

		final String message = "Formato numero di telefono non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
		
	//TC_2.0.20 Successo
	@Test
	public void testCase_2_0_20() throws ServletException, IOException, JSONException {
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
}