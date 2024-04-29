package com.dhanunjay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private final Connection connection;
    private final Scanner sc;

    public Patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.sc = scanner;
    }

        public void addPatient(){
        System.out.print("Enter patient name :");
        String name = sc.nextLine();
        System.out.print("Enter age :");
        int age = sc.nextInt();
        System.out.print("Select Gender 1)Male 2)Female 3)Trans");
        System.out.println();
        int option = sc.nextInt();
        sc.nextLine();
        String gender = null;
        switch (option){
            case 1:
                gender = "Male";
                break;
            case 2:
                gender = "Female";
                break;
            case 3:
                gender = "Trans";
                break;
            default:
                System.out.println("Enter a valid Gender!!!");
        }
        try{
            String query = "INSERT INTO patients(name, age, gender) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            int effectedRows = preparedStatement.executeUpdate();
            if(effectedRows > 0){
                System.out.println("Patient Details Insertion Successfully");
            }else{
                System.out.println("Patient Details Insertion Failed");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void viewPatient(){
        String query = "SELECT * FROM patients";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients List");
            System.out.println("+------------+----------------------------+-------+--------+");
            System.out.println("| Patient Id |            Name            |  Age  | Gender |");
            System.out.println("+------------+----------------------------+-------+--------+");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-10s | %-26s | %-5s | %-6s | \n", id, name, age, gender);
                System.out.println("+------------+----------------------------+-------+--------+");
            }
            preparedStatement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public void checkPatientByName(){
        System.out.print("Enter Patient Name :");
        String name = sc.nextLine();
        try {
            String query = "SELECT * FROM patients WHERE name LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                System.out.println("Patient Exist");
            }else{
                System.out.println("Patient Not Found");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void getPatientDetails(){
        System.out.print("Enter Patient Name :");
        String name = sc.nextLine();
        try {
            String query = "SELECT * FROM patients WHERE name LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet= preparedStatement.executeQuery();
            System.out.println("Patients");
            System.out.println("+------------+----------------------------+-------+--------+");
            System.out.println("| Patient Id |            Name            |  Age  | Gender |");
            System.out.println("+------------+----------------------------+-------+--------+");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-10s | %-26s | %-5s | %-6s | \n", id, name, age, gender);
                System.out.println("+------------+----------------------------+-------+--------+");
            }
            preparedStatement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int checkPatient(int id){
        try {
            String query = "SELECT id FROM patients WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
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
}
