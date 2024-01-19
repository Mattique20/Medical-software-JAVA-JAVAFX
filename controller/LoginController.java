package com.example.temp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;


public class LoginController {

    public Text loginMessage;
    String role;
    @FXML
    public ImageView logoimage;
    @FXML
    private TextField usernameField;
    @FXML
    private StackPane rootPane;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    private boolean checkCredentialsInDatabase(String username, String password)
    {
        // JDBC URL, username, and password of MySQL server
        String jdbcUrl = "jdbc:mysql://localhost:3306/project";
        String dbUsername = "root";
        String dbPassword = "123456";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
            // SQL query to check credentials and retrieve the role
            String query = "SELECT role,userid FROM user WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // If the query returns any rows, the credentials are valid
                    if (resultSet.next())
                    {
                        if(username.equals("") && password.equals(""))
                        {
                            return false;
                        }
                        else
                        {
                            role = resultSet.getString("role");
                            int userId = resultSet.getInt("userid");
                            GlobalState.setUserId(userId);
                            System.out.println("User role: " + role);
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @FXML
    private void switchToView() throws IOException {
        String enteredUsername = usernameField.getText();
        String enteredPassword = passwordField.getText();

        // Check credentials against the database
        boolean isValidCredentials = checkCredentialsInDatabase(enteredUsername, enteredPassword);

        if (isValidCredentials)
        {
            errorLabel.setText("");
            if(role.equalsIgnoreCase("patient"))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientMenu.fxml"));
                Parent signupView = loader.load();
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(signupView));
                stage.setTitle("Patient Menu");
                stage.sizeToScene();
                stage.show();
            }
            else if(role.equalsIgnoreCase("doctor"))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorMenu.fxml"));
                Parent signupView = loader.load();
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(signupView));
                stage.setTitle("Doctor Menu");
                stage.sizeToScene();
                stage.show();
            }
        }
        else
        {
            errorLabel.setText("Invalid credentials. Please try again.");
        }
    }
    @FXML
    private void handleShowSignup()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SIGNUP.fxml"));
            Parent signupView = loader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(signupView));
            stage.setTitle("Sign Up");
            stage.sizeToScene();
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
