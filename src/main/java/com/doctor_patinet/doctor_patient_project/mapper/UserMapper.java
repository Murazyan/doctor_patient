package com.doctor_patinet.doctor_patient_project.mapper;

import com.doctor_patinet.doctor_patient_project.models.User;
import com.doctor_patinet.doctor_patient_project.models.enums.Gender;
import com.doctor_patinet.doctor_patient_project.models.enums.UserType;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class UserMapper implements Function<ResultSet, User> {

    @Override
    @SneakyThrows
    public User apply(ResultSet resultSet) {
        User user = new User();

        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        user.setVerificationCode(resultSet.getString("verificationCode"));
        user.setBirthDate(resultSet.getDate("birth_date"));
        user.setGender(Gender.valueOf(resultSet.getString("gender")));
        user.setType(UserType.valueOf(resultSet.getString("type")));
        try {

            user.setPassword(resultSet.getString("password"));
        }catch (SQLException e){

        }

        return user;
    }
}
