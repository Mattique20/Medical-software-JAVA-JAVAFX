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
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class BookingController
{
    @FXML
    private Button HealthCareButton;
    @FXML
    private Button QuestionButton;
    @FXML
    private Button ReviewButton;
    @FXML
    private Button DashboardButton;
    @FXML
    private Button BookButton;
    @FXML
    private Button SearchButton;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private ChoiceBox<String> time;
    @FXML
    private ChoiceBox<String> searchparameter;
    @FXML
    private TextField parameterfeild;
    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<Doctor> tableview;

    @FXML
    private TableColumn<Doctor, Integer> DrIDprop;
    @FXML
    private TableColumn<Doctor, String> DrNameprop;

    @FXML
    private TableColumn<Doctor, String> Contactprop;

    @FXML
    private TableColumn<Doctor, String> Specializationprop;

    @FXML
    private TableColumn<Doctor, Integer> HospitalNameprop;
    @FXML
    private Label Invalid;
    @FXML
    private Label Success;
    Vitalife V= new Vitalife();
    @FXML
    private void initialize()
    {
        // Initialize columns with their respective properties
        DrIDprop.setCellValueFactory(new PropertyValueFactory<>("id"));
        DrNameprop.setCellValueFactory(new PropertyValueFactory<>("name"));
        Contactprop.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        Specializationprop.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        HospitalNameprop.setCellValueFactory(new PropertyValueFactory<>("hospitalId"));
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
    private void switchToDashboard()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientMenu.fxml"));
            Parent newPage = loader.load();

            Stage currentStage = (Stage) DashboardButton.getScene().getWindow();

            // Set the new scene
            currentStage.setScene(new Scene(newPage));
            currentStage.setTitle("Patient Menu");
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
    // implement action when book button is clicked
    @FXML
    private void BOOKACTION()
    {
        int userId = GlobalState.getUserId();
        LocalDate selectedDate = (LocalDate) datePicker.getValue();


        Date selectedDateAsDate = Date.valueOf(selectedDate);
        String selectedType = type.getValue();
        String selectedTime = time.getValue();
        Doctor selectedDoctor = tableview.getSelectionModel().getSelectedItem();

        // Check if any item is selected
        if (selectedDoctor != null)
        {
            // Access the properties of the selected doctor
            int selectedDoctorID = selectedDoctor.getId();
            String selectedDoctorName = selectedDoctor.getName();
            String selectedContactNumber = selectedDoctor.getContactNumber();
            String selectedSpecialization = selectedDoctor.getSpecialization();
            int selectedHospitalId = selectedDoctor.getHospitalId();

            // Print or use the selected values
            System.out.println("Selected Doctor: " + selectedDoctorName);
            System.out.println("Contact Number: " + selectedContactNumber);
            System.out.println("Specialization: " + selectedSpecialization);
            System.out.println("Hospital ID: " + selectedHospitalId);

            if(selectedType.equalsIgnoreCase("Appointment"))
            {
                String status=V.BookAppointment(selectedDateAsDate,selectedTime, selectedDoctorID,userId);

                if(status.equalsIgnoreCase("Appointment Successfully booked"))
                {
                    Invalid.setText("");
                    Success.setText("Appointment Successfully booked");
                }
                else
                {
                    Success.setText("");
                    Invalid.setText("Doctor Not Available");
                }
            }
            else if(selectedType.equalsIgnoreCase("Consultation"))
            {
                String status=V.BookConsultation(selectedDateAsDate,selectedTime, selectedDoctorID,userId);
                if(status.equalsIgnoreCase("Consultation Successfully booked"))
                {
                    Invalid.setText("");
                    Success.setText("Consultation Successfully booked");
                }
                else
                {
                    Success.setText("");
                    Invalid.setText("Doctor Not Available");
                }
            }


        }
        else
        {
            // Handle the case where no item is selected
            System.out.println("No doctor selected");
        }
    }

    @FXML
    private void SearchAction()
    {

        String searchParam = searchparameter.getValue(); // Get the selected search parameter from the ChoiceBox
        String argument = parameterfeild.getText(); // Get the parameter from the text field
        // Assuming you have a method to get doctors based on the search parameters in your backend
        List<Doctor> doctors = V.find_doctors(searchParam, argument);

        // Clear existing items in the TableView
        tableview.getItems().clear();

        // Populate the TableView with the retrieved doctors
        tableview.getItems().addAll(doctors);
    }
}
