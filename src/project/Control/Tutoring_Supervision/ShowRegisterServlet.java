package project.Control.Tutoring_Supervision;

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

/**
 * 
 *  Servlet implementation class ShowRegisterServlet
 *  
 */

@WebServlet("/ShowRegisterTutoringSupervision")
public class ShowRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Constructor
	 *
     * @see HttpServlet#HttpServlet()
     */	
	
    public ShowRegisterServlet() {
        super();       
    }
    
    /**
     * Method doGet()
     * 
   	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   	 */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActivityTutorDAO activityTutorDAO = new ActivityTutorDAO();
		Collection<ActivityTutorBean> activitiesCollection  = null;
		
		String email = request.getParameter("Email");						// Un membro della Commissione vuole visualizzare un registro di un tutor.
		if (email == null) {
			email = (String) request.getSession().getAttribute("Email");
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
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/commission/register.jsp");
		dispatcher.forward(request, response);
		return;
	}

	/**
     * Method doPost()
     * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}
}
