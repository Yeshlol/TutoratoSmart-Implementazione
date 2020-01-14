package project.Control;

import java.io.IOException;
import java.sql.Date;
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
import project.Model.TutorBean;
import project.Model.TutorDAO;
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
				
				obj.put("disponibile", beanAjax.getEmail().equals(""));
				
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
				
		String email = request.getParameter("Email");				// Dati Utente
		String pwd = request.getParameter("Password");
		String verifypwd = request.getParameter("VerifyPassword");
		String firstname = request.getParameter("FirstName");
		String lastname = request.getParameter("LastName");
		String telephoneNumber = request.getParameter("TelephoneNumber");
		String sex = request.getParameter("Sex");
		String registrationNumber = request.getParameter("RegistrationNumber");
		
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
		
		int flag = Integer.parseInt(request.getParameter("flag"));	// Flag passato nello script ajax: 1 = registrazione studente, 2 = registrazione tutor.
		JSONObject obj = new JSONObject();
		
		if (flag == 1) { 											// Registrazione nuovo Studente
			StudentDAO studentDAO = new StudentDAO();
			
			String academicYear = request.getParameter("AcademicYear");	// Dati Studente
			
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
		
		else if (flag == 2) {														// Registrazione nuovo Tutor
			TutorDAO tutorDAO = new TutorDAO();
							
			String startDate = request.getParameter("StartDate");	// Dati Tutor
			int totalHours = -1;
			
			try {
				totalHours = Integer.parseInt(request.getParameter("TotalHours"));
			} catch(NumberFormatException e) {
				throw new IllegalArgumentException("Formato ore contrattuali non valido");
			}
			if (totalHours < 30 || totalHours > 150) {
				throw new IllegalArgumentException("Numero di ore contrattuali non valido");
			}
			
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
	
/*	
	private boolean valida(String email, String pwd, String firstname, String lastname, String telephoneNumber, String sex, String registrationNumber, String academicYear) throws SQLException {
		boolean valido=true;
		String expEmail="^\\w+([\\.-]?\\w+)*@studenti.unicampania.it$";
		String expPwd="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,10}$";
		String expFirstName="^[A-Z]{1}[a-zA-Z\\s]{2,30}$";
		String expLastName="^[A-Z]{1}[a-zA-Z\\s]{2,30}$";
		String expTelephoneNumber="^\\d{9,10}$";
		String expSex="";
		String expRegistrationNumber="^[AB]\\d{5,9}$";
		String expAcademicYear="";

		
		if (!Pattern.matches(expEmail, email)) {
			valido=false;
			System.out.print(email);
		}
		if (!Pattern.matches(expPwd, pwd)) {
			valido=false;
			System.out.print(pwd);
		}
		if (!Pattern.matches(expFirstName, firstname)) {
			valido=false;
			System.out.print(firstname);
		}	
		if (!Pattern.matches(expLastName, lastname)) {
			valido=false;
			System.out.print(lastname);
		}
		if (!Pattern.matches(expTelephoneNumber, telephoneNumber)) {
			valido=false;
			System.out.print(telephoneNumber);
		}
		if (!Pattern.matches(expSex, sex)) {
			valido=false;
			System.out.print(sex);
		}
		if (!Pattern.matches(expRegistrationNumber, registrationNumber)) {
			valido=false;
			System.out.print(registrationNumber);
		}
		if (!Pattern.matches(expAcademicYear, academicYear)) {
			valido=false;
			System.out.print(academicYear);
		}
		
		return valido;
	}*/
}