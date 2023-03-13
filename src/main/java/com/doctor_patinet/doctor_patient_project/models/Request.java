package com.doctor_patinet.doctor_patient_project.models;

import com.doctor_patinet.doctor_patient_project.models.enums.RequestStatus;
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
@Table(name = "requests")
public class Request extends BaseModel{

    @ManyToOne()
    @JoinColumn(name="patient_id", nullable=false)
    private Patient patient;

    @ManyToOne()
    @JoinColumn(name="doctor_id", nullable=false)
    private Doctor doctor;

    @Column(name = "start_date", columnDefinition = "timestamp")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "message")
    private String message;

    @Column
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

}
