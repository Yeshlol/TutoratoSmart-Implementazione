package project.Control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Model.StudentBean;
import project.Model.StudentDAO;


@WebServlet("/RegisterStudent")
public class RegisterStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterStudentServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("registerStudent.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentDAO studentDAO = new StudentDAO();
		
		String email = request.getParameter("Email");					// Dati Tutor
		String pwd = request.getParameter("Pwd");
		String firstname = request.getParameter("FirstName");
		String lastname = request.getParameter("LastName");
		String telephoneNumber = request.getParameter("TelephoneNumber");
		String sex = request.getParameter("Sex");
		String registrationNumber = request.getParameter("RegistrationNumber");
		String academicYear = request.getParameter("AcademicYear");	
	
		StudentBean student = new StudentBean();
		
		student.setEmail(email);
		student.setPwd(pwd);
		student.setFirstName(firstname);
		student.setLastName(lastname);
		student.setTelephoneNumber(telephoneNumber);
		student.setSex(sex);
		student.setRegistrationNumber(registrationNumber);
		student.setAcademicYear(Integer.parseInt(academicYear));
		
	/*	String role = request.getParameter("role");
		if (role!= null && !role.equals("")) {
			user.setRole(role);
		}
		*/
		
		try {
			studentDAO.doSave(student);
		} catch (SQLException e) {
			response.sendRedirect("registerStudent.jsp");			// Errore nel salvare il nuovo utente
			return;
		}	
		RequestDispatcher dispatcher = request.getRequestDispatcher("Index");
		dispatcher.forward(request, response);
	}
}