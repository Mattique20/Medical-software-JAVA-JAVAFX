package com.example.temp.DB_HANDLER;

import java.sql.*;

public class User_handler {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    // Additional variable to store user role
    private static String role;

    // SQL queries for User table

    public static final String INSERT_USER = "INSERT INTO User (username, password, name) VALUES (?, ?, ?)";
    public static final String UPDATE_USER = "UPDATE User SET username=?, password=?, name=? WHERE userID=?";
    public static final String SELECT_ALL_USERS = "SELECT * FROM User";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM User WHERE userID=?";
    public static final String SELECT_USER_BY_USERNAME = "SELECT * FROM User WHERE username=?";
    public static final String DELETE_USER = "DELETE FROM User WHERE userID=?";

    // Static block to load the JDBC driver and establish a connection
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    // Method to insert a new user
    public static void insertUser(String username, String password, String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedUserID = generatedKeys.getInt(1);
                    System.out.println("Generated User ID: " + generatedUserID);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update user details
    public static void updateUser(int userID, String username, String password, String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setInt(4, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all users
    public static ResultSet getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(SELECT_ALL_USERS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve a specific user by ID
    public static ResultSet getUserById(int userID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, userID);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve a specific user by username
    public static ResultSet getUserByUsername(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to delete a user by ID
    public static void deleteUser(int userID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to check credentials and retrieve the role
    public static boolean checkCredentialsInDatabase(String username, String password) {
        // SQL query to check credentials and retrieve the role
        String query = "SELECT role FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // If the query returns any rows, the credentials are valid
                if (resultSet.next()) {
                    if (username.equals("") && password.equals("")) {
                        return false;
                    } else {
                        role = resultSet.getString("role");
                        System.out.println("User role: " + role);
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
