CREATE USER 'tester'@'localhost' IDENTIFIED BY 'test';
GRANT ALL PRIVILEGES ON *.* TO 'tester'@'localhost';

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
