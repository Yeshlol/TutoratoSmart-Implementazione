<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registrazione Tutor</title>
	
	</head>
<body>
<div id="content">
			<div id="registraTutorForm">
				<form method="POST" action="<%= response.encodeURL("RegisterTutor") %>">
				<div id="datiTutorDiv">
					<h1>Registrazione Tutor</h1>				
					<label for="Email">Email: </label>
					<input id="Email" type="email" name="Email" placeholder="Email" maxlength="45" onkeydown="lowerCaseF(this)" required>
					<label id="Email" for="Email"></label><br>
					
					<label for="Password">Password: </label>
					<input id="Password" type="password" name="Password" placeholder="Password" maxlength="10" required> <br>

					<label for="FirstName">Nome: </label>
					<input id="FirstName" type="text" name="FirstName" placeholder="Firstname" maxlength="20" required> <br>
					
					<label for="LastName">Cognome: </label>
					<input id="LastName" type="text" name="LastName" placeholder="Lastname" maxlength="20" required> <br>

					<label for="TelephoneNumber">Numero di telefono: </label>
					<input id="TelephoneNumber" type="tel" name="TelephoneNumber" maxlength="10" placeholder="Telephone Number"> <br>
					
					<label for="M">M</label>
  					<input type="radio" name="Sex" id="M" value="M">
  					<label for="F">F</label>
  					<input type="radio" name="Sex" id="F" value="F"> <br>

  					<label for="RegistrationNumber">Numero di matricola: </label>
  					<input id="RegistrationNumber" type="text" name="RegistrationNumber" maxlength="10" placeholder="Registration Number"><br>
  					  					  					
  					<label for="StartDate">Selezionare la data di inizio contratto: </label>
  					<input id="StartDate" type="date" name="StartDate" placeholder="StartDate"><br>
  					
  					<label for="TotalHours">Numero di ore lavorative del contratto: </label>
  					<input id="TotalHours" type="number" name="TotalHours" placeholder="100" required> <br>
  					
  					<input id="registra" type="submit" name="registraButton" value="Registra">
				</div>
				</form>						
			</div>
		</div>
</body>
</html>