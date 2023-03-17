package com.doctor_patinet.doctor_patient_project.manager;

import com.doctor_patinet.doctor_patient_project.models.Request;
import com.doctor_patinet.doctor_patient_project.models.User;
import com.doctor_patinet.doctor_patient_project.models.enums.RequestStatus;
import lombok.SneakyThrows;

import java.util.List;

public interface RequestManager {

     List<Request> dailyRequestsPerDoctor(User doctor);

     @SneakyThrows
     List<Request> dailyRequestsPerDoctorByStatus(User doctor,
                                                  RequestStatus status);

     List<Request> requestsPerPatient(User user);
}
