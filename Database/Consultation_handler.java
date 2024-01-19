package com.example.temp.DB_HANDLER;

import com.example.temp.OOP.Consultation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Consultation_handler {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    // SQL queries for Consultation table
    public static final String INSERT_CONSULTATION = "INSERT INTO Consultation (status, date, time, drID, patientID) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_CONSULTATION_STATUS = "UPDATE Consultation SET status=? WHERE consultationID=?";
    public static final String SELECT_CONSULTATIONS_BY_DRID = "SELECT * FROM Consultation WHERE drID=?";
    public static final String SELECT_CONSULTATIONS_BY_PATIENTID = "SELECT * FROM Consultation WHERE patientID=?";
    public static final String DELETE_CONSULTATION = "DELETE FROM Consultation WHERE consultationID=?";

    // Static block to load the JDBC driver and establish a connection
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert a new consultation
    public static void insertConsultation(String status, Date date, String time, int drID, int patientID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONSULTATION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setDate(2, new java.sql.Date(date.getTime()));
            preparedStatement.setString(3, time);
            preparedStatement.setInt(4, drID);
            preparedStatement.setInt(5, patientID);



            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating consultation failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedConsultationID = generatedKeys.getInt(1);
                    System.out.println("Generated Consultation ID: " + generatedConsultationID);
                } else {
                    throw new SQLException("Creating consultation failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update consultation status
    public static void updateConsultationStatus(int consultationID, String status) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONSULTATION_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, consultationID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve consultations by Doctor ID
    public static List<Consultation> getConsultationsByDoctorID(int drID) {
        List<Consultation> consultations = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONSULTATIONS_BY_DRID)) {
            preparedStatement.setInt(1, drID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int consultationID = resultSet.getInt("ConsultationID");
                String status = resultSet.getString("Status");
                Date date = resultSet.getDate("Date");
                String time = resultSet.getString("Time");
                int patientID = resultSet.getInt("PatientID");

                Consultation consultation = new Consultation(status, date, time,drID, patientID);
                consultations.add(consultation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultations;
    }


    // Method to retrieve consultations by Patient ID
    public static ResultSet getConsultationsByPatientID(int patientID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONSULTATIONS_BY_PATIENTID)) {
            preparedStatement.setInt(1, patientID);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to delete a consultation by ID
    public static void deleteConsultation(int consultationID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONSULTATION)) {
            preparedStatement.setInt(1, consultationID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
