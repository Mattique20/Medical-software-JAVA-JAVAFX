package com.example.temp.DB_HANDLER;

import com.example.temp.OOP.PackageSubscriptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PackageSubscriptionHandler {

    private static final String INSERT_SUBSCRIPTION = "INSERT INTO package_subscriptions (patient_id, healthcare_package_id) VALUES (?, ?)";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    private Connection connection;

    public PackageSubscriptionHandler() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addSubscription(PackageSubscriptions subscription) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBSCRIPTION)) {
            preparedStatement.setInt(1, subscription.getPatientId());
            preparedStatement.setInt(2, subscription.getHealthcarePackageId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
