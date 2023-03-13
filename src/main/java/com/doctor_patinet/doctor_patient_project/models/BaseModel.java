package com.doctor_patinet.doctor_patient_project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    protected int id;

//    @CreationTimestamp
    @Column
    protected  LocalDateTime created ;

    @Column
    protected LocalDateTime updated;

    @PrePersist
    public void onCreate(){
        this.created = LocalDateTime.now();
        this.updated = created;
    }

    @PreUpdate
    public void onUpdate(){
        this.updated = LocalDateTime.now();
    }

}
