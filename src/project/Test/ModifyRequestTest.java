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


public class ModifyRequestTest {
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

	//TC_4.0_1 Nessun giorno selezionato
	@Test
	public void testCase_4_0_1() throws ServletException, IOException {
		request.addParameter("id", "4");
		request.addParameter("flag","3");
		request.addParameter("time","900");
		request.addParameter("comment","ppppp");
		request.addParameter("date","");
		
		final String message = "Non è stato selezionato il giorno";
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

			servlet.doPost(request, response);
		});
		assertEquals(message, exceptionThrown.getMessage());
	}
	
  	/*TC_4.0_2 Selezionato giorno diverso da mercoledi e giovedi
		@Test
		public void testCase_4_0_2() throws ServletException, IOException {
            request.addParameter("id", "4");
			request.addParameter("flag","3");
			request.addParameter("time","900");
			request.addParameter("comment","ppppp");
			request.addParameter("date","2020-17-01");
			
			final String message = "Il giorno selezionato non è corretto";
			IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

				servlet.doPost(request, response);
			});
			assertEquals(message, exceptionThrown.getMessage());
		} */
		
		//TC_4.0_3 Orario non selezionato
		@Test
		public void testCase_3_0_3() throws ServletException, IOException {
			request.addParameter("id", "4");
			request.addParameter("flag","3");
			request.addParameter("time","");
			request.addParameter("comment","ppppp");
			request.addParameter("date","2020-22-01");
			
			final String message = "Non è stato selezionato l'orario";
			IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

				servlet.doPost(request, response);
			});
			assertEquals(message, exceptionThrown.getMessage());
		
		}
	
	//TC_4.0_4 Minuti < 540
		@Test
		public void testCase_4_0_4() throws ServletException, IOException {
			request.addParameter("id", "4");
			request.addParameter("flag","3");
			request.addParameter("time","500");
			request.addParameter("comment","ppppp");
			request.addParameter("date","2020-22-01");
			
			final String message = "Minuti selezionati errati";
			IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

				servlet.doPost(request, response);
			});
			assertEquals(message, exceptionThrown.getMessage());
		}
		
		//TC_4.0_5 	765 <= Minuti <= 870 
		@Test
		public void testCase_4_0_5() throws ServletException, IOException, JSONException, SQLException {	
			request.addParameter("id", "4");
			request.addParameter("flag","3");
			request.addParameter("time","800");
			request.addParameter("comment","ppppp");
			request.addParameter("date","2020-22-01");
			
			
			final String message = "Minuti selezionati errati";
			IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

				servlet.doPost(request, response);
			});
			assertEquals(message, exceptionThrown.getMessage());
		}
		//TC_4.0_6 	Minuti > 1000 
				@Test
				public void testCase_4_0_6() throws ServletException, IOException, JSONException, SQLException {	
					request.addParameter("id", "4");
					request.addParameter("flag","3");
					request.addParameter("time","1100");
					request.addParameter("comment","ppppp");
					request.addParameter("date","2020-22-01");
					
					
					final String message = "Minuti selezionati errati";
					IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

						servlet.doPost(request, response);
					});
					assertEquals(message, exceptionThrown.getMessage());
				}
				//TC_4.0_7 	Lunghezza commento <=0
				@Test
				public void testCase_4_0_7() throws ServletException, IOException, JSONException, SQLException {	
					request.addParameter("id", "4");
					request.addParameter("flag","3");
					request.addParameter("time","900");
					request.addParameter("comment","");
					request.addParameter("date","2020-22-01");
					
					
					final String message = "Lunghezza commento errata";
					IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

						servlet.doPost(request, response);
					});
					assertEquals(message, exceptionThrown.getMessage());
				}
				//TC_4.0_8 	Lunghezza commento > 240
				@Test
				public void testCase_4_0_8() throws ServletException, IOException, JSONException, SQLException {	
					request.addParameter("id", "4");
					request.addParameter("flag","3");
					request.addParameter("time","900");
					request.addParameter("comment","Ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
							"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
							"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
							"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
							"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
							"ppppppppppppppppppppppppppppppppppppppppppppppp\r\n" + 
							"");
					request.addParameter("date","2020-22-01");
					
					
					final String message = "Lunghezza commento errata";
					IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class, () -> {

						servlet.doPost(request, response);
					});
					assertEquals(message, exceptionThrown.getMessage());
				}
			/*	//TC_4.0_9 Successo
				@Test
				public void testCase_4_0_9() throws ServletException, IOException, JSONException, SQLException {
					RequestDAO requestDAO = new RequestDAO();
					RequestBean requestBean = requestDAO.doRetrieveById(4);
					request.getSession().setAttribute("request", requestBean);
					request.addParameter("flag","3");
					request.addParameter("time","900");
					request.addParameter("comment","ppppp");
					request.addParameter("date","2020-22-01");
					
					
					servlet.doPost(request, response);
					
					String content = response.getContentAsString();
					JSONObject jsonObj = new JSONObject(content);
					int result = (int) jsonObj.get("result");
					
					assertEquals(result, 1);
				} */
}
