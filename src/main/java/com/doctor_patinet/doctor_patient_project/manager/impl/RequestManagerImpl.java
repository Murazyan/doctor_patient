package com.doctor_patinet.doctor_patient_project.manager.impl;

import com.doctor_patinet.doctor_patient_project.manager.RequestManager;
import com.doctor_patinet.doctor_patient_project.models.Request;
import com.doctor_patinet.doctor_patient_project.models.User;
import com.doctor_patinet.doctor_patient_project.provider.DBConnectionProvider;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestManagerImpl implements RequestManager {
    private final DBConnectionProvider provider = DBConnectionProvider.getInstance();

    @Override
    @SneakyThrows
    public List<Request> dailyRequestsPerDoctor(User doctor) {
        List<Request> requests =  new ArrayList<>();
        String query = "SELECT * from requests r " +
                "INNER JOIN users u ON u.id = r.`patient_id` WHERE r.doctor_id = ? and r.start_date >= ?";
        PreparedStatement statement = provider.getConnection().prepareStatement(query);
        statement.setInt(1, doctor.getId());
        statement.setDate(2, new java.sql.Date(new Date().getTime()));
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
 //todo
        }
        return requests;
    }

    @Override
    public List<Request> requestsPerPatient(User user) {
        return null;
    }
}
