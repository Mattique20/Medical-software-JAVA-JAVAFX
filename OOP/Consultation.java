package com.example.temp.OOP;

import com.example.temp.DB_HANDLER.Consultation_handler;

import java.util.Date;
import java.util.List;

public class Consultation {
    private int consultationID;
    private String status;
    private Date date;
    private String time;
    private int drID;
    private int patientID;
    private String notes;

    public Consultation(){}
    public Consultation(String status, Date date, String time, int drID, int patientID) {
        this.status = status;
        this.date = date;
        this.time = time;
        this.drID = drID;
        this.patientID = patientID;

    }

    // getters and setters
    public int getConsultationID() {
        return consultationID;
    }

    public void setConsultationID(int consultationID) {
        this.consultationID = consultationID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDrID() {
        return drID;
    }

    public void setDrID(int drID) {
        this.drID = drID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // methods

    public List<Consultation> getconsultationbyDRID(int DrID)
    {
        Consultation_handler Chandler = new Consultation_handler();
        return Chandler.getConsultationsByDoctorID(DrID);
    }



}