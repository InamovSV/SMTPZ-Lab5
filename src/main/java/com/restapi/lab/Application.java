package com.restapi.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;

import java.sql.DriverManager;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        java.lang.Class.forName("org.postgresql.Driver");
        SpringApplication.run(Application.class, args);
    }
}