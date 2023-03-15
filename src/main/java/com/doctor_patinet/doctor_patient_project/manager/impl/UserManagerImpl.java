package com.doctor_patinet.doctor_patient_project.manager.impl;

import com.doctor_patinet.doctor_patient_project.manager.UserManager;
import com.doctor_patinet.doctor_patient_project.mapper.UserMapper;
import com.doctor_patinet.doctor_patient_project.models.User;
import com.doctor_patinet.doctor_patient_project.provider.DBConnectionProvider;
import com.doctor_patinet.doctor_patient_project.util.HibernateUtil;
import lombok.SneakyThrows;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.sql.*;

public class UserManagerImpl implements UserManager {

    @Override
    @SneakyThrows
    public boolean existByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<User> root = cq.from(User.class);

        cq.select(cb.count(root)).where(cb.equal(root.get("email"), email));

        Long count = session.createQuery(cq).getSingleResult();
        return count > 0;
    }

    @Override
    @SneakyThrows
    public User save(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(user.getId()!=0){
            Integer id = (Integer) session.save(user);
            user.setId(id);
            session.close();
        }else {
            session.beginTransaction();
            session.saveOrUpdate(user);
        }
        return user;
    }

    @Override
    @SneakyThrows
    public User getByEmailAndPassword(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(
                cb.and(cb.equal(root.get("email"), email),
                        cb.equal(root.get("password"), password))
        );
        return session.createQuery(cq).uniqueResult();
    }

    @Override
    @SneakyThrows
    public User getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(
                cb.and(cb.equal(root.get("id"), id))
        );
        return session.createQuery(cq).uniqueResult();
    }


    @Override
    public void verify(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<User> update = cb.createCriteriaUpdate(User.class);
        Root<User> root = update.from(User.class);
        update.set("verification_code", user.getVerificationCode());
        update.where(cb.equal(root.get("id"), user.getId()));
        session.createQuery(update).executeUpdate();
    }

}
