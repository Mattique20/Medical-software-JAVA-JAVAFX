package com.example.temp.OOP;

import java.util.ArrayList;
import java.util.List;


public class HealthcarePackage {
    private int packageID;
    private String services;
    private int cost;
    private String name;

    public HealthcarePackage() {

    }


    public HealthcarePackage(int pckageID, String services, int cost, String name) {
        this.packageID = pckageID;
        this.services = services;
        this.cost = cost;
        this.name = name;
    }

    // Getters
    public int getPackageID() {
        return packageID;
    }

    public String getServices() {
        return services;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }


    public void setServices(String services) {
        this.services = services;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> get_Details() {
        List<String> details = new ArrayList<>();
        details.add("Package ID: " + packageID);
        details.add("Name: " + name);
        details.add("Cost: " + cost);
        details.add("Services: " + services);
        return details;
    }



}