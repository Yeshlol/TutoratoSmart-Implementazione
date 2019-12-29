<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Nuova richiesta di appuntamento</title>
</head>
<body>
	<div id="content">
		<div id="registraUserForm">
			<form method="POST" action="<%= response.encodeURL("RegisterStudent") %>">
				<div id="datiStudentDiv">
					<h1>Registrazione Studente</h1>
					<input type="hidden" name="flag" value="1">				
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
					
					<label for="AcademicYear">Anno Accademico: </label>
					<select id="AcademicYear" name="AcademicYear">
						<option value="1">Primo Anno - Triennale</option>
						<option value="2">Secondo Anno - Triennale</option>
						<option value="3">Terzo Anno - Triennale</option>
						<option value="4">Primo Anno - Magistrale</option>
						<option value="5">Secondo Anno - Magistrale</option>
					</select>
							
					<input id="registra" type="submit" name="registraButton" value="Registra">
				</div>
			</form>						
		</div>
	</div>
</body>
</html>