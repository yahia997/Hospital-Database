-- Fady Work -----------------------------------------------

CREATE TABLE Doctor(
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
	degree VARCHAR(50),
    years_of_exp TINYINT,
    specialization VARCHAR(50)
);

CREATE TABLE Patient(
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    gender VARCHAR(25),
    date_of_birth DATE,
    city VARCHAR(50),
    country VARCHAR(50),
    street_number INT,
    building_number INT,
    email VARCHAR(100) UNIQUE
);

CREATE TABLE Examine (
    id BIGINT PRIMARY KEY,
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    date DATETIME,
    diagnosis VARCHAR(255),
    FOREIGN KEY (doctor_id) REFERENCES Doctor(id), 
	FOREIGN KEY (patient_id) REFERENCES Patient(id)
);

CREATE TABLE Patient_Phone(
    patient_id BIGINT,
    phone VARCHAR(50),
	PRIMARY KEY (patient_id, phone),
    FOREIGN KEY (patient_id) REFERENCES Patient(id)
);