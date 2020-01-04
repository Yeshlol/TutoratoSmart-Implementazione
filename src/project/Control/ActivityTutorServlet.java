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
import javax.websocket.Session;

import project.Model.ActivityTutorBean;
import project.Model.ActivityTutorDAO;
import project.Model.UserBean;

/**
 * Servlet implementation class ActivityTutorServlet
 */
@WebServlet("/ActivityTutorServlet")
public class ActivityTutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivityTutorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                 ActivityTutorDAO activityTutorDAO= new ActivityTutorDAO();
                 Collection<ActivityTutorBean> activityCollection  = null;
                 
                 UserBean user;
                 String email;
                 user = (UserBean) request.getSession().getAttribute("user");
                 email= user.getEmail();
                 
                 try {
					activityCollection = activityTutorDAO.doRetrieveAllByMail(null, email);
				} catch (SQLException e) {
					e.printStackTrace();
				}
                 if(activityCollection != null) {
                	 request.removeAttribute("activityCollection");
                	 request.setAttribute("activityCollection",activityCollection);
                 }
                 RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/activityTutor.jsp");
         		 dispatcher.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
