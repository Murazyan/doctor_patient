package com.doctor_patinet.doctor_patient_project.provider;

import com.doctor_patinet.doctor_patient_project.util.AppUtil;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnectionProvider {


    private  static  volatile DBConnectionProvider instance = new DBConnectionProvider();

    private String driverName;
    private String dbUrl;
    private String username;
    private String password;

    private Connection connection;

    @SneakyThrows
    private DBConnectionProvider(){
        loadProperties();
        Class.forName(driverName);
    }

    public static synchronized DBConnectionProvider getInstance() {
        return instance;
    }


    @SneakyThrows
    public Connection getConnection(){
        if(connection==null || connection.isClosed()){
            connection = DriverManager.getConnection(dbUrl, username, password);
        }
        return connection;
    }

    private void loadProperties() {
        Properties properties = AppUtil.loadProperties();
        driverName = properties.getProperty("db.driver.class.name");
        dbUrl = properties.getProperty("db.url");
        username = properties.getProperty("db.username");
        password = properties.getProperty("db.password");
    }

}