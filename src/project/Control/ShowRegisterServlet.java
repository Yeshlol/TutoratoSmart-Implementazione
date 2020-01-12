package project.Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Model.ActivityTutorBean;
import project.Model.ActivityTutorDAO;
import project.Model.TutorBean;
import project.Model.TutorDAO;
import project.Model.UserBean;


@WebServlet("/ShowRegister")
public class ShowRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowRegisterServlet() {
        super();       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActivityTutorDAO activityTutorDAO = new ActivityTutorDAO();
		Collection<ActivityTutorBean> activitiesCollection  = null;
		
		int flag = Integer.parseInt(request.getParameter("flag"));	// Flag 1 = visualizza registro del tutor (loggato)
																	// Flag 2 = membro della Commissione vuole visualizzare un registro di un tutor.
		
		if (flag == 1) {
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			String email = user.getEmail();
	 
			try {
				activitiesCollection = activityTutorDAO.doRetrieveAllByMail("YEAR(ActivityDate) DESC, MONTH(ActivityDate) DESC, DAY(ActivityDate) DESC", email);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(activitiesCollection != null) {
				request.removeAttribute("activitiesCollection");
				request.setAttribute("activitiesCollection", activitiesCollection);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/register.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		else if(flag == 2) {
			String email = request.getParameter("Email");
			if (email == null) {
				email = (String) request.getSession(false).getAttribute("Email");
			}
			TutorDAO tutorDAO = new TutorDAO();
			TutorBean tutor = new TutorBean();		
			
			try {
				tutor = tutorDAO.doRetrieveByMail(email);
				activitiesCollection = activityTutorDAO.doRetrieveAllByMail(null, email);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(activitiesCollection != null) {
				request.removeAttribute("activitiesCollection");
				request.setAttribute("activitiesCollection", activitiesCollection);
				request.removeAttribute("tutor");
				request.setAttribute("tutor", tutor);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/commission/register.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}
}
