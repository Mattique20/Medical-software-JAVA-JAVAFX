package com.example.temp.DB_HANDLER;
import com.example.temp.OOP.HealthcarePackage;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HealthcarePackage_handler {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    public static final String SELECT_HEALTHCARE_PACKAGE_BY_NAME = "SELECT * FROM HealthcarePackage WHERE name=?";
    public static final String INSERT_HEALTHCARE_PACKAGE = "INSERT INTO HealthcarePackage (services, cost, name) VALUES (?, ?, ?)";
    public static final String SELECT_ALL_HEALTHCARE_PACKAGES = "SELECT * FROM HealthcarePackage";
    public static final String SELECT_HEALTHCARE_PACKAGE_BY_ID = "SELECT * FROM HealthcarePackage WHERE packageID=?";
    public static final String DELETE_HEALTHCARE_PACKAGE = "DELETE FROM HealthcarePackage WHERE packageID=?";

    // Static block to load the JDBC driver and establish a connection
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve a specific healthcare package by name
    public static ResultSet getHealthcarePackageByName(String packageName) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HEALTHCARE_PACKAGE_BY_NAME)) {
            preparedStatement.setString(1, packageName);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to insert a new healthcare package
    public static void insertHealthcarePackage(String name, int cost, String services) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HEALTHCARE_PACKAGE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(3, name);
            preparedStatement.setInt(2, cost);
            preparedStatement.setString(1, services);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating healthcare package failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedPackageID = generatedKeys.getInt(1);
                    System.out.println("Generated Healthcare Package ID: " + generatedPackageID);
                } else {
                    throw new SQLException("Creating healthcare package failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all healthcare packages
    public static List<HealthcarePackage> getAllHealthcarePackages() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_HEALTHCARE_PACKAGES);
            List<HealthcarePackage> AllPackages = new ArrayList<>();
            while (resultSet.next()) {
                int packageID = resultSet.getInt("packageID");
                String services = resultSet.getString("services");
                int cost = resultSet.getInt("cost");
                String name = resultSet.getString("name");

                HealthcarePackage Package = new HealthcarePackage(packageID, services, cost, name);
                AllPackages.add(Package);

            }
            return AllPackages;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList(); // Return an empty list or handle the exception as needed
        }
    }

    // Method to retrieve a specific healthcare package by ID
    public static ResultSet getHealthcarePackageById(int packageID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HEALTHCARE_PACKAGE_BY_ID)) {
            preparedStatement.setInt(1, packageID);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to delete a healthcare package by ID
    public static void deleteHealthcarePackage(int packageID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_HEALTHCARE_PACKAGE)) {
            preparedStatement.setInt(1, packageID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
