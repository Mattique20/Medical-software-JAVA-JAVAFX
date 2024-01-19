package com.example.temp;

import com.example.temp.OOP.DoctorsReview;
import com.example.temp.OOP.Vitalife;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DrReviewController
{
    @FXML
    private Button PatientHistory;
    @FXML
    private Button ProfileButton;
    @FXML
    private Button BookingButton;
    @FXML
    private Button DashboardButton;
    @FXML
    private Button Logoutbutton;
    @FXML
    private TableView<DoctorsReview> TableView;


    @FXML
    private TableColumn<DoctorsReview, String> Comments;

    @FXML
    private TableColumn<DoctorsReview, Double> Rating;

    @FXML
    private TableColumn<DoctorsReview, String> DRNAME;

    @FXML
    private TableColumn<DoctorsReview, String> PNAME;
    Vitalife V= new Vitalife();
    int userId = GlobalState.getUserId();

    public void initialize() {
        // Initialize columns
        Comments.setCellValueFactory(new PropertyValueFactory<>("comments"));
        Rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        DRNAME.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        PNAME.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        List<DoctorsReview> doctors = V.Check_Verified_reviews();
        TableView.getItems().clear();
        TableView.getItems().addAll(doctors);

    }



    @FXML
    private void switchToLoginPage()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent newPage = loader.load();

            Stage currentStage = (Stage) Logoutbutton.getScene().getWindow();

            // Set the new scene
            currentStage.setScene(new Scene(newPage));
            currentStage.setTitle("HealthCare");
            currentStage.sizeToScene();
            currentStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToDashboard()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorMenu.fxml"));
            Parent newPage = loader.load();

            Stage currentStage = (Stage) DashboardButton.getScene().getWindow();

            // Set the new scene
            currentStage.setScene(new Scene(newPage));
            currentStage.setTitle("Doctor Menu");
            currentStage.sizeToScene();
            currentStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToHistoryPage()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DrPatientHistory.fxml"));
            Parent newPage = loader.load();

            Stage currentStage = (Stage) PatientHistory.getScene().getWindow();

            // Set the new scene
            currentStage.setScene(new Scene(newPage));
            currentStage.setTitle("Patient History");
            currentStage.sizeToScene();
            currentStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToProfilePage()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DrManageProfile.fxml"));
            Parent newPage = loader.load();

            Stage currentStage = (Stage) ProfileButton.getScene().getWindow();

            // Set the new scene
            currentStage.setScene(new Scene(newPage));
            currentStage.setTitle("Profile");
            currentStage.sizeToScene();
            currentStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToBookingPage()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DrCheckBooking.fxml"));
            Parent newPage = loader.load();

            Stage currentStage = (Stage) BookingButton.getScene().getWindow();

            // Set the new scene
            currentStage.setScene(new Scene(newPage));
            currentStage.setTitle("Booking Page");
            currentStage.sizeToScene();
            currentStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
