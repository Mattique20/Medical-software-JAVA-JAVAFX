package com.example.temp.DB_HANDLER;

import com.example.temp.OOP.Prescription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Prescription_handler {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    public static final String INSERT_PRESCRIPTION = "INSERT INTO Prescription (drugName, formula, timePeriod, drugDetails,symptoms) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_PRESCRIPTIONS = "SELECT * FROM Prescription";
    public static final String SELECT_PRESCRIPTION_BY_ID = "SELECT * FROM Prescription WHERE prescriptionID=?";
    public static final String DELETE_PRESCRIPTION = "DELETE FROM Prescription WHERE prescriptionID=?";
    public static final String SELECT_PRESCRIPTION_BY_SYMPTOM = "SELECT * FROM Prescription WHERE symptoms=?";

    // Static block to load the JDBC driver and establish a connection
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert a new prescription
    public static boolean insertPrescription(String drugName, String formula, String timePeriod, String drugDetails,String symptoms) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRESCRIPTION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, drugName);
            preparedStatement.setString(2, formula);
            preparedStatement.setString(3, timePeriod);
            preparedStatement.setString(4, drugDetails);
            preparedStatement.setString(5, symptoms);


            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating prescription failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedPrescriptionID = generatedKeys.getInt(1);
                    System.out.println("Generated Prescription ID: " + generatedPrescriptionID);
                    return true;
                } else {
                    throw new SQLException("Creating prescription failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to retrieve all prescriptions
    public static ResultSet getAllPrescriptions() {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(SELECT_ALL_PRESCRIPTIONS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve a specific prescription by ID
    public static ResultSet getPrescriptionById(int prescriptionID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRESCRIPTION_BY_ID)) {
            preparedStatement.setInt(1, prescriptionID);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to delete a prescription by ID
    public static void deletePrescription(int prescriptionID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRESCRIPTION)) {
            preparedStatement.setInt(1, prescriptionID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Prescription> getPrescriptionBySymptom(String symptom) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM prescription WHERE symptoms = ?")) {
            preparedStatement.setString(1, symptom);
            List<Prescription> prescriptions = new ArrayList<>();

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Prescription prescription = new Prescription();

                    prescription.setDrugName(resultSet.getString("drugName"));
                    prescription.setFormula(resultSet.getString("formula"));
                    prescription.setTimePeriod(resultSet.getString("timePeriod"));
                    prescription.setDrugDetails(resultSet.getString("drugDetails"));
                    prescription.setSymptoms(resultSet.getString("symptoms"));

                    prescriptions.add(prescription);
                }
                return prescriptions;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
