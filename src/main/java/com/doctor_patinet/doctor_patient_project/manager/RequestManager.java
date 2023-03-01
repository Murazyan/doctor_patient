package com.doctor_patinet.doctor_patient_project.manager;

import com.doctor_patinet.doctor_patient_project.models.Request;
import com.doctor_patinet.doctor_patient_project.models.User;

import java.util.List;

public interface RequestManager {

    public List<Request> dailyRequestsPerDoctor(User doctor);

    public List<Request> requestsPerPatient(User user);
}
