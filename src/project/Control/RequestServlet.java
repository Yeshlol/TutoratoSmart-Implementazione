package project.Control;

import java.io.IOException;
import java.sql.Date;
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
import project.Utils.Utils;

@WebServlet("/Request")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RequestServlet() {
        super();														
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	return;
    }
    	    	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flag = Integer.parseInt(request.getParameter("flag"));		// Flag passato come hidden nel form: 
																		// 1 = registrazione nuova prenotazione.
																		// 2 = cancellazione prenotazione.
		RequestDAO requestDAO = new RequestDAO();
		JSONObject obj = new JSONObject();
		
		if (flag == 1) {												// 1 = Registrazione nuova prenotazione da parte dello studente.
			String time = request.getParameter("time");					// Dati della richiesta inseriti dallo studente.
			String comment = request.getParameter("comment");					
			UserBean user = (UserBean) request.getSession().getAttribute("user");
						
			Date date = Date.valueOf(request.getParameter("date"));			
			
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
			} catch (SQLException e) {								// Errore nella convalida dell'attivita'.
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
		
			String delete = request.getParameter("delete");
			
			if(delete != null && delete.equals("true")) {				// cancellazione prenotazione da parte dello studente.
				RequestBean bean;
				try {
					bean = requestDAO.doRetrieveById(id);
					requestDAO.doDelete(bean);
					request.getSession(false).removeAttribute("request");
					request.getSession(false).removeAttribute("requestsCollection");
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					
					obj.put("result", 1);
				} catch (SQLException e) {								// Errore nella convalida dell'attivita'.
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
			else {														// Parametro delete non valido o nullo.
				RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		else {															// flag non valido.
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}
}