package com.example.temp.OOP;

public class Payment {
    private int transactionID;
    private String comments;
    private int amount;
    private boolean isPaid;
    private int appointmentID;
    private int consultationID;

    public Payment(int transactionID, String comments, int amount) {
        this.transactionID = transactionID;
        this.comments = comments;
        this.amount = amount;
        this.isPaid = false;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public String getComments() {
        return comments;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public boolean getPayment(int amount) {
        if (this.amount >= amount) {
            this.isPaid = true;
            return true;
        }
        return false;
    }

    public void refund() {
        this.isPaid = false;
    }
}