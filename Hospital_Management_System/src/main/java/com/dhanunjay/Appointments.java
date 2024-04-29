package com.dhanunjay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Appointments {
    public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner sc){
        System.out.print("Enter patient id :");
        int patientId = sc.nextInt();
        sc.nextLine();
        int ifExist = patient.checkPatient(patientId);
        if(ifExist == 0){
            System.out.println("Patient Doesn't Exist");
            patient.addPatient();
        }else{
            System.out.print("Enter doctors ID :");
            int doctorId = sc.nextInt();
            sc.nextLine();
            ifExist = doctor.checkDoctorExist(doctorId);
            if(ifExist == 0){
                System.out.println("Doctor doesn't Exist");
            }else{
                System.out.print("Enter date of appointment (YYYY-MM-DD) :");
                String date = sc.nextLine();
                if(doctor.checkDoctorAvailability(doctorId, date)){
                    String query = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?,?,?)";
                    try{
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, patientId);
                        preparedStatement.setInt(2,doctorId);
                        preparedStatement.setString(3, date);
                        int effectedRows = preparedStatement.executeUpdate();
                        if(effectedRows > 0){
                            System.out.println("Appointment Booked!");
                        }else{
                            System.out.println("Appointment Failed to Booked!!!");
                        }
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                }else{
                    System.out.println("Doctor Not Available!!!");
                }
            }
        }
    }
    public static void exit() {
        System.out.print("THANK YOU! FOR USING HOSPITAL MANAGEMENT SYSTEM");
        for(int i = 0; i < 3; i++){
            System.out.print(".");
            try{
                Thread.sleep(1500);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
