package com.doctor_patinet.doctor_patient_project.manager.impl;

import com.doctor_patinet.doctor_patient_project.manager.IntervalManager;
import com.doctor_patinet.doctor_patient_project.models.Interval;
import com.doctor_patinet.doctor_patient_project.util.HibernateUtil;
import org.hibernate.Session;

public class IntervalManagerImpl implements IntervalManager {
    @Override
    public Interval save(Interval interval) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(interval.getId()==0){
            session.beginTransaction();
            Integer id = (Integer) session.save(interval);
            session.getTransaction().commit();
            interval.setId(id);
            session.close();
        }else {
            session.beginTransaction();
            session.saveOrUpdate(interval);
            session.getTransaction().commit();
        }
        return interval;
    }
}
