package com.example.temp.OOP;

import com.example.temp.DB_HANDLER.Review_handler;

import java.util.List;

public class DoctorsReview {
    private String DoctorsReviewID;
    private String comments;
    private int rating;
    private String doctorName;
    private String patientName;

    public DoctorsReview(String commnts,int rating,String docname,String patName){
        this.comments = commnts;
        this.rating = rating;
        this.doctorName = docname;
        this.patientName = patName;
    }
    public DoctorsReview(){};

    // Getter and setter for 'comments'
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    // Getter and setter for 'rating'
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // Getter and setter for 'doctorName'
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    // Getter and setter for 'patientName'
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public List<DoctorsReview> GetAllReviews(){
    Review_handler Rhandler = new Review_handler();
    List<DoctorsReview> alldocsreview = Rhandler.getDoctorsReview();
    return alldocsreview;
    }
}
