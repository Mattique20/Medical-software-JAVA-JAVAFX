package com.example.temp.OOP;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
       Review r= new Review("sks",5,12489,54989);
       Vitalife V = new Vitalife();
        List<DoctorsReview> D= V.Check_Verified_reviews();
        for (DoctorsReview review : D) {

            System.out.println("Comments: " + review.getComments());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Doctor Name: " + review.getDoctorName());
            System.out.println("Patient Name: " + review.getPatientName());
            // Add more properties as needed
            System.out.println();
        }

    }

    private static Date parseStringToDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
