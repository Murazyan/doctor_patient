package com.doctor_patinet.doctor_patient_project.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
public class Patient extends User {


    private List<Request> requests;

    public Patient(User user,
                   List<Request> requests){
        this.requests =  requests;
    }



}
