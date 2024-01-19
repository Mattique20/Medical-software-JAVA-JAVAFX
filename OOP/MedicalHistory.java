package com.example.temp.OOP;

import com.example.temp.DB_HANDLER.MedicalHistoryHandler;

import java.util.List;

public class MedicalHistory {
    private int patientId;
    private String diagnosis;
    private String labResults;
    private String medicalRecord;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getLabResults() {
        return labResults;
    }

    public void setLabResults(String labResults) {
        this.labResults = labResults;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public List<MedicalHistory> GetPatientMedicalHistory(int patient_id){

        MedicalHistoryHandler MH_handler = new MedicalHistoryHandler();
        List<MedicalHistory> PatientHistory = MH_handler.getPatientMedicalHistory(patient_id);

        return PatientHistory;
    }
}

