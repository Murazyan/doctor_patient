package com.doctor_patinet.doctor_patient_project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class BaseModel {

    protected int id;
    protected final LocalDateTime created = LocalDateTime.now();
    protected LocalDateTime updated;

}
