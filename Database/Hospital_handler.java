package com.example.temp.DB_HANDLER;

import java.sql.*;

public class
Hospital_handler {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;


    public static final String INSERT_HOSPITAL = "INSERT INTO Hospital (name, location) VALUES (?, ?)";
    public static final String SELECT_ALL_HOSPITALS = "SELECT * FROM Hospital";
    public static final String SELECT_HOSPITAL_BY_ID = "SELECT * FROM Hospital WHERE id=?";
    public static final String DELETE_HOSPITAL = "DELETE FROM Hospital WHERE id=?";

    // Static block to load the JDBC driver and establish a connection
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert a new hospital
    public static void insertHospital(String name, String location) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HOSPITAL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, location);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating hospital failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedHospitalID = generatedKeys.getInt(1);
                    System.out.println("Generated Hospital ID: " + generatedHospitalID);
                } else {
                    throw new SQLException("Creating hospital failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all hospitals
    public static ResultSet getAllHospitals() {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(SELECT_ALL_HOSPITALS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve a specific hospital by ID
    public static ResultSet getHospitalById(int hospitalID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOSPITAL_BY_ID)) {
            preparedStatement.setInt(1, hospitalID);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to delete a hospital by ID
    public static void deleteHospital(int hospitalID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_HOSPITAL)) {
            preparedStatement.setInt(1, hospitalID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
