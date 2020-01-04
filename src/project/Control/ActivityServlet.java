package project.Control;

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
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/ActivityServlet")
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             ActivityTutorDAO activityDAO=new ActivityTutorDAO();
             ActivityTutorBean activityBean=new ActivityTutorBean();
		     int id=Integer.parseInt(request.getParameter("id"));
		     
		     try {
				activityBean=activityDAO.doRetrieveById(id);
				request.removeAttribute("activity");
				request.setAttribute("activity",activityBean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		     
		     
		     RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/activity.jsp");
		     dispatcher.forward(request, response); 
                  
                  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
