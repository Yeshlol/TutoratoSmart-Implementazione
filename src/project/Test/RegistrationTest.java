/*package project.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import project.Control.DBConnection;
import project.Control.RegistrationServlet;

public class RegistrationTest {
	private RegistrationServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;


	//CONTROLLARE MESSAGGI
	final String message = "Formato errato dati";
	final String successMessage="successo";

	@BeforeEach
	public void setUp() throws Exception {
		servlet=new RegistrationServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}

	//TC_2.0_1 Lunghezza cognome insufficiente
	@Test
	public void testCase_1() throws ServletException, IOException {
		request.addParameter("LastName", "O1");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");	
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		
		
		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_2.0_2 Lunghezza cognome errata
	@Test
	public void testCase_2() throws ServletException, IOException {
		request.addParameter("LastName", "asdfghjklmnbvcxzqwertyuioplkjh");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_2.0_3 Formato cognome non corretto
	@Test
	public void testCase_3() throws ServletException, IOException {
		request.addParameter("LastName", "olivieri1");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");


		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_2.0_4 Lunghezza nome insufficiente
	@Test
	public void testCase_4() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "b");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_2.0_5 Lunghezza nome errata
	@Test
	public void testCase_5() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "asdfghjklmnbvcxzqwertyuioplkjh");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");


		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}


	//TC_2.0_6 Formato nome non corretto
	@Test
	public void testCase_6() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "alessia1");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");


		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}

	//TC_2.0_7 Lunghezza email insufficiente
	@Test
	public void testCase_7() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}
	
	
	//TC_2.0_8 Lunghezza email non corretta
	@Test
	public void testCase_8() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieriabcdertghilp@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}
	
	//TC_2.0_9 Formato email non corretto
	@Test
	public void testCase_9() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unisa.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}
	
	//TC_2.0_10 Lunghezza password insufficiente
	@Test
	public void testCase_10() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}
	//TC_2.0_11 Lunghezza password >10
	@Test
	public void testCase_11() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M1234567890");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}
	//TC_2.0_12 Formato password errato
	@Test
	public void testCase_12() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}
	
	//TC_2.0_13 Le due password inserite non corrispondono
	@Test
	public void testCase_13() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}
	
	//TC_2.0_14 Lunghezza matricola insufficiente
	@Test
	public void testCase_14() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A5121");
		request.addParameter("TelephoneNumber", "3465891013");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}
	//TC_2.0_15 Lunghezza matricola>10
	@Test
	public void testCase_15() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A5121012121");
		request.addParameter("TelephoneNumber", "3465891013");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}
	
	//TC_2.0_16 Formato matricola errato
	@Test
	public void testCase_16() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "0512105111");
		request.addParameter("TelephoneNumber", "3465891013");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}
	
	//TC_2.0_17 Lunghezza numero di telefono insufficiente
	@Test
	public void testCase_17() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "34658910");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}
	//TC_2.0_18 Lunghezza numero di telefono non corretto
	@Test
	public void testCase_18() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "34658910321");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}
	//TC_2.0_19 Formato numero di telefono non corretto
	@Test
	public void testCase_19() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "346589101a");

		servlet.doPost(request, response);

		assertEquals(message, response.getContentAsString());
	}
	
	//TC_2.0.20 Successo
	@Test
	public void testCase_20() throws ServletException, IOException {
		request.addParameter("LastName", "Olivieri");
		request.addParameter("FirstName", "Alessia");
		request.addParameter("Email", "a.olivieri@studenti.unicampania.it");
		request.addParameter("Password", "M12345678");
		request.addParameter("RegistrationNumber", "A512101212");
		request.addParameter("TelephoneNumber", "3465891013");


		servlet.doPost(request, response);

		assertEquals(successMessage, (String) request.getAttribute("result"));
	}

	@AfterEach
	public void tearDown() throws Exception{
		DBConnection.setTest(false);
	}
}*/