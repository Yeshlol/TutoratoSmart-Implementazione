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
import project.Model.UserBean;


@WebServlet("/ActivityTutor")
public class ActivityTutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ActivityTutorServlet() {
        super();       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActivityTutorDAO activityTutorDAO= new ActivityTutorDAO();
		Collection<ActivityTutorBean> activitiesCollection  = null;
 
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		String email = user.getEmail();
 
		try {
			activitiesCollection = activityTutorDAO.doRetrieveAllByMail(null, email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(activitiesCollection != null) {
			request.removeAttribute("activitiesCollection");
			request.setAttribute("activitiesCollection", activitiesCollection);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/activityTutor.jsp");
		dispatcher.forward(request, response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
