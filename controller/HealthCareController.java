package com.example.temp;

import com.example.temp.OOP.HealthcarePackage;
import com.example.temp.OOP.Vitalife;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HealthCareController
{
    @FXML
    private Button DashboardButton;
    @FXML
    private Button BookingButton;
    @FXML
    private Button QuestionButton;
    @FXML
    private Button ReviewButton;
    @FXML
    private Button SubscribeButton;

    @FXML
    private TableView<HealthcarePackage> tableview;

    @FXML
    private TableColumn<HealthcarePackage, Integer> packageIDprop;

    @FXML
    private TableColumn<HealthcarePackage, String> Nameprop;

    @FXML
    private TableColumn<HealthcarePackage, Integer> Costprop;

    @FXML
    private TableColumn<HealthcarePackage, String> Servicesprop;
    @FXML
    private Label errorlabel;
    @FXML
    private Label Error;

    int userId = GlobalState.getUserId();
    Vitalife V = new Vitalife();

    @FXML
    private void initialize() {
        // Initialize columns with their respective properties
        packageIDprop.setCellValueFactory(new PropertyValueFactory<>("packageID"));
        Nameprop.setCellValueFactory(new PropertyValueFactory<>("name"));
        Costprop.setCellValueFactory(new PropertyValueFactory<>("cost"));
        Servicesprop.setCellValueFactory(new PropertyValueFactory<>("services"));
        Vitalife V= new Vitalife();

        List<HealthcarePackage> healthcarePackages = V.explore_healthcare_packages();
        ObservableList<HealthcarePackage> data = FXCollections.observableArrayList();
        for (HealthcarePackage pkg : healthcarePackages)
        {
            System.out.println("PackageID: " + pkg.getPackageID());
            System.out.println("Name: " + pkg.getName());
            System.out.println("Cost: " + pkg.getCost());
            System.out.println("Services: " + pkg.getServices());
            System.out.println("---------------");
        }
        for (HealthcarePackage pkg : healthcarePackages)
        {
            HealthcarePackage dataModel = new HealthcarePackage(pkg.getPackageID(), pkg.getName(), pkg.getCost(), pkg.getServices());
            data.add(dataModel);
        }
        tableview.setItems(data);
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
    private void SubscribeButtonAction()
    {
        HealthcarePackage selectedPackage = tableview.getSelectionModel().getSelectedItem();
        if (selectedPackage != null)
        {
            int packageID = selectedPackage.getPackageID();
            String name = selectedPackage.getName();
            int cost = selectedPackage.getCost();
            String services = selectedPackage.getServices();
            String text= V.Subscribe(userId, packageID);
            System.out.println("DONE.");
            Error.setText("");
            errorlabel.setText(text);

        }
        else
        {
            // No package selected, show an error or handle accordingly
            errorlabel.setText("");
            Error.setText("Invalid No Package Selected");
            System.out.println("Please select a healthcare package before subscribing.");
        }

    }
}
