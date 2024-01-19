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
public class AddPrescriptionController
{
    @FXML
    private Button BookingButton;
    @FXML
    private Button ProfileButton;
    @FXML
    private Button ReviewButton;
    @FXML
    private Button DashboardButton;
    @FXML
    private Button Logoutbutton;
    @FXML
    private Button Prescriptionbutton;
    @FXML
    private TextField DrugName;
    @FXML
    private TextField Formula;
    @FXML
    private TextField TimePeriod;
    @FXML
    private TextField Details;
    @FXML
    private TextField Symptoms;
    @FXML
    private Label Success;
    @FXML
    private Label Error;

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
    private void PrescriptionButtonAction()
    {
        String Dname=DrugName.getText();
        String formula=Formula.getText() ;
        String Time=TimePeriod.getText() ;
        String details= Details.getText() ;
        String symp= Symptoms.getText() ;
        Boolean t = V.Add_prescription(Dname,formula,Time, details, symp);
        if(t)
        {
            Error.setText("");
            Success.setText("Added Successfully");
        }
        else
        {
            Error.setText("Failed");
            Success.setText("");
        }
    }
}
