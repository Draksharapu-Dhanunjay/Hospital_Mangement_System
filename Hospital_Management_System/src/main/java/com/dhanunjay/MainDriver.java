package com.dhanunjay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainDriver {
    private final static String url = "jdbc:mysql://localhost:3306/hospital_system";
    private final static String username = "root";
    private final static String password = "Admin@123";

    public static void start() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        Scanner sc = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection, sc);
            Doctor doctor = new Doctor(connection, sc);
            System.out.println("Welcome To Hospital Management System");
            while (true){
                System.out.println("Main Menu");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patient");
                System.out.println("3. Check Patient Exists");
                System.out.println("4. Get Patient Details");
                System.out.println("5. View Doctors");
                System.out.println("6. Doctor Details by Name");
                System.out.println("7. Book Appointment");
                System.out.println("8. Exit");
                System.out.print("Enter your choice:");
                int option = sc.nextInt();
                sc.nextLine();
                if(option == 8){
                    Appointments.exit();
                    break;
                }
                switch (option){
                    case 1:
                        patient.addPatient();
                        break;
                    case 2:
                        patient.viewPatient();
                        break;
                    case 3:
                        patient.checkPatientByName();
                        break;
                    case 4:
                        patient.getPatientDetails();
                        break;
                    case 5:
                        doctor.viewDoctors();
                        break;
                    case 6:
                        doctor.getDoctorByName();
                        break;
                    case 7:
                        Appointments.bookAppointment(patient, doctor, connection, sc);
                        break;
                    default:
                        System.out.println("Enter a valid input!!!");
                }
            }
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        sc.close();
    }

}
