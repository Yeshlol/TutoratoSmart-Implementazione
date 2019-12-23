<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registrazione Studente</title>
	
	</head>
<body>
<div id="content">
			<div id="registraUserForm">
				<form method="POST" action="<%= response.encodeURL("RegisterStudent") %>">
				<div id="datiStudentDiv">
					<h1>Nuovo Utente [STUDENTE]</h1>				
				<!-- 	<input name="role" type="hidden" value="USER">  -->
					
									
					<label for="Email">Email: </label>
					<input id="Email" type="email" name="Email" placeholder="Email" maxlength="45" onkeydown="lowerCaseF(this)" required>
					<label id="Email" for="Email"></label><br>
					
					<label for="Password">Scegli una password con almeno una lettera maiuscola e un numero: </label>
					<input id="Password" type="password" name="Password" placeholder="Password" maxlength="10" required> <br>

					<label for="FirstName">Scegli un nome con lettera maiuscola e lunghezza di massimo 20 caratteri: </label>
					<input id="FirstName" type="text" name="FirstName" placeholder="Firstname" maxlength="20" required> <br>
					
					<label for="LastName">Scegli un cognome con lettera maiuscola e lunghezza di massimo 20 caratteri: </label>
					<input id="LastName" type="text" name="LastName" placeholder="Lastname" maxlength="20" required> <br>

					<label for="TelephoneNumber">Scegli un numero di telefono con lunghezza di massimo 12 caratteri: </label>
					<input id="TelephoneNumber" type="tel" name="TelephoneNumber" maxlength="12" placeholder="Telephone Number"> <br>
					
					<label for="M">M</label>
  					<input type="radio" name="Sex" id="M" value="M">
  					<label for="F">F</label>
  					<input type="radio" name="Sex" id="F" value="F"> <br>

  					<label for="RegistrationNumber">Scegli una matricola con massimo 10 caratteri: </label>
  					<input id="RegistrationNumber" type="text" name="RegistrationNumber" maxlength="10" placeholder="Registration Number"><br>
  					
  					<label for="AcademicYear">Scegli un anno accademico: </label>
  					<input id="AcademicYear" type="date" name="AcademicYear" placeholder="Academic Year"><br>
  					
  					<input id="button" type="button" onclick="validateInputs()" name="registraButton" value="Registra">
				</div>
				</form>						
			</div>
		</div>
</body>
</html>