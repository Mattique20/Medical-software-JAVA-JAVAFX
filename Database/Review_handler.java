package com.example.temp.DB_HANDLER;

import com.example.temp.OOP.DoctorsReview;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Review_handler {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    public static final String INSERT_REVIEW = "INSERT INTO Review (comments, rating, patient_id, doctor_id) VALUES (?, ?, ?, ?)";
    public static final String SELECT_ALL_REVIEWS = "SELECT * FROM Review";
    public static final String SELECT_REVIEW_BY_ID = "SELECT * FROM Review WHERE reviewID=?";
    public static final String DELETE_REVIEW = "DELETE FROM Review WHERE reviewID=?";

    // Static block to load the JDBC driver and establish a connection
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert a new review
    public static void insertReview(String comments, int rating, int patient_id, int doctor_id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REVIEW, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, comments);
            preparedStatement.setInt(2, rating);
            preparedStatement.setInt(3, patient_id);
            preparedStatement.setInt(4, doctor_id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating review failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedReviewID = generatedKeys.getInt(1);
                    System.out.println("Generated Review ID: " + generatedReviewID);
                } else {
                    throw new SQLException("Creating review failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all reviews
    public static ResultSet getAllReviews() {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(SELECT_ALL_REVIEWS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve a specific review by ID
    public static ResultSet getReviewById(int reviewID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVIEW_BY_ID)) {
            preparedStatement.setInt(1, reviewID);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to delete a review by ID
    public static void deleteReview(int reviewID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REVIEW)) {
            preparedStatement.setInt(1, reviewID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DoctorsReview> getDoctorsReview() {
        List<DoctorsReview> reviews = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT comments, rating, doctorName, patientName FROM doctorsreview");

            while (resultSet.next()) {
                String comments = resultSet.getString("comments");
                int rating = resultSet.getInt("rating");
                String doctorName = resultSet.getString("doctorName");
                String patientName = resultSet.getString("patientName");

                // Create a Review instance
                DoctorsReview review = new DoctorsReview(comments, rating, doctorName, patientName);
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }

}
