package com.doctor_patinet.doctor_patient_project.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "intervals")
public class Interval extends BaseModel{


    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name="doctor_id", nullable=false)
    private Doctor doctor;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

}
