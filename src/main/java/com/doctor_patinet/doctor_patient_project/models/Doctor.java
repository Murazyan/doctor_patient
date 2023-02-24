package com.doctor_patinet.doctor_patient_project.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Setter
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends User{


    private List<Interval> intervals;
    private List<Request> requests;

}
