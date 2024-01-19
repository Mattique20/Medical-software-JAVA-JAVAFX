package com.example.temp;

import com.example.temp.OOP.Consultation;
import com.example.temp.OOP.Appointment;

import com.example.temp.OOP.Vitalife;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class DrCheckBookingController
{

    @FXML
    private Button PatientHistory;
    @FXML
    private Button ProfileButton;
    @FXML
    private Button ReviewButton;
    @FXML
    private Button DashboardButton;
    @FXML
    private Button Logoutbutton;
    @FXML
    private Button SearchButton;
    @FXML
    private ChoiceBox<String> Type;

    @FXML
    private TableView<Consultation> TableView; // Replace YourDataModel with your actual data model class

    @FXML
    private TableColumn<Consultation, String> Status;
    @FXML
    private TableColumn<Consultation, Date> Date;
    @FXML
    private TableColumn<Consultation, String> Time;
    @FXML
    private TableColumn<Consultation, Integer> PatientID;

    @FXML
    private TableView<Appointment> AppointmentTable; // Replace YourDataModel with your actual data model class
    @FXML
    private TableColumn<Consultation, Date> Date_AP;
    @FXML
    private TableColumn<Consultation, String> Time_AP;
    @FXML
    private TableColumn<Consultation, Integer> PatientID_AP;
    Vitalife V= new Vitalife();
    int userId = GlobalState.getUserId();
    @FXML
    private void initialize() {
        initializeConsultation();
        initializeAppointmentTable();
    }
    @FXML
    private void initializeConsultation()
    {
        // Initialize columns with their respective properties
        Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Time.setCellValueFactory(new PropertyValueFactory<>("time"));
        PatientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
    }
    private void initializeAppointmentTable() {
        Date_AP.setCellValueFactory(new PropertyValueFactory<>("date"));
        Time_AP.setCellValueFactory(new PropertyValueFactory<>("time"));
        PatientID_AP.setCellValueFactory(new PropertyValueFactory<>("patientID"));

        // You might want to customize the appearance of the Date column if needed...
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
    private void SearchButtonAction()
    {
        String text = Type.getValue();
        if (text.equals("Consultation"))
        {
            AppointmentTable.setVisible(false);
            TableView.setVisible(true);
            List<Consultation> Consultation = V.getconsultationbyDoctorID(userId);
            TableView.getItems().clear();
            TableView.getItems().addAll(Consultation);
        }
        else
        {
            AppointmentTable.setVisible(true);
            TableView.setVisible(false);
            List<Appointment> Appointment = V.getAppointmentbyDoctorID(userId);
            AppointmentTable.getItems().clear();
            AppointmentTable.getItems().addAll(Appointment);
        }
    }

}
