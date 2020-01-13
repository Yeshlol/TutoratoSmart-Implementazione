package project.Control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import project.Model.UserBean;
import project.Model.UserDAO;
import project.Utils.Utils;

/**
 * 
 *  Servlet implementation class LoginServlet
 *
 */

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 /**
     * Method doGet()
     * 
   	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}

	/**
     * Method doPost()
     * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");

		UserDAO userDAO = new UserDAO();
		UserBean user = null;
		request.getSession().invalidate();
		HttpSession session = request.getSession(true);
		JSONObject obj = new JSONObject();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
				
		synchronized (session) {
			try {
				user = userDAO.doRetrieveByMail(email);
				if (user != null) {
					pwd = Utils.sha256(pwd);
					if (pwd.equals(user.getPwd())) {					// Utente trovato, password corretta.
						request.getSession(false).setAttribute("user", user);
						obj.put("result", 1);				
					}				
				}
				else {
					obj.put("result", 2);
				}
			} catch (SQLException e) {
				try {
					obj.put("result", 2);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			finally {
				response.getWriter().write(obj.toString());
			}
		}
	}
}
