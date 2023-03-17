package com.doctor_patinet.doctor_patient_project.models.enums;

public enum RequestStatus {

    WAITING, ACCEPTED, CANCELED, COMPLETE;


    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
