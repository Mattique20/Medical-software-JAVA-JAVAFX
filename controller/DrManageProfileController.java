package com.example.temp;

import com.example.temp.OOP.Vitalife;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class DrManageProfileController
{
    @FXML
    private Button BookingButton;
    @FXML
    private Button HistoryButton;
    @FXML
    private Button ReviewButton;
    @FXML
    private Button DashboardButton;
    @FXML
    private Button Logoutbutton;
    @FXML
    private Button UpdateButton;

    @FXML
    private TextField Name;
    @FXML
    private TextField Specialization;
    @FXML
    private TextField ContactNumber;
    @FXML
    private TextField Avaibility;
    @FXML
    private TextField HospitalID;
    @FXML
    private Label Success;
    @FXML
    private Label Error;
    int userId = GlobalState.getUserId();
    Vitalife V= new Vitalife();
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
    private void switchToBookingPage()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DrCheckBooking.fxml"));
            Parent newPage = loader.load();

            Stage currentStage = (Stage) BookingButton.getScene().getWindow();

            // Set the new scene
            currentStage.setScene(new Scene(newPage));
            currentStage.setTitle("Bookings");
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

            Stage currentStage = (Stage) HistoryButton.getScene().getWindow();

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
    private void switchToReviewPage()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DrReviews.fxml"));
            Parent newPage = loader.load();

            Stage currentStage = (Stage) ReviewButton.getScene().getWindow();

            // Set the new scene
            currentStage.setScene(new Scene(newPage));
            currentStage.setTitle("Review Page");
            currentStage.sizeToScene();
            currentStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    private void UpdateButtonAction()
    {
        String name = Name.getText();
        String specialization = Specialization.getText();
        String contactNumber = ContactNumber.getText();
        String availability = Avaibility.getText();
        String hospitalID = HospitalID.getText();
        int intValue = Integer.parseInt(hospitalID);
        boolean t=V.update_doctor_profile(name, specialization, contactNumber, availability, intValue, userId);
        if(t)
        {
            Error.setText("");
            Success.setText("Successfully Updated");
        }
        else
        {
            Error.setText("INVALID");
            Success.setText("");
        }
    }
    @FXML
    private void RegisterButtonAction()
    {
        String name = Name.getText();
        String specialization = Specialization.getText();
        String contactNumber = ContactNumber.getText();
        String availability = Avaibility.getText();
        String hospitalID = HospitalID.getText();
        int intValue = Integer.parseInt(hospitalID);
        boolean t=V.create_doctor_profile(name, specialization, contactNumber, availability, intValue,userId);
        if(t)
        {
            Error.setText("");
            Success.setText("Successfully Registered");
        }
        else
        {
            Error.setText("INVALID");
            Success.setText("");
        }
    }

}
