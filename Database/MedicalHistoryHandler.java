package com.example.temp.DB_HANDLER;

import com.example.temp.OOP.MedicalHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalHistoryHandler {
    private static final String SELECT_MEDICAL_HISTORY_BY_PATIENT_ID = "SELECT * FROM MedicalHistory WHERE patient_id = ?";

    // Add other database connection-related code here

    public List<MedicalHistory> getPatientMedicalHistory(int patientId) {
        List<MedicalHistory> patientHistory = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "123456");
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICAL_HISTORY_BY_PATIENT_ID)) {

            preparedStatement.setInt(1, patientId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MedicalHistory medicalHistory = new MedicalHistory();

                    medicalHistory.setPatientId(resultSet.getInt("patient_id"));
                    medicalHistory.setDiagnosis(resultSet.getString("diagnosis"));
                    medicalHistory.setLabResults(resultSet.getString("lab_results"));
                    medicalHistory.setMedicalRecord(resultSet.getString("medical_record"));

                    patientHistory.add(medicalHistory);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patientHistory;
    }
}
