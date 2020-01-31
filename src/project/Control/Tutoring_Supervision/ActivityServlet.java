package project.Control.Tutoring_Supervision;

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

import project.Model.ActivityTutorBean;
import project.Model.ActivityTutorDAO;
import project.Model.UserBean;
import project.Model.ValidatesBean;
import project.Model.ValidatesDAO;

/**
 * 
 *  Servlet implementation class ActivityServlet
 *
 */
@WebServlet("/ActivityTutoringSupervision")
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Constructor
	 * 
     * @see HttpServlet#HttpServlet()
     */
    public ActivityServlet() {
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
		UserBean user = (UserBean) request.getSession(false).getAttribute("user");
		
		ActivityTutorDAO activityDAO = new ActivityTutorDAO();
				
		JSONObject obj = new JSONObject();
		
		String activityId = request.getParameter("id");				// Id attività da valutare
		int id = Integer.parseInt(activityId);
		
		int flag = Integer.parseInt(request.getParameter("flag"));	// Flag passato nello script: 
																	// 1 = convalida attività
																	// 2 = cancellazione attività
		
		if (flag == 1) {											// Validazione di un'attività da parte di un membro della Commissione
			ValidatesDAO validatesDAO = new ValidatesDAO();
			
			try {					
				ValidatesBean bean = new ValidatesBean();
				bean.setActivityId(id);
				bean.setCommissionMember(user.getEmail());
				validatesDAO.doSave(bean);
				
				request.removeAttribute("activitiesCollection");
				request.getSession(false).setAttribute("Email", activityDAO.doRetrieveById(id).getTutor());
				
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
		else if (flag == 2) {										// Rimozione attivita'.
			try {
				ActivityTutorBean activity = activityDAO.doRetrieveById(id);
				request.getSession(false).setAttribute("Email", activityDAO.doRetrieveById(id).getTutor());
									
				activityDAO.doDelete(activity);
				request.removeAttribute("activitiesCollection");
				
				request.getSession(false).removeAttribute("activity");
				request.getSession(false).setAttribute("delete", "true");
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				obj.put("result", 1);
			} catch (SQLException e) {								// Errore nella rimozione dell'attivita'.
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
		else {														// Flag non valido.
			RequestDispatcher dispatcher = request.getRequestDispatcher("/View/home.jsp");
			dispatcher.forward(request, response);
			return;
		}
     }
 }	
