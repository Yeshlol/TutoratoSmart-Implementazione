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


@WebServlet("/ShowActivity")
public class ShowActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowActivityServlet() {
    	super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActivityTutorDAO activityDAO = new ActivityTutorDAO();
		
		int flag = Integer.parseInt(request.getParameter("flag"));	// Flag 1 = visualizza dettagli attività del tutor (loggato)
																	// Flag 2 = membro della Commissione vuole visualizzare un'attivita di un tutor.
		
		ActivityTutorBean activityBean = new ActivityTutorBean();
		
	    int id = Integer.parseInt(request.getParameter("id"));
	     
	    try {
	    	activityBean = activityDAO.doRetrieveById(id);
			request.getSession(false).setAttribute("activity",activityBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (flag == 1) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/activityInfo.jsp");
		    dispatcher.forward(request, response);		    
		    return;
		}
		
		else if (flag == 2) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/commission/activityInfo.jsp");
		    dispatcher.forward(request, response);
		    return;
		}		 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
