package project.Control.User;

import java.io.IOException;
import java.sql.SQLException;
//import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import project.Model.StudentBean;
import project.Model.StudentDAO;
import project.Model.UserBean;
import project.Model.UserDAO;

/**
 * 
 *  Servlet implementation class RegistrationServlet
 *
 */

@WebServlet("/Registration")
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
		boolean ajax = Boolean.parseBoolean(request.getParameter("ajax"));  // Richieste ajax per validazione email
				
		if(ajax) {
			try {
				UserDAO userDAO = new UserDAO();
				UserBean beanAjax = null;
							
				JSONObject obj = new JSONObject();
				
				String email = request.getParameter("mail");
				
				if(email!=null) 
					beanAjax = userDAO.doRetrieveByMail(email);			
								
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				obj.put("disponibile", beanAjax == null);
				
				response.getWriter().write(obj.toString());
				return;
			} catch(SQLException e) { 
				response.sendRedirect("registration.jsp");			// Errore query
				return;
			} catch(JSONException e) {
				response.sendRedirect("registration.jsp"); 			// Errore parser json
				return;
			}
		}
				
		String email = request.getParameter("Email");				// Dati Studente
		String pwd = request.getParameter("Password");
		String verifypwd = request.getParameter("VerifyPassword");
		String firstname = request.getParameter("FirstName");
		String lastname = request.getParameter("LastName");
		String telephoneNumber = request.getParameter("TelephoneNumber");
		String sex = request.getParameter("Sex");
		String registrationNumber = request.getParameter("RegistrationNumber");
		String academicYear = request.getParameter("AcademicYear");
																	
		// Validazione dati immessi
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
				
		StudentBean student = new StudentBean();				
		
		student.setEmail(email);
		student.setPwd(pwd);
		student.setFirstName(firstname);
		student.setLastName(lastname);
		student.setTelephoneNumber(telephoneNumber);
		student.setSex(sex);
		student.setRegistrationNumber(registrationNumber);
		student.setAcademicYear(Integer.parseInt(academicYear));
		
		try {
			StudentDAO studentDAO = new StudentDAO();
			studentDAO.doSave(student);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			obj.put("result", 1);				
		} catch (SQLException e) {				// Errore nel salvare il nuovo studente
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