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


@WebServlet("/Activity")
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ActivityServlet() {
    	super();
    }

	
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
	    	     
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/activityInfo.jsp");
	    dispatcher.forward(request, response); 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
