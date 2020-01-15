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

import project.Control.DBConnection;
import project.Control.RequestServlet;
import project.Model.UserBean;
import project.Model.UserDAO;


class AddRequestTest {
	private RequestServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@BeforeEach
	void setUp() throws Exception {
		servlet = new RequestServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}

	//TC_3.0_1 Nessun giorno selezionato
	@Test
	public void testCase_3_0_1() throws ServletException, IOException {
		request.addParameter("flag","1");
		request.addParameter("time","11:00");
		request.addParameter("comment","Mi serve aiuto per l'immatricolazione");
		request.addParameter("date","");
		
		final String message = "Selezionare la data";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_3.0_2 Selezionato giorno diverso da mercoledi e giovedi
	@Test
	public void testCase_3_0_2() throws ServletException, IOException {
		request.addParameter("flag","1");
		request.addParameter("time","11:00");
		request.addParameter("comment","Mi serve aiuto per l'immatricolazione");
		request.addParameter("date","2020-01-13");
		
		final String message = "Giorno selezionato non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}

	//TC_3.0_3 Orario non selezionato
	@Test
	public void testCase_3_0_3() throws ServletException, IOException {
		request.addParameter("flag","1");
		request.addParameter("time","");
		request.addParameter("comment","Mi serve aiuto per l’immatricolazione");
		request.addParameter("date","2020-01-15");
		
		final String message = "Selezionare l’orario";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_3.0_4 Orario non valido Minuti < 540
	@Test
	public void testCase_3_0_4() throws ServletException, IOException {
		request.addParameter("flag","1");
		request.addParameter("time","06:00");
		request.addParameter("comment","Mi serve aiuto per l’immatricolazione");
		request.addParameter("date","2020-01-15");
		
		final String message = "Orario selezionato non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
		
	//TC_3.0_5 Orario non valido 765 <= Minuti <= 870 
	@Test
	public void testCase_3_0_5() throws ServletException, IOException, JSONException, SQLException {	
		request.addParameter("flag","1");
		request.addParameter("time","13:30");
		request.addParameter("comment","Mi serve aiuto per l’immatricolazione");
		request.addParameter("date","2020-01-15");
				
		final String message = "Orario selezionato non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_3.0_6 Orario non valido Minuti > 1000 
	@Test
	public void testCase_3_0_6() throws ServletException, IOException, JSONException, SQLException {	
		request.addParameter("flag","1");
		request.addParameter("time","19:00");
		request.addParameter("comment","Mi serve aiuto per l’immatricolazione");
		request.addParameter("date","2020-01-15");
				
		final String message = "Orario selezionato non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_3.0_7 	Lunghezza commento non valida = 0
	@Test
	public void testCase_3_0_7() throws ServletException, IOException, JSONException, SQLException {	
		request.addParameter("flag","1");
		request.addParameter("time","10:00");
		request.addParameter("comment","");
		request.addParameter("date","2020-01-15");
				
		final String message = "Lunghezza commento non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	//TC_3.0_8 	Lunghezza commento > 240
	@Test
	public void testCase_3_0_8() throws ServletException, IOException, JSONException, SQLException {	
		request.addParameter("flag","1");
		request.addParameter("time","10:00");
		request.addParameter("comment","Ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
				"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
				"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
				"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
				"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
				"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
				"");
		request.addParameter("date","2020-01-15");
				
		final String message = "Lunghezza commento non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_3.0_9 Successo
	@Test
	public void testCase_3_0_9() throws ServletException, IOException, JSONException, SQLException {	
		UserDAO userDAO = new UserDAO();
		UserBean user = userDAO.doRetrieveByMail("e.merola@studenti.unicampania.it");
		request.getSession().setAttribute("user", user);
		request.addParameter("flag","1");
		request.addParameter("time","10:00");
		request.addParameter("comment","Mi serve aiuto per l’immatricolazione");
		request.addParameter("date","2020-01-15");
				
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
}
