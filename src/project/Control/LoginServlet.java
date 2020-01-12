package project.Control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.Model.UserBean;
import project.Model.UserDAO;
import project.Utils.Utils;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");

		UserDAO userDAO = new UserDAO();
		UserBean user = null;
		request.getSession().invalidate();
		HttpSession session = request.getSession(true);
		synchronized (session) {
			try {
				user = userDAO.doRetrieveByMail(email);
				if (user != null) {
					pwd = Utils.sha256(pwd);
					if (pwd.equals(user.getPwd())) {					// Utente trovato, password corretta.
						session.setAttribute("user", user);
						response.sendRedirect(request.getContextPath() + "/home.jsp");
					}
					else {
						session.setAttribute("user", null);				// Utente trovato, password non corretta.
						response.sendRedirect(request.getContextPath() + "/login.jsp");
					}
				} else {
					session.setAttribute("user", null);					// Utente non trovato.
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				}
			} catch (SQLException e) {
				System.out.println("Errore query sql!");
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
		}
	}
}
