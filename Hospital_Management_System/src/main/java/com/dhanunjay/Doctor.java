package com.dhanunjay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private final Connection connection;
    private final Scanner scanner;
    public Doctor(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }
    public void viewDoctors(){
        String query = "SELECT * FROM doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors List");
            System.out.println("+------------+----------------------------+---------+----------+");
            System.out.println("| Doctors Id |            Name            |   Specialization   |");
            System.out.println("+------------+----------------------------+---------+----------+");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("| %-10s | %-26s | %-18s | \n", id, name, specialization);
                System.out.println("+------------+----------------------------+---------+----------+");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public void getDoctorByName(){
        System.out.print("Enter doctor full name :");
        String name = scanner.nextLine();
        try {
            String query = "SELECT * FROM doctors WHERE name LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            ResultSet resultSet= preparedStatement.executeQuery();
            System.out.println("+------------+----------------------------+---------+----------+");
            System.out.println("| Doctors Id |            Name            |   Specialization   |");
            System.out.println("+------------+----------------------------+---------+----------+");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("| %-10s | %-26s | %-18s | \n", id, name, specialization);
                System.out.println("+------------+----------------------------+---------+----------+");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int checkDoctorExist(int id){
        try {
            String query = "SELECT id FROM doctors WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getInt(1) == id){
                    return 1;
                }else{
                    return 0;
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public boolean checkDoctorAvailability(int id, String date){
        try{
            String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()){
                count++;
            }
            if(count > 2){
                return false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
}
