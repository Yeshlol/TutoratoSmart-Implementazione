package project.Control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Model.UserBean;
import project.Model.UserDAO;


@WebServlet("/RegisterUser")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("registerUser.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    UserDAO userDAO = new UserDAO();
		
		String email = request.getParameter("Email");					// Dati Tutor
		String pwd = request.getParameter("Pwd");
		String firstname = request.getParameter("FirstName");
		String lastname = request.getParameter("LastName");
		String telephoneNumber = request.getParameter("TelephoneNumber");
		String sex = request.getParameter("Sex");
		String registrationNumber = request.getParameter("RegistrationNumber");
	
		UserBean user = new UserBean();
		
		user.setEmail(email);
		user.setPwd(pwd);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setTelephoneNumber(telephoneNumber);
		user.setSex(sex);
		user.setRegistrationNumber(registrationNumber);
		
	/*	String role = request.getParameter("role");
		if (role!= null && !role.equals("")) {
			user.setRole(role);
		}
		*/
		
		try {
			userDAO.doSave(user);
		} catch (SQLException e) {
			response.sendRedirect("registerUser.jsp");			// Errore nel salvare il nuovo utente
			return;
		}	
		RequestDispatcher dispatcher = request.getRequestDispatcher("Index");
		dispatcher.forward(request, response);
	}
}
