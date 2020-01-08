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
    	doPost(request, response);
    }
    	    	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flag = Integer.parseInt(request.getParameter("flag"));		// Flag passato nello script ajax: 
																		// 1 = registrazione nuova attività
																		// 2 = modifica attività da parte di un tutor
																		// 3 = convalida/cancellazione attività da parte di un membro della Commissione
		ActivityTutorDAO activityDAO = new ActivityTutorDAO();
		
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
			float hours = (finish - start) / 60;
			
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
			return;
		}
		
		else if (flag == 2) {											// Modifica/Cancellazione di un'attività da parte di un tutor
			
		}
		
		else if (flag == 3) {											// Validazione di un'attività da parte di un membro della Commissione
			String activityId = request.getParameter("id");
			int id = Integer.parseInt(activityId);
			
			ValidatesDAO validatesDAO = new ValidatesDAO();
			
			String validate = request.getParameter("validate");
			
			if(validate != null && validate.equals("true")) {
				UserBean user = (UserBean) request.getSession(false).getAttribute("user");
				try {
					ValidatesBean bean = new ValidatesBean();
					bean.setActivityId(id);
					bean.setCommissionMember(user.getEmail());
					validatesDAO.doSave(bean);
					
					request.removeAttribute("activitiesCollection");
					request.getSession(false).setAttribute("Email", activityDAO.doRetrieveById(id).getTutor());					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(validate != null && validate.equals("false")) {
				try {
					ActivityTutorBean activity = activityDAO.doRetrieveById(id);
					activityDAO.doDelete(activity);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/commission/register.jsp");
			dispatcher.forward(request, response);
			return;
		}
		    	
/*    	RequestDispatcher dispatcher = null;
    	int id = 0;
    	try {
			if (idActivity != null && !idActivity.equals("")) {
				id = Integer.parseInt(idActivity);
				ActivityTutorBean activity = activityDAO.doRetrieveById(id);
				request.removeAttribute("activity");
				request.setAttribute("activity",activity);
				dispatcher = request.getRequestDispatcher("/tutor/activityModify.jsp");
			}else dispatcher = request.getRequestDispatcher("/tutor/activity.jsp");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
    	dispatcher.forward(request, response);
		
    	 
    	
    	
		ActivityTutorBean activityBean=new ActivityTutorBean();
		
		String fl=request.getParameter("flag");
		int flag=Integer.parseInt(fl);
		if ((idActivity != null && !idActivity.equals("")) || flag==1 ) {
			if(flag!=1)
				id = Integer.parseInt(idActivity);
			String categoria=request.getParameter("categoria");
			Date data=Date.valueOf(request.getParameter("data"));
			int oraInizio=Integer.parseInt(request.getParameter("oraInizio"));
			int oraFine=Integer.parseInt(request.getParameter("oraFine"));
			String dettagli=request.getParameter("dettagli");
			
			float oreSvolte=oraFine-oraInizio;
			
			activityBean.setIdActivity(id);
			activityBean.setCategory(categoria);
			activityBean.setActivityDate(data);
			activityBean.setStartTime(oraInizio);
			activityBean.setFinishTime(oraFine);
			activityBean.setHours(oreSvolte);
			activityBean.setDetails(dettagli);
			
			try {
				if(flag==1) {
					activityDAO.doSave(activityBean);
				}else
					activityDAO.doModify(activityBean);
			}catch (SQLException e) {
				e.printStackTrace();
			}
	    } */
		
		response.sendRedirect(request.getContextPath()+ "/tutor/register.jsp");
     }
 }	
