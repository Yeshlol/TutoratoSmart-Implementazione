package project.Control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

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
import project.Utils.Utils;

/**
 * 
 *  Servlet implementation class RequestServlet
 *
 */

@WebServlet("/Request")
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
		boolean ajax = Boolean.parseBoolean(request.getParameter("ajax"));// Richieste ajax per disponibilità orario appuntamento
		
		RequestDAO requestDAO = new RequestDAO();
		JSONObject obj = new JSONObject();
		
		if(ajax) {
			try {				
				String date = request.getParameter("date");
				String time = request.getParameter("time");
				
				if (date != null && date != "" && time != null && time != "") {
					Date requestDate = Date.valueOf(date);
					int requestTime = Utils.getTimeAsInt(time);
					
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					
					boolean available = false;
					
					boolean modify = Boolean.parseBoolean(request.getParameter("modify"));
					
					if(modify) {														// Controllo altre richieste registrate, diverse da quella in modifica
						int requestId = Integer.parseInt(request.getParameter("id"));
						
						try {
							available = requestDAO.differentRequestRegistered(requestDate, requestTime, requestId);
							obj.put("disponibile", available);
						} catch (JSONException e) {
							e.printStackTrace();
							return;
						} catch (SQLException e) {
							e.printStackTrace();
							return;
						}
						
						response.getWriter().write(obj.toString());
						return;
					}
					else {																// Controllo altre richieste registrate
						try {
							available = requestDAO.isAvailable(requestDate, requestTime);
							obj.put("disponibile", available);
						} catch (SQLException e) {
							e.printStackTrace();
							return;
						} catch (JSONException e) {
							e.printStackTrace();
							return;
						}
						
						response.getWriter().write(obj.toString());
						return;
					}
				}
				obj.put("disponibile", false);
				
				response.getWriter().write(obj.toString());
				return;
			} catch(JSONException e) {
				response.sendRedirect("home.jsp"); 						// Errore parser json
				return;
			}
		}
		
		int flag = Integer.parseInt(request.getParameter("flag"));		// Flag passato tramite gli scipt ajax																		
				
		if (flag == 1) {												// 1 = Registrazione nuova prenotazione da parte dello studente.
			String time = request.getParameter("time");					// Dati della richiesta inseriti dallo studente.
			String comment = request.getParameter("comment");					
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			String stringDate = request.getParameter("date");
									
			if(stringDate.length()==0) {
				throw new IllegalArgumentException("Selezionare la data");
			}
			
			Date date = Date.valueOf(stringDate);
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
			
	        if(calendar.get(Calendar.DAY_OF_WEEK)!=3 && calendar.get(Calendar.DAY_OF_WEEK)!=4) {
				throw new IllegalArgumentException("Giorno selezionato non valido");
			}
	        
	        if(time.length() == 0) {
				throw new IllegalArgumentException("Selezionare l’orario");
			}
			
			int tempo = Utils.getTimeAsInt(time);
			
			if(tempo < 540 || ((tempo >= 765) && (tempo <= 870)) || tempo > 1000){
				throw new IllegalArgumentException("Orario selezionato non valido");
			}
	        
			if(comment.length() > 240 || comment.length() == 0) {
				throw new IllegalArgumentException("Lunghezza commento non valida");
			}
							
			RequestBean bean = new RequestBean();
			bean.setRequestDate(date);
			bean.setRequestTime(Utils.getTimeAsInt(time));
			bean.setStudentComment(comment);
			bean.setStudent(user.getEmail());
			
			try {
			    requestDAO.doSave(bean);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
			
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
		else if (flag == 2) {											// 2 = Cancellazione prenotazione da parte dello studente.
			String requestId = request.getParameter("id");
			int id;
		
			if (requestId != null && requestId != "") {					// recupero id richiesta.
				id = Integer.parseInt(requestId);
			}
			else {														// parametro id non valido o nullo.
				RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
				return;
			}
		
			RequestBean bean;											// cancellazione prenotazione da parte dello studente.
			try {
				bean = requestDAO.doRetrieveById(id);
			    requestDAO.doDelete(bean);			    
				request.getSession(false).removeAttribute("request");
				request.removeAttribute("requestsCollection");
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
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
		else if (flag == 3) {											// 3 = modifica prenotazione da parte dello studente.
			String requestId = request.getParameter("id");
			int id;
		
			if (requestId != null && requestId != "") {					// recupero id richiesta.
				id = Integer.parseInt(requestId);
			}
			else {														// parametro id non valido o nullo.
				RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
				return;
			}
											
			String time = request.getParameter("time");					// Dati della richiesta inseriti dallo studente.
			String comment = request.getParameter("comment");
			String stringDate = request.getParameter("date");
			
			if(stringDate.length()==0) {
				throw new IllegalArgumentException("Selezionare la data");
			}
			
			Date date = Date.valueOf(stringDate);
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
			
	        if(calendar.get(Calendar.DAY_OF_WEEK)!=3 && calendar.get(Calendar.DAY_OF_WEEK)!=4) {
				throw new IllegalArgumentException("Giorno selezionato non valido");
			}
	        
	        if(time.length() == 0) {
				throw new IllegalArgumentException("Selezionare l’orario");
			}
			
			int tempo = Utils.getTimeAsInt(time);
			
			if(tempo < 540 || ((tempo >= 765) && (tempo <= 870)) || tempo > 1000){
				throw new IllegalArgumentException("Orario selezionato non valido");
			}
	        
			if(comment.length() > 240 || comment.length() == 0) {
				throw new IllegalArgumentException("Lunghezza commento non valida");
			}
			
			try {
				RequestBean bean = new RequestBean();					// Recupero dati della richiesta registrati nel DB
				bean = requestDAO.doRetrieveById(id);
				
				bean.setRequestDate(date);								// Modifica dati del bean
				bean.setRequestTime(Utils.getTimeAsInt(time));
				bean.setStudentComment(comment);
				
				requestDAO.doModify(bean);                        // Modifica dati della richiesta nel DB
				
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
			
				request.getSession(false).removeAttribute("request");
				request.getSession(false).setAttribute("request",bean);
				request.removeAttribute("requestsCollection");
				
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
		else if(flag == 4) {											// 4 = accettazione prenotazione da parte di un tutor.
			String requestId = request.getParameter("id");
			int id;
		
			if (requestId != null && requestId != "") {					// recupero id richiesta.
				id = Integer.parseInt(requestId);
			}
			else {														// parametro id non valido o nullo.
				RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
				return;
			}			
			
			String requestDuration = request.getParameter("duration");
			
			int duration;
			
			if (requestDuration != null && requestDuration != "") {		// recupero durata stimata.
				duration = Integer.parseInt(requestDuration);
			}
			else {														// parametro durata non valido o nullo.
				RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
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
		else if(flag == 5) {											// Studente assente
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
		else {															// flag non valido.
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}
}