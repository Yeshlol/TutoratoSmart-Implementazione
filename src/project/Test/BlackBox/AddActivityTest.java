package project.Test.BlackBox;

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

import project.Control.Tutoring_Activity_Management.ActivityServlet;
import project.Control.DBConnection;
import project.Model.UserBean;
import project.Model.UserDAO;
import project.Test.DatabaseHelper;

class AddActivityTest {
	private ActivityServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	

	@BeforeEach
	void setUp() throws Exception {
		servlet = new ActivityServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}

	@AfterEach
	void tearDown() throws Exception {
		DatabaseHelper.resetDatabase();
		DBConnection.setTest(false);
	}

	// TC_8.0_1 Categoria non selezionata
	@Test
	public void testCase_8_0_1() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("category", "");
		request.addParameter("date", "2020-01-20");
		request.addParameter("startTime", "09:00");
		request.addParameter("finishTime", "11:00");
		request.addParameter("description", "Giornata open-day");
		
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.lombardo@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		final String message = "Selezionare la categoria";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_8.0_2 Data non selezionata
	@Test
	public void testCase_8_0_2() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("category", "Evento");
		request.addParameter("date", "");
		request.addParameter("startTime", "09:00");
		request.addParameter("finishTime", "11:00");
		request.addParameter("description", "Giornata open-day");
		
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.lombardo@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		final String message = "Selezionare la data";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_8.0_3 Orario di inizio attivita non selezionato
	@Test
	public void testCase_8_0_3() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("category", "Evento");
		request.addParameter("date", "2020-01-20");
		request.addParameter("startTime", "");
		request.addParameter("finishTime", "11:00");
		request.addParameter("description", "Giornata open-day");
		
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.lombardo@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		final String message = "Inserire l’orario di inizio attivita";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_8.0_4 Orario di inizio attivita non valido precedente 7:30
	@Test
	public void testCase_8_0_4() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("category", "Evento");
		request.addParameter("date", "2020-01-20");
		request.addParameter("startTime", "06:00");
		request.addParameter("finishTime", "11:00");
		request.addParameter("description", "Giornata open-day");
		
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.lombardo@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		final String message = "Orario di inizio attivita non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_8.0_5 Orario di inizio attivita non valido successivo alle 22:00
	@Test
	public void testCase_8_0_5() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("category", "Evento");
		request.addParameter("date", "2020-01-20");
		request.addParameter("startTime", "23:00");
		request.addParameter("finishTime", "24:00");
		request.addParameter("description", "Giornata open-day");
		
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.lombardo@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		final String message = "Orario di inizio attivita non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_8.0_6 Orario di fine attivita non selezionato
	@Test
	public void testCase_8_0_6() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("category", "Evento");
		request.addParameter("date", "2020-01-20");
		request.addParameter("startTime", "09:00");
		request.addParameter("finishTime", "");
		request.addParameter("description", "Giornata open-day");
		
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.lombardo@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		final String message = "Inserire l’orario di fine attivita";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_8.0_7 Orario di fine attivita non valido precedente 7:30
	@Test
	public void testCase_8_0_7() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("category", "Evento");
		request.addParameter("date", "2020-01-20");
		request.addParameter("startTime", "09:00");
		request.addParameter("finishTime", "06:00");
		request.addParameter("description", "Giornata open-day");
		
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.lombardo@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		final String message = "Orario di fine attivita non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
		
	// TC_8.0_8 Orario di fine attivita non valido successivo alle 22:00
	@Test
	public void testCase_8_0_8() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("category", "Evento");
		request.addParameter("date", "2020-01-20");
		request.addParameter("startTime", "09:00");
		request.addParameter("finishTime", "23:00");
		request.addParameter("description", "Giornata open-day");
		
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.lombardo@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		final String message = "Orario di fine attivita non valido";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_8.0_9 Orari inseriti non validi (Orario di fine precedente a orario di inizio)
	@Test
	public void testCase_8_0_9() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("category", "Evento");
		request.addParameter("date", "2020-01-20");
		request.addParameter("startTime", "11:00");
		request.addParameter("finishTime", "09:00");
		request.addParameter("description", "Giornata open-day");
		
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.lombardo@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		final String message = "Orari inseriti non validi";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_8.0_10 Orari inseriti non validi (Orario di fine precedente a orario di inizio)
	@Test
	public void testCase_8_0_10() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("category", "Evento");
		request.addParameter("date", "2020-01-20");
		request.addParameter("startTime", "09:00");
		request.addParameter("finishTime", "11:00");
		request.addParameter("description", "");
		
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.lombardo@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		final String message = "Lunghezza commento non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_8.0_11 Orari inseriti non validi (Orario di fine precedente a orario di inizio)
	@Test
	public void testCase_8_0_11() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		request.addParameter("category", "Evento");
		request.addParameter("date", "2020-01-20");
		request.addParameter("startTime", "09:00");
		request.addParameter("finishTime", "11:00");
		request.addParameter("description", "Ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
							"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
							"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
							"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
							"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
							"ppppppppppppppppppppppppppppppppppppp\r\n" + 
							"");
		
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.lombardo@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		final String message = "Lunghezza commento non valida";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {
			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	// TC_8.0_12 Successo
	@Test
	public void testCase_8_0_12() throws ServletException, IOException, SQLException, JSONException {
		request.addParameter("flag", "1");
		request.addParameter("category", "Evento");
		request.addParameter("date", "2020-01-20");
		request.addParameter("startTime", "09:00");
		request.addParameter("finishTime", "11:00");
		request.addParameter("description", "Giornata open-day");
		
		UserDAO userDAO = new UserDAO();
		UserBean userBean = userDAO.doRetrieveByMail("m.pisciotta@studenti.unicampania.it");
		
		request.getSession().setAttribute("user", userBean);
		
		servlet.doPost(request, response);
		
		String content = response.getContentAsString();
		JSONObject jsonObj = new JSONObject(content);
		int result = (int) jsonObj.get("result");
		
		assertEquals(result, 1);
	}
}