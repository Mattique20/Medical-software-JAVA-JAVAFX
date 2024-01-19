package com.example.temp.OOP;

import com.example.temp.DB_HANDLER.Prescription_handler;

import java.util.List;

import static com.example.temp.DB_HANDLER.Prescription_handler.getPrescriptionBySymptom;

public class Prescription {

    private String drugName;
    private String formula;
    private String timePeriod;
    private String drugDetails;
    private String symptoms;
    private int patientID;
    private int doctorID;


    public Prescription(String drugName, String formula, String timePeriod,String Drugdetails,String symptoms) {
        this.drugName = drugName;
        this.formula = formula;
        this.timePeriod = timePeriod;
        this.drugDetails = Drugdetails;
        this.symptoms = symptoms;
    }


    public Prescription() {

    }


    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getDrugDetails() {
        return drugDetails;
    }

    public void setDrugDetails(String drugDetails) {
        this.drugDetails = drugDetails;
    }

    public void setPatientID(int id){
        this.patientID=id;
    }
    public void setDoctorID(int id){
        this.doctorID = id;
    }
    public void setSymptoms(String arg){
        this.symptoms = arg;
    }

    public List<Prescription>  getPrescription(String query) {
        Prescription_handler P = new Prescription_handler();

        // call db handler
        List<Prescription> prescriptions = getPrescriptionBySymptom(query);

        return  prescriptions;
    }

    public boolean AddPrescription(Prescription P){
        Prescription_handler Phandler = new Prescription_handler();
        boolean status = Prescription_handler.insertPrescription(P.getDrugName(),P.getFormula(),P.getTimePeriod(),P.getDrugDetails(),P.symptoms);

        return status;
    }
}