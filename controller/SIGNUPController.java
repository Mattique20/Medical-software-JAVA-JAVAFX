package com.example.temp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class SIGNUPController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private ChoiceBox<String> option;
    @FXML
    Label errorMessageLabel;
    @FXML
    Label registered;

    String role;


    @FXML
    private void onSignupButtonClick() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        role = option.getValue();

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Confirm Password: " + confirmPassword);
        System.out.println("Selected Option: " + role);
        // Check if the password and confirm password match
        if (password.equals(confirmPassword))
        {
            // SQL query to insert user details into the 'user' table
            String insertQuery = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "123456");
                 PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, role);

                // Execute the insert query
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Retrieve the generated keys (including the auto-incremented ID)
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next())
                        {
                            int generatedUserId = generatedKeys.getInt(1);
                            GlobalState.setUserId(generatedUserId);
                            System.out.println("User registered successfully! User ID: " + generatedUserId);
                            registered.setText("User registered successfully! User ID: " + generatedUserId);
                        }
                        else
                        {
                            System.out.println("User registration failed. Could not obtain the user ID.");
                            errorMessageLabel.setText("User registration failed. Please try again.");
                        }
                    }
                } else {
                    System.out.println("User registration failed. Please try again.");
                    errorMessageLabel.setText("User registration failed. Please try again.");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                errorMessageLabel.setText("User registration failed. Please try again.");
            }
        }


    }
    @FXML
    private void switchToLogin()
    {
        String text= option.getValue();
            if(text.equals("Patient")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                    Parent signupView = loader.load();
                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    stage.setScene(new Scene(signupView));
                    stage.setTitle("Login");
                    stage.sizeToScene();
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DrManageProfile.fxml"));
                Parent signupView = loader.load();
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(signupView));
                stage.setTitle("register");
                stage.sizeToScene();
                stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

