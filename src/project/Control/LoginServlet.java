package project.Control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.Model.UserBean;
import project.Model.UserDAO;
import project.Utils.Utils;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");

		UserDAO userDAO = new UserDAO();
		UserBean user = null;
		request.getSession().invalidate();
		HttpSession session = request.getSession(true);
		synchronized (session) {
			try {
				user = userDAO.doRetrieveByMail(email);
				if (user != null ) {
					pwd = Utils.sha256(pwd);
					if (pwd.equals(user.getPwd())) {
						session.setAttribute("user", user);
					} else {
						session.setAttribute("user", null);
						response.sendRedirect("refuseLogin.jsp");
						return;
					}
				} else {
					session.setAttribute("user", null);
					response.sendRedirect("refuseLogin.jsp");
					return;
				}
			} catch (SQLException e) {
				return;
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}