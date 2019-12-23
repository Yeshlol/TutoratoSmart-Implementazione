package project.Control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.Model.TutorBean;
import project.Model.TutorDAO;


@WebServlet("/RegisterTutor")
public class RegisterTutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	public RegisterTutorServlet() {
        super();
    }
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("registerTutor.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TutorDAO tutorDAO = new TutorDAO();
			
		String email = request.getParameter("Email");					// Dati Tutor
		String pwd = request.getParameter("Pwd");
		String firstname = request.getParameter("FirstName");
		String lastname = request.getParameter("LastName");
		String telephoneNumber = request.getParameter("TelephoneNumber");
		String sex = request.getParameter("Sex");
		String registrationNumber = request.getParameter("RegistrationNumber");
		String state = request.getParameter("State");
		String startDate = request.getParameter("StartDate");
		String finishDate = request.getParameter("FinishDate");
		String commissionMember = request.getParameter("CommissionMember");
		String registerId = request.getParameter("RegisterId");
		
		
		TutorBean tutor = new TutorBean();
		
		tutor.setEmail(email);
		tutor.setPwd(pwd);
		tutor.setFirstName(firstname);
		tutor.setLastName(lastname);
		tutor.setTelephoneNumber(telephoneNumber);
		tutor.setSex(sex);
		tutor.setRegistrationNumber(registrationNumber);
		tutor.setState(state);
		tutor.setStartDate(Date.valueOf(startDate));
		tutor.setFinishDate(Date.valueOf(finishDate));
		tutor.setCommissionMember(commissionMember);
		tutor.setRegisterId(Integer.parseInt(registerId));
		
	/*	String role = request.getParameter("role");
		if (role!= null && !role.equals("")) {
			user.setRole(role);
		}
		*/
		
		try {
			tutorDAO.doSave(tutor);
		} catch (SQLException e) {
			response.sendRedirect("registerTutor.jsp");			// Errore nel salvare il nuovo utente
			return;
		}	
		RequestDispatcher dispatcher = request.getRequestDispatcher("Index");
		dispatcher.forward(request, response);
	}
}