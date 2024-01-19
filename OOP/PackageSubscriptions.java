package com.example.temp.OOP;

import  com.example.temp.DB_HANDLER.PackageSubscriptionHandler;

public class PackageSubscriptions {
    private int patient_id;
    private int healthcare_package_id;

    public PackageSubscriptions(int patientid, int packageid) {
        this.patient_id = patientid;
        this.healthcare_package_id = packageid;
    }

    public int getPatientId() {
        return patient_id;
    }

    public void setPatientId(int patientId) {
        this.patient_id = patientId;
    }

    public int getHealthcarePackageId() {
        return healthcare_package_id;
    }

    public void setHealthcarePackageId(int healthcarePackageId) {
        this.healthcare_package_id = healthcarePackageId;
    }

    public String Subscribe(PackageSubscriptions P){
        PackageSubscriptionHandler PSHandler = new PackageSubscriptionHandler();
        boolean status = PSHandler.addSubscription(P);
        if (status){
            System.out.println("Subscription added: " + status);
            return "Package Subscribed Successfully";
        }
        else {
            System.out.println("Subscription NOT added: " + status);
            return  "Package NOT Subscribed ";
        }

    }
}
