package com.example.temp.OOP;

import com.example.temp.DB_HANDLER.Review_handler;

public class Review {
    private int reviewID; // set to auto increment in db
    private String comments;
    private int rating;
    private int patient_id;
    private int doctor_id;


    public Review( String comments, int rating, int patient_id, int doctor_id) {

        this.comments = comments;
        this.rating = rating;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
    }

    // getters and setters
    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    Review() {

    }

    public boolean RateADoctor(Review R){
        Review_handler Rhandler = new Review_handler();
        Review_handler.insertReview(R.comments,R.rating,R.patient_id,R.doctor_id);
        return true;
    }
}