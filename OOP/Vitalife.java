package com.example.temp.OOP;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Vitalife {



    public String BookAppointment(Date appointment_date, String Time, int doctorid, int patientid) { // attique // OOP.Patient
        Patient P= new Patient();
        String status = P.requestAppointment(appointment_date,Time,doctorid,patientid);
        return status;
    };

    public List<Prescription> ask_question(String query){ // attique // OOP.Patient  - Return (Prescription) based on query, searching
        Prescription P = new Prescription();
        List<Prescription> prescriptions =  P.getPrescription(query);
        return prescriptions;
    }

    public String BookConsultation(Date consulation_date, String Time, int doctorid, int patientid){ // attique // OOP.Patient
        Patient P= new Patient();
        String status = P.bookConsultation( consulation_date, Time, doctorid, patientid);
        return status;
    }

    public Boolean Add_prescription(String Drugname, String formula,String timeperiod, String Detials, String Symptoms) // attique // OOP.Patient
    {
        Prescription P = new Prescription(Drugname,formula,timeperiod,Detials,Symptoms);
        return P.AddPrescription(P);
    }

    public List<DoctorsReview> Check_Verified_reviews () // attique // OOP.Patient // show reviews of all doctors
    {
       DoctorsReview D = new DoctorsReview();
       return D.GetAllReviews();
    }
    // --------------------------------------------------------------
    public boolean create_doctor_profile (String name,String specialization,String contactNumber,String availability,int hospital_id, int userid) // saif // OOP.Doctor
    {
        Doctor d = new Doctor(name,specialization,contactNumber,availability,hospital_id,userid);
        boolean status = d.createDoctorProfile(d);
        return true;
    }
    public boolean update_doctor_profile (String name,String specialization,String contactNumber,String availability,int hospital_id, int userid) // saif // OOP.Doctor
    {
        Doctor d = new Doctor(name,specialization,contactNumber,availability,hospital_id,userid);
        boolean status = d.UpdateDoctorProfile(d);
        return true;
    }

    public List<Doctor> find_doctors (String searchParam, String Argument) // saif // OOP.Patient, hospital ya specialization
    {
         Doctor H = new Doctor();

        List<Doctor> Doctors = new ArrayList<>();

         if (Objects.equals(searchParam, "Hospital")){
              Doctors = H.FindDocByHospital(Argument);
         }
         if (Objects.equals(searchParam, "Doctor Specialization")){
              Doctors = H.FindDocBySpeciality(Argument);
         }

         return  Doctors;
    }

    public List<HealthcarePackage> explore_healthcare_packages() { // saif // OOP.Patient
        HealthcarePackageCatalogue H = new HealthcarePackageCatalogue();
        List<HealthcarePackage> Packages =  H.GetHealthcarePackages();
        return Packages;
    }

    public List<MedicalHistory> Patient_history(int patient_id){ // saif // OOP.Doctor
        MedicalHistory M = new MedicalHistory();
        return M.GetPatientMedicalHistory(patient_id);
    }

    public List<Doctor> DocByUserID(int UserID){

            List<Doctor> Doctors = new ArrayList<>();
            Doctor D = new Doctor();

            Doctors = D.FindDocByUserID(UserID);
            return Doctors;
    }

    public String  rate_doctor(String comments, int rating, int doctorid, int patientid ) { // saif // OOP.Patient, Only Insert
        Review review = new Review(comments,rating,doctorid,patientid);
        Boolean status = review.RateADoctor(review);
        if (status){

            return "Doctor Rated";
        }
        else{
            return "Doctor Not Rated";
        }
    }
    public  String Subscribe(int patientid, int packageid)
    { // Subscribe to package
        PackageSubscriptions P = new PackageSubscriptions(patientid,packageid);
        return P.Subscribe(P);
    }
    public List<Consultation> getconsultationbyDoctorID(int DrID)
    {
        Consultation C=new Consultation();
        return C.getconsultationbyDRID(DrID);
    }
    public List<Appointment> getAppointmentbyDoctorID(int DrID)
    {
        Appointment C=new Appointment();
        return C.getAppointmentbyDRID(DrID);
    }
}