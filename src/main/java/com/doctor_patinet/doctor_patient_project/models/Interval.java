package com.doctor_patinet.doctor_patient_project.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Interval extends BaseModel{

    private User doctor;
    private int doctorId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
