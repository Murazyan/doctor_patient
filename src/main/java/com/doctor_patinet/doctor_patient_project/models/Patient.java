package com.doctor_patinet.doctor_patient_project.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User {


    @OneToMany(mappedBy = "patient")
    private List<Request> requests;

    public Patient(User user,
                   List<Request> requests){
        this.requests =  requests;
    }



}
