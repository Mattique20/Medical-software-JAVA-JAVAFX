package com.example.temp.DB_HANDLER;

import com.example.temp.OOP.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Doctor_handler {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    // SQL queries for Doctor table
// ADD HOSPITAL ID
    public static final String INSERT_DOCTOR = "INSERT INTO Doctor (name, specialization, contactNumber, availability, hospital_id, id) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_DOCTOR = "UPDATE Doctor SET name=?, specialization=?, contactNumber=?, availability=?, Hospital_id=? WHERE id=?";
    public static final String SELECT_ALL_DOCTORS = "SELECT * FROM Doctor";
    public static final String SELECT_DOCTOR_BY_ID = "SELECT * FROM Doctor WHERE id=?";
    public static final String DELETE_DOCTOR = "DELETE FROM Doctor WHERE id=?";

    // Static block to load the JDBC driver and establish a connection
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert a new doctor
    public static boolean insertDoctor(String name, String specialization, String contactNumber, String availability, int hospital_id, int drID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DOCTOR)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, specialization);
            preparedStatement.setString(3, contactNumber);
            preparedStatement.setString(4, availability);
            preparedStatement.setInt(5, hospital_id);
            preparedStatement.setInt(6, drID);

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to update doctor details
    public static boolean updateDoctor(String name, String specialization, String contactNumber, String availability, int hospital_id, int drID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DOCTOR))
        {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, specialization);
            preparedStatement.setString(3, contactNumber);
            preparedStatement.setString(4, availability);
            preparedStatement.setInt(5, hospital_id);
            preparedStatement.setInt(6, drID);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve all doctors
    public static ResultSet getAllDoctors() {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(SELECT_ALL_DOCTORS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to retrieve a specific doctor by ID
    public static ResultSet getDoctorById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTOR_BY_ID)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to delete a doctor by ID
    public static void deleteDoctor(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DOCTOR)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Doctor> getDoctorsBySpecialty(String specialty) {
        List<Doctor> doctors = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Doctor WHERE specialization = ?")) {
            preparedStatement.setString(1, specialty);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id= resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String specialization = resultSet.getString("specialization");
                    String  contactNumber = resultSet.getString("contactNumber");
                    String availability = resultSet.getString("availability");
                    int hospital_id = resultSet.getInt("hospital_id");



                    // Create a Doctor instance
                    Doctor doctor = new Doctor( name, specialization, contactNumber,availability,hospital_id,id); // Adjust fields as needed
                    doctors.add(doctor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    public List<Doctor> getDoctorsByHospital(String hospitalName) {
        List<Doctor> doctors = new ArrayList<>();

        try {
            // Step 1: Find the hospital ID based on the hospital name
            int hospitalId = getHospitalIdByName(hospitalName);

            // Step 2: Query doctors by hospital ID
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doctor WHERE hospital_id = ?")) {
                preparedStatement.setInt(1, hospitalId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String specialization = resultSet.getString("specialization");
                        String contactNumber = resultSet.getString("contactNumber");
                        String availability = resultSet.getString("availability");
                        int doctorHospitalId = resultSet.getInt("hospital_id");

                        // Create a Doctor instance
                        Doctor doctor = new Doctor( name, specialization, contactNumber, availability, doctorHospitalId,id);
                        doctors.add(doctor);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    // Helper method to get hospital ID by name
    private int getHospitalIdByName(String hospitalName) throws SQLException {
        int hospitalId = -1;

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM hospital WHERE name = ?")) {
            preparedStatement.setString(1, hospitalName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    hospitalId = resultSet.getInt("id");
                }
            }
        }

        return hospitalId;
    }

    // Function to check if the provided availability matches the doctor's availability
    public boolean checkAvailability(int doctorId, String providedAvailability) {
        // JDBC code to query the database and check availability

            String query = "SELECT availability FROM Doctor WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, doctorId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next())
                    {
                        String doctorAvailability = resultSet.getString("availability");
                        return providedAvailability.equals(doctorAvailability);
                    }
                }
            }
         catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Doctor> getDoctorsByAppointment(int UserId) {
        List<Doctor> doctors = new ArrayList<>();

        try {
            // Step 2: Query doctors by hospital ID
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT Distinct d.* " +
                    "FROM Doctor d " +
                    "JOIN Appointment a ON d.id = a.drID " +
                    "JOIN patient p ON a.PatientID = p.id " +
                    "WHERE p.id = ? ;")) {
                preparedStatement.setInt(1, UserId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String specialization = resultSet.getString("specialization");
                        String contactNumber = resultSet.getString("contactNumber");
                        String availability = resultSet.getString("availability");
                        int doctorHospitalId = resultSet.getInt("hospital_id");

                        // Create a Doctor instance
                        Doctor doctor = new Doctor(name, specialization, contactNumber, availability, doctorHospitalId,id);
                        doctors.add(doctor);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

}
