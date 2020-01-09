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

import project.Model.ActivityTutorBean;
import project.Model.ActivityTutorDAO;
import project.Model.UserBean;
import project.Model.ValidatesBean;
import project.Model.ValidatesDAO;
import project.Utils.Utils;


@WebServlet("/Activity")
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ActivityServlet() {
        super();
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	return;
    }
    	    	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flag = Integer.parseInt(request.getParameter("flag"));		// Flag passato: 
																		// 1 = registrazione nuova attività
																		// 2 = modifica/cancellazione attività da parte di un tutor
																		// 3 = convalida/cancellazione attività da parte di un membro della Commissione
		ActivityTutorDAO activityDAO = new ActivityTutorDAO();
		
		JSONObject obj = new JSONObject();
		
		if (flag == 1) {												// Registrazione nuova attività
			String category = request.getParameter("category");			// Dati Attività
			Date date = Date.valueOf(request.getParameter("date"));
			String startTime = request.getParameter("startTime");
			String finishTime = request.getParameter("finishTime");
			String description = request.getParameter("description");
			String tutorMail = request.getParameter("email");
			int registerId = Integer.parseInt(request.getParameter("registerId"));
			
			int start = Utils.getTimeAsInt(startTime);
			int finish = Utils.getTimeAsInt(finishTime);
			float hours = (finish - start) / 60.f;
						
			ActivityTutorBean activityBean = new ActivityTutorBean();
			
			activityBean.setCategory(category);
			activityBean.setActivityDate(date);
			activityBean.setStartTime(start);
			activityBean.setFinishTime(finish);
			activityBean.setHours(hours);
			activityBean.setDetails(description);
			activityBean.setTutor(tutorMail);
			activityBean.setRegisterId(registerId);
			
			try {
				activityDAO.doSave(activityBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("activitiesCollection", null);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/register.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		else if (flag == 2) {											// Modifica/Cancellazione di un'attività da parte di un tutor
			
		}
		
		else if (flag == 3) {											// Validazione di un'attività da parte di un membro della Commissione
			String activityId = request.getParameter("id");
			int id = Integer.parseInt(activityId);
			
			ValidatesDAO validatesDAO = new ValidatesDAO();
			
			String validate = request.getParameter("validate");
			
			if (validate != null && validate.equals("true")) {
				UserBean user = (UserBean) request.getSession(false).getAttribute("user");
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
				} catch (SQLException e) {				// Errore nella convalida dell'attivita'.
					try {
						obj.put("result", 2);			
					} catch (JSONException jsonexp) {	// Errore parser json					
					}
				} catch (JSONException jsonexp) {		// Errore parser json
				}
				finally {
					response.getWriter().write(obj.toString());
				}
				
				return;
			}
			if (validate != null && validate.equals("false")) {
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
				} catch (SQLException e) {				// Errore nella rimozione dell'attivita'.
					try {
						obj.put("result", 2);			
					} catch (JSONException jsonexp) {	// Errore parser json					
					}
				} catch (JSONException jsonexp) {		// Errore parser json
				}
				finally {
					response.getWriter().write(obj.toString());
				}
				
				return;
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/commission/register.jsp");
			dispatcher.forward(request, response);
			return;
		}
			
		response.sendRedirect(request.getContextPath()+ "/tutor/register.jsp");
     }
 }	
