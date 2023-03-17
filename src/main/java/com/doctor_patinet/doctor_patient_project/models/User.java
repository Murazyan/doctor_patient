package com.doctor_patinet.doctor_patient_project.models;

import com.doctor_patinet.doctor_patient_project.models.enums.Gender;
import com.doctor_patinet.doctor_patient_project.models.enums.UserType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "users")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
//@DiscriminatorValue(value="employee")
public class User extends BaseModel{

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "type", insertable = false, updatable = false)
    @Enumerated(value = EnumType.STRING)
    private UserType type;

    @Column(name = "verification_code")
    private String verificationCode;


    public boolean isVerified(){
        return this.verificationCode == null;
    }




}
