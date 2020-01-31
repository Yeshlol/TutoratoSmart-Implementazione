package project.Control.User;

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
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
								
		if (email.length() == 0 || email.length() > 45) {
			throw new IllegalArgumentException("Lunghezza email non valida");
	    }
		
		String mailFormat1 = "\\w+([\\.-]?\\w+)*@studenti.unicampania.it";
		String mailFormat2 = "\\w+([\\.-]?\\w+)*@commissione.unicampania.it";
		String mailFormat3 = "\\w+([\\.-]?\\w+)*@unicampania.it";
		
		if (!(email.matches(mailFormat1) || email.matches(mailFormat2) || email.matches(mailFormat3))) {
			throw new IllegalArgumentException("Formato email non valido");
		}
	   		
		if(pwd.length() < 8 || pwd.length() > 10) {
			throw new IllegalArgumentException("Lunghezza password non valida");
		}
		
		String passwordFormat = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,10}";
		
		if (!pwd.matches(passwordFormat)) {
			throw new IllegalArgumentException("Formato password non valido");
		}
		
		UserDAO userDAO = new UserDAO();
		UserBean user = null;
		request.getSession().invalidate();
		HttpSession session = request.getSession(true);
						
		synchronized (session) {
			JSONObject obj = new JSONObject();
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
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
					throw new IllegalArgumentException("Utente non trovato");
				}
			} catch (SQLException e) {
				
								
			} catch (JSONException e) {
				e.printStackTrace();
			}
			finally {
				response.getWriter().write(obj.toString());
			}
		}
	}
}
