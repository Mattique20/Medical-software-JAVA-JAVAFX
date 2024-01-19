package com.example.temp.OOP;

import com.example.temp.DB_HANDLER.HealthcarePackage_handler;

import java.util.List;

public class HealthcarePackageCatalogue {

    public List<HealthcarePackage> GetHealthcarePackages() {

        HealthcarePackage_handler HPhandler = new HealthcarePackage_handler();

        List<HealthcarePackage> healthcarePackages = HPhandler.getAllHealthcarePackages();


        return healthcarePackages;
    }
}
