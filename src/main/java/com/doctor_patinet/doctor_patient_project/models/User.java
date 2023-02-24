package com.doctor_patinet.doctor_patient_project.models;

import com.doctor_patinet.doctor_patient_project.models.enums.Gender;
import com.doctor_patinet.doctor_patient_project.models.enums.UserType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel{

    private String name;
    private String surname;
    private String email;
    private String password;
    private Date birthDate;
    private Gender gender;
    private UserType type;

    private String verificationCode;

    public boolean isVerified(){
        return this.verificationCode== null;
    }




}
