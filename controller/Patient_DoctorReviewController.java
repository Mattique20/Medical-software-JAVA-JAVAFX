package com.example.temp;

import com.example.temp.OOP.Doctor;
import com.example.temp.OOP.Vitalife;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Patient_DoctorReviewController
{
    @FXML
    private Button HealthCareButton;
    @FXML
    private Button BookingButton;
    @FXML
    private Button QuestionButton;
    @FXML
    private Button DashboardButton;
    @FXML
    private TextField CommentFeild;
    @FXML
    private Slider ratingSlider;
    @FXML
    private TableView<Doctor> tableview;

    @FXML
    private TableColumn<Doctor, Integer> Specializationprop;
    @FXML
    private TableColumn<Doctor, String> DrNameprop;

    @FXML
    private TableColumn<Doctor, String> Contactprop;

    @FXML
    private TableColumn<Doctor, Integer> HospitalID;
    @FXML
    private Label Error;
    Vitalife V= new Vitalife();
    int userId = GlobalState.getUserId();
    @FXML
    private void initialize() {
        // Initialize columns with their respective properties

        DrNameprop.setCellValueFactory(new PropertyValueFactory<>("name"));
        Contactprop.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        Specializationprop.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        HospitalID.setCellValueFactory(new PropertyValueFactory<>("hospitalId"));

        List<Doctor> doctors = V.DocByUserID(userId);
        tableview.getItems().clear();
        tableview.getItems().addAll(doctors);

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
    private void switchToQuestionPage()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Question.fxml"));
            Parent newPage = loader.load();

            Stage currentStage = (Stage) QuestionButton.getScene().getWindow();

            // Set the new scene
            currentStage.setScene(new Scene(newPage));
            currentStage.setTitle("Question");
            currentStage.sizeToScene();
            currentStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    private void ReviewButtonAction()
    {
        Doctor selectedDoctor = tableview.getSelectionModel().getSelectedItem();

        if (selectedDoctor != null)
        {
            // Access the properties of the selected doctor
            int selectedDoctorID = selectedDoctor.getId();
            String selectedDoctorName = selectedDoctor.getName();

            // Get the slider value
            double sliderValue = ratingSlider.getValue();

            // Get the comment text (you need to replace "commentTextField" with the actual TextField used for comments)
            String commentText = CommentFeild.getText();

            // Print or use the selected values
            System.out.println("Selected Doctor ID: " + selectedDoctorID);
            System.out.println("Selected Doctor Name: " + selectedDoctorName);
            System.out.println("Slider Value: " + sliderValue);
            System.out.println("Comment: " + commentText);
            int rating = (int) sliderValue;
            V.rate_doctor(commentText,rating,selectedDoctorID,userId);
            Error.setText("Successfully Review Given");
        }
        else
        {
            // Handle the case where no item is selected
            System.out.println("No doctor selected");
            Error.setText("No Doctor Selected");
        }

    }
}
