package com.example.temp.OOP;

import com.example.temp.DB_HANDLER.Appointment_handler;
import com.example.temp.DB_HANDLER.Consultation_handler;
import com.example.temp.DB_HANDLER.Doctor_handler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {
    private int id;
    private String name;
    private String bloodType;
    private String DOB;
    private Consultation C;

    private Appointment A;

    public Patient(){}

    public Patient(int id, String name, String bloodType, String DOB)
    {
        this.id = id;
        this.name = name;
        this.bloodType = bloodType;
        this.DOB = DOB;
    }

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

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String  bloodType) {
        this.bloodType = bloodType;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }


    public String requestAppointment(Date appointment_date,String Time,int doctorid,int patientid)
    {
        A = new Appointment(appointment_date,Time,doctorid,patientid);
        Doctor_handler Dhandler = new Doctor_handler();
        Appointment_handler Ahandler = new Appointment_handler();
        String Status="";
        Date mydate = A.getDate();
        // Format the date to get the day of the week
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String dayOfWeek = sdf.format(mydate);

        // Print or use the day of the week
        System.out.println("Day of the week: " + dayOfWeek);

        // search for dayofweek in doctor availability column
        boolean check = Dhandler.checkAvailability(A.getDrID(),dayOfWeek);

        String status;
        if (check == true){ // doctor available
            // insert new record

            Ahandler.insertAppointment("Pending",A.getDate(),A.getTime(),A.getDrID(),A.getPatientID());

            status = "Appointment Successfully booked";
            System.out.println(status);
        }
        else{
            status = "Doctor Not Available";
            System.out.println(status);
        }


        return status;
    }
    public String bookConsultation(Date consulation_date, String Time, int doctorid, int patientid)
    {
        C = new Consultation("Pending",consulation_date,Time,doctorid,patientid);
        Doctor_handler Dhandler = new Doctor_handler();
        Consultation_handler Chandler = new Consultation_handler();
        String Status="";
        Date mydate = C.getDate();
        // Format the date to get the day of the week
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String dayOfWeek = sdf.format(mydate);

        // Print or use the day of the week
        System.out.println("Day of the week: " + dayOfWeek);

        // search for dayofweek in doctor availability column
        boolean check = Dhandler.checkAvailability(C.getDrID(),dayOfWeek);

        String status;
        if (check){ // doctor available
            // insert new record

            Chandler.insertConsultation("Pending",C.getDate(),C.getTime(),C.getDrID(),C.getPatientID());

            status = "Consultation Successfully booked";
            System.out.println(status);
        }
        else{
            status = "Doctor Not Available";
            System.out.println(status);
        }


        return status;
    }

}