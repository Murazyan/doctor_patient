package com.doctor_patinet.doctor_patient_project.manager;


import com.doctor_patinet.doctor_patient_project.models.User;

public interface UserManager {

    boolean existByEmail(String email);

    User save(User user);

    User getByEmailAndPassword(String email,
                               String password);

    User getById(int id);

    void update(User user);
}
