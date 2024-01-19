package com.example.temp;

import com.example.temp.OOP.Prescription;
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

public class QuestionController
{
     @FXML
        private Button HealthCareButton;
        @FXML
        private Button BookingButton;
        @FXML
        private Button AskButton;
        @FXML
        private Button ReviewButton;
        @FXML
        private Button DashboardButton;
        @FXML
        private TextField QueryFeild;
        @FXML
        private TableView<Prescription> tableview;
        @FXML
        private TableColumn<Prescription, String> DrugName;
        @FXML
        private TableColumn<Prescription, String> Formula;
        @FXML
        private TableColumn<Prescription, String> Timeperiod;
        @FXML
        private TableColumn<Prescription, String> Details;

        Vitalife V=new Vitalife();

    @FXML
    private void initialize() {
        // Initialize columns with their respective properties
        DrugName.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        Formula.setCellValueFactory(new PropertyValueFactory<>("formula"));
        Timeperiod.setCellValueFactory(new PropertyValueFactory<>("timePeriod"));
        Details.setCellValueFactory(new PropertyValueFactory<>("drugDetails"));
    }

        @FXML
        private void switchToLoginPage()
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientMenu.fxml"));
                Parent newPage = loader.load();

                // Get the current stage from the button's scene
                Stage currentStage = (Stage) DashboardButton.getScene().getWindow();

                // Set the new scene
                currentStage.setScene(new Scene(newPage));
                currentStage.setTitle("PatientMenu");
                currentStage.sizeToScene();
                currentStage.show();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        @FXML
        private void switchToHealthcarePage()
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HealthCare.fxml"));
                Parent newPage = loader.load();

                Stage currentStage = (Stage) HealthCareButton.getScene().getWindow();

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
        private void switchToBookingPage()
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Booking.fxml"));
                Parent newPage = loader.load();

                Stage currentStage = (Stage) BookingButton.getScene().getWindow();

                // Set the new scene
                currentStage.setScene(new Scene(newPage));
                currentStage.setTitle("Booking");
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Patient_DoctorReview.fxml"));
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
        private void AskAction()
        {
            String text= QueryFeild.getText();
            List<Prescription> prescriptions = V.ask_question(text);
            tableview.getItems().clear();
            tableview.getItems().addAll(prescriptions);
        }
}



