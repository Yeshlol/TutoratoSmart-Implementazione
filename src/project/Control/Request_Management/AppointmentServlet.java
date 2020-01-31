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

import project.Model.AppointmentBean;
import project.Model.AppointmentDAO;
import project.Model.RequestBean;
import project.Model.RequestDAO;
import project.Model.UserBean;

/**
 * 
 *  Servlet implementation class AppointmentServlet
 *
 */
@WebServlet("/AppointmentRequestManagement")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * Constructor
	 *
     * @see HttpServlet#HttpServlet()
     */
    
    public AppointmentServlet() {
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
		UserBean tutor = (UserBean) request.getSession(false).getAttribute("user");
				
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		RequestDAO requestDAO = new RequestDAO();
		
		JSONObject obj = new JSONObject();
		
		int flag = Integer.parseInt(request.getParameter("flag"));		// Flag passato nello script: 
																		// 1 = registrazione nuovo appuntamento
																		// 2 = cancellazione appuntamento
																		// 3 = modifica appuntamento
																		
																		
		if (flag == 1) {												// Registrazione nuovo appuntamento
			String comment = request.getParameter("comment");			// Dati appuntamento
						
			if (comment.length() == 0 || comment.length() > 240) {
				throw new IllegalArgumentException("Lunghezza commento non valida");
		    }
			
			String tutorMail = tutor.getEmail();
			
			RequestBean req = (RequestBean) request.getSession(false).getAttribute("request");
			
			AppointmentBean appointmentBean = new AppointmentBean();
			
			appointmentBean.setDetails(comment);
			appointmentBean.setTutor(tutorMail);
			appointmentBean.setRequestId(req.getIdRequest());
			
			try {
				appointmentDAO.doSave(appointmentBean);
							
				req = requestDAO.doRetrieveById(req.getIdRequest());
				
				request.getSession(false).removeAttribute("request");
				request.getSession(false).setAttribute("request", req);
				request.getSession(false).removeAttribute("appointment");
				request.getSession(false).setAttribute("appointment", appointmentBean);
				request.getSession(false).setAttribute("appointmentRegistered", "true");
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
			
				obj.put("result", 1);
			} catch (SQLException e) {								// Errore nella convalida dell'attivita'.
				System.out.println(e.getMessage());
				try {
					obj.put("result", 2);			
				} catch (JSONException jsonexp) {					// Errore parser json					
				}
			} catch (JSONException jsonexp) {						// Errore parser json
			}
			finally {
				response.getWriter().write(obj.toString());
			}
		}
		
		else if (flag == 2) {										// Cancellazione appuntamento
			AppointmentBean appointment = (AppointmentBean) request.getSession(false).getAttribute("appointment");
			
			try {
				appointmentDAO.doDelete(appointment);
							
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
			
				obj.put("result", 1);
			} catch (SQLException e) {								// Errore nella cancellazione dell'appuntamento.
				System.out.println(e.getMessage());
				try {
					obj.put("result", 2);			
				} catch (JSONException jsonexp) {					// Errore parser json					
				}
			} catch (JSONException jsonexp) {						// Errore parser json
			}
			finally {
				response.getWriter().write(obj.toString());
			}
		}
		
		else if (flag == 3) {										// Modifica appuntamento
			String comment = request.getParameter("comment");		// Dati appuntamento					
			
			// Validazione dati in input
			if (comment.length() == 0 || comment.length() > 240) {
				throw new IllegalArgumentException("Lunghezza commento non valida");
		    }
			
			AppointmentBean appointmentBean = (AppointmentBean) request.getSession(false).getAttribute("appointment");
			
			appointmentBean.setDetails(comment);
			
			try {
				appointmentDAO.doModify(appointmentBean);
				
				request.getSession(false).removeAttribute("appointment");
				request.getSession(false).setAttribute("appointment", appointmentBean);
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
			
				obj.put("result", 1);
			} catch (SQLException e) {								// Errore nella modifica dell'appuntamento.
				System.out.println(e.getMessage());
				try {
					obj.put("result", 2);			
				} catch (JSONException jsonexp) {					// Errore parser json					
				}
			} catch (JSONException jsonexp) {						// Errore parser json
			}
			finally {
				response.getWriter().write(obj.toString());
			}
			
			return;
		}
		else {														// Flag non valido
			RequestDispatcher dispatcher = request.getRequestDispatcher("/View/home.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}
}