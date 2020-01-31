package project.Control.Request_Management;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import project.Model.RequestBean;
import project.Model.RequestDAO;
import project.Model.UserBean;

/**
 * 
 *  Servlet implementation class RequestServlet
 *
 */

@WebServlet("/RequestRequestManagement")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Constructor
	 *
     * @see HttpServlet#HttpServlet()
     */	
    
    public RequestServlet() {
        super();														
    }
   
    /**
     * Method doGet()
     * 
   	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	return;
    }
    	    	
    /**
     * Method doPost()
     * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDAO requestDAO = new RequestDAO();
		
		JSONObject obj = new JSONObject();
		
		int flag = Integer.parseInt(request.getParameter("flag"));		// Flag passato tramite gli scipt ajax																		
																		// 1 = accettazione prenotazione
																		// 2 = studente risulta assente
		
		if(flag == 1) {													// 1 = accettazione prenotazione da parte di un tutor.
			String requestId = request.getParameter("id");
			int id;
		
			if (requestId != null && requestId != "") {					// recupero id richiesta.
				id = Integer.parseInt(requestId);
			}
			else {														// parametro id non valido o nullo.
				RequestDispatcher dispatcher = request.getRequestDispatcher("/View/home.jsp");
				dispatcher.forward(request, response);
				return;
			}			
			
			String requestDuration = request.getParameter("duration");
			
			int duration;
			
			if (requestDuration != null && requestDuration != "") {		// recupero durata stimata.
				duration = Integer.parseInt(requestDuration);
			}
			else {														// parametro durata non valido o nullo.
				RequestDispatcher dispatcher = request.getRequestDispatcher("/View/home.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			if(duration < 10 || duration > 120) {
				throw new IllegalArgumentException("Durata inserita non valida");
			}
			
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			
			try {
				RequestBean bean = new RequestBean();					// Recupero dati della richiesta registrati nel DB
				bean = requestDAO.doRetrieveById(id);
				bean.setDuration(duration);
				
				requestDAO.doAccept(bean, user.getEmail());				// Accetta la richiesta, ne aggiorna lo stato e memorizza nel DB il tutor che l'ha gestita.
				
				bean = requestDAO.doRetrieveById(id);
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				request.getSession(false).removeAttribute("request");
				request.getSession(false).setAttribute("request",bean);
				request.getSession(false).setAttribute("accept", "true");
				
				obj.put("result", 1);
			} catch (SQLException e) {									// Errore nella convalida dell'attivita'.
				try {
					obj.put("result", 2);			
				} catch (JSONException jsonexp) {						// Errore parser json					
				}
			} catch (JSONException jsonexp) {							// Errore parser json
			}
			finally {
				response.getWriter().write(obj.toString());
			}
		
			return;
		}
		else if(flag == 2) {											// Studente assente
			try {
				RequestBean req = (RequestBean) request.getSession(false).getAttribute("request");					// Recupero dati della richiesta registrati nel DB
				
				req.setState("Studente assente");
								
				requestDAO.doModify(req);				// Accetta la richiesta, ne aggiorna lo stato e memorizza nel DB il tutor che l'ha gestita.
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				request.getSession(false).removeAttribute("request");
				request.getSession(false).setAttribute("request",req);
				request.getSession(false).setAttribute("absent", "true");
				
				obj.put("result", 1);
			} catch (SQLException e) {									// Errore nella convalida dell'attivita'.
				try {
					obj.put("result", 2);			
				} catch (JSONException jsonexp) {						// Errore parser json					
				}
			} catch (JSONException jsonexp) {							// Errore parser json
			}
			finally {
				response.getWriter().write(obj.toString());
			}
		
			return;
		}
		else {															// Flag non valido.
			RequestDispatcher dispatcher = request.getRequestDispatcher("/View/home.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}
}
