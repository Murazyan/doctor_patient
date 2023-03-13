package com.doctor_patinet.doctor_patient_project;

import com.doctor_patinet.doctor_patient_project.models.Doctor;
import com.doctor_patinet.doctor_patient_project.models.Patient;
import com.doctor_patinet.doctor_patient_project.models.Request;
import com.doctor_patinet.doctor_patient_project.models.User;
import com.doctor_patinet.doctor_patient_project.models.enums.RequestStatus;
import com.doctor_patinet.doctor_patient_project.models.enums.UserType;
import com.doctor_patinet.doctor_patient_project.util.AppUtil;
import com.doctor_patinet.doctor_patient_project.util.HibernateUtil;
import com.doctor_patinet.doctor_patient_project.util.MailSender;
import lombok.SneakyThrows;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.hibernate.Session;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        System.err.println("xxxxx");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Doctor areg = Doctor.builder()
                .name("Margarita")
                .surname("Murazyan")
                .email("areg@gmail.com")
                .password("123")
                .type(UserType.DOCTOR)
                .build();
        Integer aregId = (Integer) session.save(areg);
        areg.setId(aregId);

//        session.beginTransaction();
        Patient margarita = Patient.builder()
                .name("Margarita")
                .surname("Murazyan")
                .email("maga@gmail.com")
                .password("123")

                .requests(List.of(Request.builder()
                        .doctor( areg)
                        .message("Message 1")
                        .startDate(LocalDateTime.now())
                        .endDate(LocalDateTime.now())
                        .status(RequestStatus.WAITING)
                        .build()))
                .type(UserType.PATIENT)
                .build();




//        session.beginTransaction();
        Integer id = (Integer) session.save(margarita);
//        session.flush();
        session.close();
        HibernateUtil.getSessionFactory().close();
//        session.getTransaction().commit();
        System.out.println("**** "+id);
//        Integer petrosId = (Integer) session.save(petros);
//        System.out.println("+++" + petrosId);
//        poxos.setId(id);

//        session.save(Article.builder()
//                .content("Content 1")
//                .title("Title 1")
//                .author(poxos)
//                .build()
//        );
//        initDb();
//        Application application = new Application();
//        application.start();
//        MailSender mailSender = new MailSender();
//        mailSender.sendMessage("murazyan.margarita@gmail.com", "Account verification", "xxxxx");
    }

//    @SneakyThrows
//    public static void initDb() {
//        Properties properties = AppUtil.loadProperties();
//        FluentConfiguration configuration = Flyway.configure()
//                .configuration(properties);
//        Flyway flyway = new Flyway(configuration);
//        flyway.migrate();
//    }
}
