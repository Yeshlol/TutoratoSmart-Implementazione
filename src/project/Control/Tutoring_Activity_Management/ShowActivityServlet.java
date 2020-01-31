package project.Control.Tutoring_Activity_Management;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Model.ActivityTutorBean;
import project.Model.ActivityTutorDAO;

/**
 * 
 *  Servlet implementation class ShowActivityServlet
 *
 */

@WebServlet("/ShowActivityTutoringActivityManagement")
public class ShowActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Constructor
	 *
     * @see HttpServlet#HttpServlet()
     */	
    
    public ShowActivityServlet() {
    	super();
    }
    
    /**
     * Method doGet()
     * 
   	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   	 */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActivityTutorDAO activityDAO = new ActivityTutorDAO();
		ActivityTutorBean activityBean = new ActivityTutorBean();
		
	    int id = Integer.parseInt(request.getParameter("id"));
	     
	    try {
	    	activityBean = activityDAO.doRetrieveById(id);
			request.getSession(true).setAttribute("activity",activityBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/View/tutor/activityInfo.jsp");
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
