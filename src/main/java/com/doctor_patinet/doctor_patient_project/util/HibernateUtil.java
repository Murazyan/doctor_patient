package com.doctor_patinet.doctor_patient_project.util;

import com.doctor_patinet.doctor_patient_project.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Doctor.class)
                    .addAnnotatedClass(Interval.class)
                    .addAnnotatedClass(Patient.class)
                    .addAnnotatedClass(Request.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("build SessionFactory failed :" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close() {
        // Close all cached and active connection pools
        getSessionFactory().close();
    }

}