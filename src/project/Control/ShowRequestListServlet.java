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

import project.Model.RequestBean;
import project.Model.RequestDAO;
import project.Model.UserBean;

/**
 * Servlet implementation class ShowRequestListServlet
 */
@WebServlet("/ShowRequestList")
public class ShowRequestListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowRequestListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               RequestDAO requestDAO=new RequestDAO();
		       UserBean user = (UserBean) request.getSession().getAttribute("user");
               String email=user.getEmail();
               
               Collection<RequestBean> requestCollection=null;
               
               try {
				requestCollection=requestDAO.doRetrieveAllByMail(null, email);
				request.removeAttribute("requestCollection");
				request.setAttribute("requestCollection", requestCollection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("/student/requestList.jsp");
   			   dispatcher.forward(request, response);    
   			   return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
