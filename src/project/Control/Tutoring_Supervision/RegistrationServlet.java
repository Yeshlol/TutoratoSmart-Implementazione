package project.Control.Tutoring_Supervision;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import project.Model.TutorBean;
import project.Model.TutorDAO;
import project.Model.UserBean;

/**
 * 
 *  Servlet implementation class RegistrationServlet
 *
 */

@WebServlet("/RegistrationTutoringSupervision")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * Constructor
	 *
     * @see HttpServlet#HttpServlet()
     */
	
	public RegistrationServlet() {
        super();
    }
	
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
		String email = request.getParameter("Email");				// Dati Tutor
		String pwd = request.getParameter("Password");
		String verifypwd = request.getParameter("VerifyPassword");
		String firstname = request.getParameter("FirstName");
		String lastname = request.getParameter("LastName");
		String telephoneNumber = request.getParameter("TelephoneNumber");
		String sex = request.getParameter("Sex");
		String registrationNumber = request.getParameter("RegistrationNumber");
		String startDate = request.getParameter("StartDate");
		int totalHours = -1;
																	
																	// Validazione dati in input
		if (lastname.length() < 3 || lastname.length() > 30) {
			throw new IllegalArgumentException("Lunghezza cognome non valida");
	    }
		
		String lastNameFormat = "[A-Z]{1}[a-zA-Z\\s]{2,29}";
		
		if (!lastname.matches(lastNameFormat)) {
			throw new IllegalArgumentException("Formato cognome non valido");
		}
		
		if (firstname.length() < 3 || firstname.length() > 30) {
			throw new IllegalArgumentException("Lunghezza nome non valida");
	    }		
		
		String firstNameFormat = "[A-Z]{1}[a-zA-Z\\s]{2,29}";
		
		if (!firstname.matches(firstNameFormat)) {
			throw new IllegalArgumentException("Formato nome non valido");
		}
		
		if (email.length() == 0 || email.length() > 45) {
			throw new IllegalArgumentException("Lunghezza email non valida");
	    }
		
		String mailFormat1 = "\\w+([\\.-]?\\w+)*@studenti.unicampania.it";
		String mailFormat2 = "\\w+([\\.-]?\\w+)*@commissione.unicampania.it";
		String mailFormat3 = "\\w+([\\.-]?\\w+)*@unicampania.it";
		
		if (!(email.matches(mailFormat1) || email.matches(mailFormat2) || email.matches(mailFormat3))) {
			throw new IllegalArgumentException("Formato email non valido");
		}
		
		if (pwd.length() < 8 || pwd.length() > 10) {
			throw new IllegalArgumentException("Lunghezza password non valida");
	    }
		
		String passwordFormat = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,10}";
		
		if (!pwd.matches(passwordFormat)) {
			throw new IllegalArgumentException("Formato password non valido");
		}
		
		if(!pwd.equals(verifypwd)) {
			throw new IllegalArgumentException("Le due password inserite non corrispondono");			
		}
		
		if (registrationNumber.length() < 6 || registrationNumber.length() > 10) {
			throw new IllegalArgumentException("Lunghezza matricola non valida");
	    }
		
		String registrationNumberFormat = "[AB]\\d{5,9}";
		
		if (!registrationNumber.matches(registrationNumberFormat)) {
			throw new IllegalArgumentException("Formato matricola non valido");
		}
		
		if (telephoneNumber.length() < 9 || telephoneNumber.length() > 10) {
			throw new IllegalArgumentException("Lunghezza numero di telefono non valida");
		}
		
		String telephoneNumberFormat = "\\d{9,10}";
		
		if (!telephoneNumber.matches(telephoneNumberFormat)) {
			throw new IllegalArgumentException("Formato numero di telefono non valido");
		}
		
		JSONObject obj = new JSONObject();
				
		try {
			totalHours = Integer.parseInt(request.getParameter("TotalHours"));
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException("Formato ore contrattuali non valido");
		}
		if (totalHours < 30 || totalHours > 150) {
			throw new IllegalArgumentException("Numero di ore contrattuali non valido");
		}
																
		TutorDAO tutorDAO = new TutorDAO();						// Registrazione nuovo Tutor	
		
		UserBean commissionMember = (UserBean) request.getSession().getAttribute("user");
		
		TutorBean tutor = new TutorBean();
		
		tutor.setEmail(email);
		tutor.setPwd(pwd);
		tutor.setFirstName(firstname);
		tutor.setLastName(lastname);
		tutor.setTelephoneNumber(telephoneNumber);
		tutor.setSex(sex);
		tutor.setRegistrationNumber(registrationNumber);
		tutor.setStartDate(Date.valueOf(startDate));
		tutor.setCommissionMember(commissionMember.getEmail());
				
		try {
			tutorDAO.doSave(tutor, totalHours);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			obj.put("result", 1);				
		} catch (SQLException e) {				// Errore nel salvare il nuovo tutor
			try {
				obj.put("result", 2);			
			} catch (JSONException jsonexp) {	// Errore parser json					
			}
		} catch (JSONException jsonexp) {		// Errore parser json
		}
		finally {
			response.getWriter().write(obj.toString());
		}
		
		return;
	}
}
