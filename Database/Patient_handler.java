package com.example.temp.DB_HANDLER;

import java.sql.*;

public class Patient_handler {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // JDBC variables for opening, closing and managing connection
    private static Connection connection;

    // SQL queries

    public static final String INSERT_PATIENT = "INSERT INTO Patient (id, name, bloodType, DOB) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_PATIENT = "UPDATE Patient SET name=?, bloodType=?, DOB=? WHERE id=?";
    public static final String SELECT_ALL_PATIENTS = "SELECT * FROM Patient";
    public static final String SELECT_PATIENT_BY_ID = "SELECT * FROM Patient WHERE id=?";
    public static final String DELETE_PATIENT = "DELETE FROM Patient WHERE id=?";

    // Static block to load the JDBC driver and establish a connection
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    // Method to insert a new patient
    public static void insertPatient(int id, String name, String bloodType, Date DOB) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, bloodType);
            preparedStatement.setDate(4, DOB);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update patient details
    public static void updatePatient(int id, String name, String bloodType, Date DOB) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PATIENT)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, bloodType);
            preparedStatement.setDate(3, DOB);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all patients
    public static ResultSet getAllPatients() {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(SELECT_ALL_PATIENTS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve a specific patient by ID
    public static ResultSet getPatientById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to delete a patient by ID
    public static void deletePatient(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PATIENT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
