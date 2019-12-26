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


@WebServlet("/Registration2")
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
		String pwd = request.getParameter("Password");
		String firstname = request.getParameter("FirstName");
		String lastname = request.getParameter("LastName");
		String telephoneNumber = request.getParameter("TelephoneNumber");
		String sex = request.getParameter("Sex");
		String registrationNumber = request.getParameter("RegistrationNumber");
		
		
		String startDate = request.getParameter("StartDate");
		int totalHours = Integer.parseInt(request.getParameter("TotalHours"));
		
		
		TutorBean tutor = new TutorBean();
		
		tutor.setEmail(email);
		tutor.setPwd(pwd);
		tutor.setFirstName(firstname);
		tutor.setLastName(lastname);
		tutor.setTelephoneNumber(telephoneNumber);
		tutor.setSex(sex);
		tutor.setRegistrationNumber(registrationNumber);
		
		tutor.setStartDate(Date.valueOf(startDate));
		tutor.setCommissionMember("d.molinaro@commissione.unicampania.it");
				
		try {
			tutorDAO.doSave(tutor, totalHours);
		} catch (SQLException e) {
			response.sendRedirect("registerTutor.jsp");			// Errore nel salvare il nuovo tutor
			return;
		}	
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
}