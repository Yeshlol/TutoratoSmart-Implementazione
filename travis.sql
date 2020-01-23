CREATE USER 'tester'@'localhost' IDENTIFIED BY 'test';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON *.* TO 'tester'@'localhost';

CREATE SCHEMA TutoratoSmartTest;
	
USE TutoratoSmartTest;

CREATE TABLE TS_USER
(	Email				VARCHAR(45)		NOT NULL,
    Pwd					VARCHAR(64)		NOT NULL,
    UserRole			TINYINT(1) 		NOT NULL,			-- 1 = CommissionMember, 2 = Tutor, 3 = Student
	FirstName			VARCHAR(30)		NOT NULL,
    LastName			VARCHAR(30)		NOT NULL,
    TelephoneNumber		VARCHAR(10)		NOT NULL,
    Sex					ENUM('M', 'F')  NOT NULL,
    RegistrationNumber	CHAR(10),
PRIMARY KEY (Email),
UNIQUE (RegistrationNumber));

CREATE TABLE STUDENT
(	Email				VARCHAR(45)		NOT NULL,
	AcademicYear		INT				NOT NULL,
PRIMARY KEY (Email),
FOREIGN KEY (Email) REFERENCES TS_USER(Email));

CREATE TABLE REGISTER
(	IdRegister			INT				NOT NULL	AUTO_INCREMENT,
	State				ENUM('Non approvato', 'Approvato')		NOT NULL 	DEFAULT 'Non approvato',
    ValidatedHours		FLOAT			NOT NULL	DEFAULT 0,
    TotalHours			INT				NOT NULL,
    PercentageComplete	FLOAT			NOT NULL	DEFAULT 0,
PRIMARY KEY (IdRegister));

CREATE TABLE TUTOR
(	Email				VARCHAR(45)		NOT NULL,
	State				ENUM('Attivo', 'Non Attivo')		NOT NULL		DEFAULT 'Attivo',
	StartDate			DATE			NOT NULL,
    FinishDate			DATE,
    CommissionMember	VARCHAR(45)		NOT NULL,
    RegisterId			INT				NOT NULL,
PRIMARY KEY (Email),
FOREIGN KEY (Email) REFERENCES TS_USER(Email),
FOREIGN KEY (CommissionMember) REFERENCES TS_USER(Email),
FOREIGN KEY (RegisterId) REFERENCES REGISTER(IdRegister));

CREATE TABLE REQUEST
(	IdRequest			INT				NOT NULL	AUTO_INCREMENT,
	State				ENUM('In valutazione', 'Accettata', 'Appuntamento effettuato', 'Studente assente')  NOT NULL	DEFAULT 'In valutazione',
	StudentComment		VARCHAR(320)	NOT NULL,
    RequestDate			DATE			NOT NULL,
    RequestTime			INT				NOT NULL,
    Duration			INT				NOT NULL	DEFAULT '15',
    Student				VARCHAR(45)		NOT NULL,
PRIMARY KEY (IdRequest),
FOREIGN KEY (Student) REFERENCES STUDENT(Email));

CREATE TABLE APPOINTMENT
(	IdAppointment		INT				NOT NULL	AUTO_INCREMENT,
	Details				VARCHAR(320)	NOT NULL,
    RequestId			INT				NOT NULL,
    Tutor				VARCHAR(45)		NOT NULL,
PRIMARY KEY (IdAppointment),
FOREIGN KEY (RequestId) REFERENCES REQUEST(IdRequest),
FOREIGN KEY (Tutor) REFERENCES TUTOR(Email));

CREATE TABLE ACTIVITY_TUTOR
(	IdActivity			INT				NOT NULL	AUTO_INCREMENT,
	Category			ENUM('Sportello Tutorato', 'Assistenza Esame', 'Organizzazione Seminario', 'Seminario', 'Organizzazione Evento', 'Evento')	NOT NULL,
	ActivityDate		DATE			NOT NULL,
    StartTime			INT				NOT NULL,
    FinishTime			INT				NOT NULL,
    Hours				FLOAT			NOT NULL,
    State				ENUM('In valutazione', 'Convalidata')	NOT NULL	DEFAULT 'In valutazione',
    Details				VARCHAR(320)	NOT NULL,
    Tutor				VARCHAR(45)		NOT NULL,
    RegisterId			INT				NOT NULL,
PRIMARY KEY (IdActivity),
FOREIGN KEY (Tutor) REFERENCES TS_USER(Email),
FOREIGN KEY (RegisterId) REFERENCES REGISTER(IdRegister));

CREATE TABLE MANAGES
(	Tutor 				VARCHAR(45)		NOT NULL,
	RequestId			INT				NOT NULL,
PRIMARY KEY (Tutor, RequestId),
FOREIGN KEY (Tutor) REFERENCES TUTOR(Email),
FOREIGN KEY (RequestId) REFERENCES REQUEST(IdRequest));

CREATE TABLE CONTAINED_IN
(	AppointmentId		INT				NOT NULL,
	ActivityId			INT				NOT NULL,
PRIMARY KEY (AppointmentId, ActivityId),
FOREIGN KEY (AppointmentId) REFERENCES APPOINTMENT(IdAppointment) 	ON DELETE CASCADE,
FOREIGN KEY (ActivityId) REFERENCES ACTIVITY_TUTOR(IdActivity) 		ON DELETE CASCADE);

CREATE TABLE VALIDATES
(	CommissionMember	VARCHAR(45)		NOT NULL,
	ActivityId			INT				NOT NULL,
PRIMARY KEY (CommissionMember, ActivityId),
FOREIGN KEY (CommissionMember) REFERENCES TS_USER(Email),
FOREIGN KEY (ActivityId) REFERENCES ACTIVITY_TUTOR(IdActivity) ON DELETE CASCADE);


-- Add Data
Use TutoratoSmartTest;


-- Users:
INSERT INTO TS_USER (Email,Pwd,UserRole,FirstName,LastName,TelephoneNumber,Sex) VALUES ('d.molinaro@commissione.unicampania.it',SHA2('M12345678', 256),1,'Danila','Molinari','3374488832','F');

INSERT INTO TS_USER (Email,Pwd,UserRole,FirstName,LastName,TelephoneNumber,Sex,RegistrationNumber) VALUES ('m.pisciotta@studenti.unicampania.it',SHA2('M12345678', 256),2,'Manuel','Pisciotta','3343355632','M','A512102493');
INSERT INTO TS_USER (Email,Pwd,UserRole,FirstName,LastName,TelephoneNumber,Sex,RegistrationNumber) VALUES ('c.ferrari@studenti.unicampania.it',SHA2('M12345678', 256),2,'Caterina','Ferrari','3343355633','F','B512102494');
INSERT INTO TS_USER (Email,Pwd,UserRole,FirstName,LastName,TelephoneNumber,Sex,RegistrationNumber) VALUES ('m.lombardo@studenti.unicampania.it',SHA2('M12345678', 256),2,'Marta','Lombardo','3343355634','F','A212102495');

INSERT INTO TS_USER (Email,Pwd,UserRole,FirstName,LastName,TelephoneNumber,Sex,RegistrationNumber) VALUES ('g.luongo@studenti.unicampania.it',SHA2('M12345678', 256),3,'Gianluca','Luongo','3343355636','M','B512102496');
INSERT INTO TS_USER (Email,Pwd,UserRole,FirstName,LastName,TelephoneNumber,Sex,RegistrationNumber) VALUES ('e.merola@studenti.unicampania.it',SHA2('M12345678', 256),3,'Eduardo','Merola','3343355637','M','A512102497');
INSERT INTO TS_USER (Email,Pwd,UserRole,FirstName,LastName,TelephoneNumber,Sex,RegistrationNumber) VALUES ('a.tommasino@studenti.unicampania.it',SHA2('M12345678', 256),3,'Antonio','Tommasino','3343355638','M','B512102498');


-- Students:
INSERT INTO STUDENT(Email,AcademicYear) VALUES ('g.luongo@studenti.unicampania.it','1');
INSERT INTO STUDENT(Email,AcademicYear) VALUES ('e.merola@studenti.unicampania.it','5');
INSERT INTO STUDENT(Email,AcademicYear) VALUES ('a.tommasino@studenti.unicampania.it','3');


-- Registers:
INSERT INTO REGISTER(IdRegister,State,ValidatedHours,TotalHours,PercentageComplete) VALUES (1, 'Non approvato', 0, 10, 30);
INSERT INTO REGISTER(IdRegister,State,ValidatedHours,TotalHours,PercentageComplete) VALUES (2, 'Non approvato', 0, 10, 10);
INSERT INTO REGISTER(IdRegister,State,ValidatedHours,TotalHours,PercentageComplete) VALUES (3, 'Approvato', 4, 4, 100);


-- Tutors:
INSERT INTO TUTOR(Email,StartDate,FinishDate,CommissionMember,RegisterId) VALUES ('m.pisciotta@studenti.unicampania.it','2019-10-23','2019-12-26','d.molinaro@commissione.unicampania.it', 1);
INSERT INTO TUTOR(Email,StartDate,FinishDate,CommissionMember,RegisterId) VALUES ('c.ferrari@studenti.unicampania.it','2019-10-22','2019-12-27','d.molinaro@commissione.unicampania.it', 2);
INSERT INTO TUTOR(Email,StartDate,FinishDate,CommissionMember,RegisterId) VALUES ('m.lombardo@studenti.unicampania.it','2019-09-21','2019-12-28','d.molinaro@commissione.unicampania.it', 3);


-- Activity
INSERT INTO ACTIVITY_TUTOR(IdActivity,Category,ActivityDate,StartTime,FinishTime,Hours,State,Details,Tutor,RegisterId) VALUES (1,'Sportello Tutorato', '2019-11-24', 540, 900, 6, 'Convalidata', 'L'' attività riguarda informazioni relative all''immatricolazione dello studente', 'm.pisciotta@studenti.unicampania.it', 1);
INSERT INTO ACTIVITY_TUTOR(IdActivity,Category,ActivityDate,StartTime,FinishTime,Hours,State,Details,Tutor,RegisterId) VALUES (2,'Assistenza Esame', '2019-11-25', 720, 780, 1, 'In valutazione', 'L''attività riguarda il supporto allo studente per l''esame di matematica discreta', 'c.ferrari@studenti.unicampania.it', 2);
INSERT INTO ACTIVITY_TUTOR(IdActivity,Category,ActivityDate,StartTime,FinishTime,Hours,State,Details,Tutor,RegisterId) VALUES (3, 'Organizzazione Seminario', '2019-10-26', 540, 780, 4, 'Convalidata', 'L''attività riguarda l''organizzazione di un seminario di orientamento al lavoro', 'm.lombardo@studenti.unicampania.it', 3);


-- Request
INSERT INTO REQUEST(IdRequest,State,StudentComment,RequestDate,RequestTime,Duration,Student) VALUES (1, 'Accettata', 'Supporto Immatricolazione', '2019-11-24', 720, 80, 'g.luongo@studenti.unicampania.it');
INSERT INTO REQUEST(IdRequest,State,StudentComment,RequestDate,RequestTime,Duration,Student) VALUES (2, 'Accettata', 'Aiuto preparazione esame', '2019-11-25', 660, 40, 'e.merola@studenti.unicampania.it');
INSERT INTO REQUEST(IdRequest,State,StudentComment,RequestDate,RequestTime,Duration,Student) VALUES (3, 'Accettata', 'Non riesco a prenotarmi per la prova intercorso', '2019-10-26', 840, 30, 'a.tommasino@studenti.unicampania.it');
INSERT INTO REQUEST(IdRequest,State,StudentComment,RequestDate,RequestTime,Duration,Student) VALUES (4, 'In valutazione', 'Supporto prenotazione esame', '2019-11-26', 600, 20, 'e.merola@studenti.unicampania.it');
INSERT INTO REQUEST(IdRequest,State,StudentComment,RequestDate,RequestTime,Duration,Student) VALUES (5, 'In valutazione', 'Preparazione prova scritta', '2019-11-27', 780, 15, 'g.luongo@studenti.unicampania.it');


-- Appointment
INSERT INTO APPOINTMENT(IdAppointment,Details,RequestId,Tutor) VALUES (1, 'Supporto immatricolazione studenti', '1', 'm.pisciotta@studenti.unicampania.it');
INSERT INTO APPOINTMENT(IdAppointment,Details,RequestId,Tutor) VALUES (2,'Aiuto per la preparazione dell''esame di matematica discreta', '2', 'c.ferrari@studenti.unicampania.it');
INSERT INTO APPOINTMENT(IdAppointment,Details,RequestId,Tutor) VALUES (3,'Supporto per l''organizzazione seminario orientamento al lavoro', '3', 'm.lombardo@studenti.unicampania.it');


-- Contained_in
INSERT INTO CONTAINED_IN(AppointmentId,ActivityId) VALUES (1, 1);
INSERT INTO CONTAINED_IN(AppointmentId,ActivityId) VALUES (2, 2);
INSERT INTO CONTAINED_IN(AppointmentId,ActivityId) VALUES (3, 3);


-- Manages
INSERT INTO MANAGES(Tutor,RequestId) VALUES ('m.pisciotta@studenti.unicampania.it', 1);
INSERT INTO MANAGES(Tutor,RequestId) VALUES ('c.ferrari@studenti.unicampania.it', 2);
INSERT INTO MANAGES(Tutor,RequestId) VALUES ('m.lombardo@studenti.unicampania.it', 3);


-- Validates
INSERT INTO VALIDATES(CommissionMember,ActivityId) VALUES('d.molinaro@commissione.unicampania.it', 1);
INSERT INTO VALIDATES(CommissionMember,ActivityId) VALUES('d.molinaro@commissione.unicampania.it', 3);