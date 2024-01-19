package com.example.temp.DB_HANDLER;

import java.sql.*;

public class Payment_handler {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    public static final String INSERT_PAYMENT = "INSERT INTO Payment (comments, amount, isPaid, appointmentID, consultationID) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_PAYMENTS = "SELECT * FROM Payment";
    public static final String SELECT_PAYMENT_BY_ID = "SELECT * FROM Payment WHERE transactionID=?";
    public static final String DELETE_PAYMENT = "DELETE FROM Payment WHERE transactionID=?";

    // Static block to load the JDBC driver and establish a connection
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert a new payment
    public static void insertPayment(String comments, int amount, boolean isPaid, int appointmentID, int consultationID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, comments);
            preparedStatement.setInt(2, amount);
            preparedStatement.setBoolean(3, isPaid);
            preparedStatement.setInt(4, appointmentID);
            preparedStatement.setInt(5, consultationID);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating payment failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedTransactionID = generatedKeys.getInt(1);
                    System.out.println("Generated Transaction ID: " + generatedTransactionID);
                } else {
                    throw new SQLException("Creating payment failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all payments
    public static ResultSet getAllPayments() {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(SELECT_ALL_PAYMENTS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve a specific payment by ID
    public static ResultSet getPaymentById(int transactionID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENT_BY_ID)) {
            preparedStatement.setInt(1, transactionID);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to delete a payment by ID
    public static void deletePayment(int transactionID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PAYMENT)) {
            preparedStatement.setInt(1, transactionID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
