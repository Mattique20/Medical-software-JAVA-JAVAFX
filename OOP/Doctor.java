package com.example.temp.OOP;

import com.example.temp.DB_HANDLER.Doctor_handler;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private int id;
    private String name;
    private String specialization;
    private String contactNumber;
    private String availability;
    private int hospital_id;

    public Doctor( String name, String specialization, String contactNumber, String availability,int hospital) {

        this.name = name;
        this.specialization = specialization;
        this.contactNumber = contactNumber;
        this.availability = availability;
        this.hospital_id = hospital;
    }
    public Doctor(String name, String specialization, String contactNumber, String availability,int hospital,int id) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.contactNumber = contactNumber;
        this.availability = availability;
        this.hospital_id = hospital;
    }

    public Doctor(){};

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    // methods
    public List<Doctor> search_dr(String specialization) {
        // search implementation
        return null;
    }

    public boolean Check_availbility(String date, String time) {
        // check availability implementation
        return false;
    }

    public String Answer_Query(String query) {
        // answer query implementation
        return "Query answered.";
    }



    public String ProvideHealthConcern() {
        // provide health concern implementation
        return "Health concern provided.";
    }


    public List<Doctor> FindDocByUserID(int UserID){
        List<Doctor> Doctors = new ArrayList<>();
        Doctor_handler D = new Doctor_handler();

        Doctors = D.getDoctorsByAppointment(UserID);
        return Doctors;
    }
    public List<Doctor> FindDocByHospital(String hospital){
    List<Doctor> Doctors = new ArrayList<>();
        Doctor_handler D = new Doctor_handler();

        Doctors = D.getDoctorsByHospital(hospital);

    return Doctors;
    }
    public List<Doctor> FindDocBySpeciality(String specialization){

        List<Doctor> Doctors = new ArrayList<>();
        Doctor_handler D = new Doctor_handler();

        Doctors = Doctor_handler.getDoctorsBySpecialty(specialization);

        return Doctors;
    }

    public int getHospitalId() {
        return  hospital_id;
    }

    public boolean createDoctorProfile(Doctor d){
        Doctor_handler Dhandler = new Doctor_handler();
        boolean Statuss = Dhandler.insertDoctor(d.name,d.specialization,d.contactNumber,d.availability,d.hospital_id,d.id);
        return Statuss;
    }

    public boolean UpdateDoctorProfile(Doctor d){
        Doctor_handler Dhandler = new Doctor_handler();
        boolean Statuss = Dhandler.updateDoctor(d.name,d.specialization,d.contactNumber,d.availability,d.hospital_id,d.id);
        return Statuss;
    }


}