package com.example.temp;

import com.example.temp.OOP.MedicalHistory;
import com.example.temp.OOP.Vitalife;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PatientHistoryController
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
    private TableView<MedicalHistory> TableView; // Replace YourDataModel with your actual data model class
    @FXML
    private TableColumn<MedicalHistory, Integer> PatientID;
    @FXML
    private TableColumn<MedicalHistory, String> Diagnosis;
    @FXML
    private TableColumn<MedicalHistory, String> Results;
    @FXML
    private TableColumn<MedicalHistory, String> MedicalRecord;


    @FXML
    private Button SearchButton;
    @FXML
    private TextField PatientIDFeild;

    Vitalife V= new Vitalife();
    int userId = GlobalState.getUserId();
    @FXML
    private void initialize()
    {
        // Initialize columns with their respective properties
        Diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        Results.setCellValueFactory(new PropertyValueFactory<>("labResults"));
        MedicalRecord.setCellValueFactory(new PropertyValueFactory<>("medicalRecord"));
        PatientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DrAddPrescription.fxml"));
            Parent newPage = loader.load();

            Stage currentStage = (Stage) Prescriptionbutton.getScene().getWindow();

            // Set the new scene
            currentStage.setScene(new Scene(newPage));
            currentStage.setTitle("Prescription Page");
            currentStage.sizeToScene();
            currentStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    private void SearchButtonAction()
    {
        String PID= PatientIDFeild.getText();
        int intValue = Integer.parseInt(PID);
        List<MedicalHistory> MH = V.Patient_history(intValue);;
        TableView.getItems().clear();
        TableView.getItems().addAll(MH);
    }

}
