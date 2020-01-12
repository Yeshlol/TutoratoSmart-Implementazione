package project.Control;

import java.io.IOException;
import java.sql.SQLException;

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

@WebServlet("/Appointment")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AppointmentServlet() {
        super();
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	return;
    }
    	    	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean tutor = (UserBean) request.getSession(false).getAttribute("user");
		RequestBean req = (RequestBean) request.getSession(false).getAttribute("request");
		
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		RequestDAO requestDAO = new RequestDAO();
		
		JSONObject obj = new JSONObject();
		
		int flag = Integer.parseInt(request.getParameter("flag"));		// Flag passato nello script: 
																		// 1 = registrazione nuovo appuntamento
																		// 2 = modifica/cancellazione appuntamento da parte di un tutor
																		
		if (flag == 1) {												// Registrazione nuovo appuntamento
			String comment = request.getParameter("comment");			// Dati appuntamento
			String tutorMail = tutor.getEmail();
						
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
	}
}