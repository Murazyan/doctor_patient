package com.doctor_patinet.doctor_patient_project;

import com.doctor_patinet.doctor_patient_project.util.AppUtil;
import com.doctor_patinet.doctor_patient_project.util.MailSender;
import lombok.SneakyThrows;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        initDb();
        Application application = new Application();
        application.start();
//        MailSender mailSender = new MailSender();
//        mailSender.sendMessage("murazyan.margarita@gmail.com", "Account verification", "xxxxx");
    }

    @SneakyThrows
    public static void initDb() {
        Properties properties = AppUtil.loadProperties();
        FluentConfiguration configuration = Flyway.configure()
                .configuration(properties);
        Flyway flyway = new Flyway(configuration);
        flyway.migrate();
    }
}
