package com.example.temp.OOP;

import com.example.temp.DB_HANDLER.Appointment_handler;

import java.util.Date;
import java.util.List;

public class Appointment {
    private int appointmentID;
    private String status;
    private Date date;
    private String time;
    ///////////////////////////
    private int drID;
    private int patientID;

    Appointment(){};
    public Appointment(Date date, String time, int drID, int patientID) {

        this.status = "pending";
        this.date = date;
        this.time = time;
        this.drID = drID;
        this.patientID = patientID;

    }

    // getters and setters
    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
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




    public List<Appointment> getAppointmentbyDRID(int DrID)
    {
        Appointment_handler App = new Appointment_handler();
        return App.getAppointmentsByDoctorId(DrID);
    }


}