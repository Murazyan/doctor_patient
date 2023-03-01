package com.doctor_patinet.doctor_patient_project.manager.impl;

import com.doctor_patinet.doctor_patient_project.manager.RequestManager;
import com.doctor_patinet.doctor_patient_project.models.Request;
import com.doctor_patinet.doctor_patient_project.models.User;
import com.doctor_patinet.doctor_patient_project.provider.DBConnectionProvider;

import java.util.List;

public class RequestManagerImpl implements RequestManager {
    private final DBConnectionProvider provider = DBConnectionProvider.getInstance();

    @Override
    public List<Request> dailyRequestsPerDoctor(User doctor) {
        String query = "SELECT * from requests WHERE doctor_id = ? and created_at>= ?";


    }

    @Override
    public List<Request> requestsPerPatient(User user) {
        return null;
    }
}
