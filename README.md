# Hospital_Mangement_System
This is a Project on hotel management system based on core Java and MySQL. This implemented project provides the basic functionality of hotel management system.

## Setup
* Clone the github repo into local computer
  ```shell
      git clone https://github.com/Draksharapu-Dhanunjay/Hospital_Mangement_System.git
  ```
* First create a temporary database in MySQL
  ```shell
        CREATE DATABASE hospital_system;
  ```
* Create a table using
* Patients table to store patients details
  ```shell
         CREATE TABLE patients (
         id INT AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(255) NOT NULL,
         age INT NOT NULL,
         gender VARCHAR(10) NOT NULL
         );
  ```
  * Doctors table to store doctors details
  * We need to add doctors details from backend as we can't add doctors from the app
  ```shell
        CREATE TABLE doctors (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        specialization VARCHAR(255) NOT NULL
        );
  ```
  * Appointments Table to store the appointments
  ```shell
        CREATE TABLE appointments (
        id INT AUTO_INCREMENT PRIMARY KEY,
        patient_id INT NOT NULL,
        doctor_id INT NOT NULL,
        appointment_date DATE NOT NULL,
        FOREIGN KEY (patient_id) REFERENCES patients(id),
        FOREIGN KEY (doctor_id) REFERENCES doctors(id)
        );
  ```
* open the src file in any local IDE then change the url, username, password according to your personal system
* Then run the App.java file
