package com.example.temp.DB_HANDLER;

import com.example.temp.OOP.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Appointment_handler {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    // SQL queries for Appointment table

    public static final String INSERT_APPOINTMENT = "INSERT INTO Appointment (status, date, time, drID, patientID) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_APPOINTMENT_STATUS = "UPDATE Appointment SET status=? WHERE appointmentID=?";
    public static final String SELECT_ALL_APPOINTMENTS = "SELECT * FROM Appointment";
    public static final String SELECT_APPOINTMENT_BY_ID = "SELECT * FROM Appointment WHERE appointmentID=?";
    public static final String SELECT_APPOINTMENT_BY_DRID = "SELECT * FROM Appointment WHERE drID=?";
    public static final String DELETE_APPOINTMENT = "DELETE FROM Appointment WHERE appointmentID=?";

    // Static block to load the JDBC driver and establish a connection
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert a new appointment
    public String  insertAppointment(String status, Date date, String time, int drID, int patientID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_APPOINTMENT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setDate(2, new java.sql.Date(date.getTime()));
            preparedStatement.setString(3, time);
            preparedStatement.setInt(4, drID);
            preparedStatement.setInt(5, patientID);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating appointment failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedAppointmentID = generatedKeys.getInt(1);
                    System.out.println("Generated Appointment ID: " + generatedAppointmentID);
                } else {
                    throw new SQLException("Creating appointment failed, no ID obtained.");
                }
            }
            return "Appointment booked Successfully";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Appointment NOT booked Successfully";
    }

    // Method to update appointment status
    public static void updateAppointmentStatus(int appointmentID, String status) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_APPOINTMENT_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, appointmentID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all appointments
    public static ResultSet getAllAppointments() {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(SELECT_ALL_APPOINTMENTS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve a specific appointment by ID
    public static ResultSet getAppointmentById(int appointmentID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_BY_ID)) {
            preparedStatement.setInt(1, appointmentID);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Appointment> getAppointmentsByDoctorId(int DrID) {
        List<Appointment> appointments = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_BY_DRID)) {
            preparedStatement.setInt(1, DrID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Date date = resultSet.getDate("Date");
                String time = resultSet.getString("Time");
                int patientID = resultSet.getInt("PatientID");
                // Retrieve other fields from the ResultSet

                Appointment appointment = new Appointment(date, time,DrID, patientID);
                appointments.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
    // Method to delete an appointment by ID
    public static void deleteAppointment(int appointmentID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_APPOINTMENT)) {
            preparedStatement.setInt(1, appointmentID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
