package com.doctor_patinet.doctor_patient_project.models;

import com.doctor_patinet.doctor_patient_project.models.enums.RequestStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Request extends BaseModel{

    private User patient;
    private int patientId;

    private User doctor;
    private int doctorId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String message;

    private RequestStatus status;




}
