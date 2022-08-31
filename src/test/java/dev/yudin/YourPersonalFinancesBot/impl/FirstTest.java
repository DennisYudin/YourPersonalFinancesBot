package dev.yudin.YourPersonalFinancesBot.impl;//package com.yourcodereview.java01.yourpersonalfinancesbot.dao.impl;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
////@ContextConfiguration(classes = AppConfigTest.class)
////@Sql(scripts = {
////        "file:src/test/resources/cleanUpTables.sql",
////        "file:src/test/resources/createTables.sql",
////        "file:src/test/resources/populateTables.sql"},
////        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
////@Sql(scripts = "file:src/test/resources/cleanUpTables.sql",
////        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//@WebAppConfiguration
//class FirstTest {
//
//    @Autowired
//    private DAOImpl dao;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Test
//    void connectionTest() {
//        String url = "jdbc:h2:mem:telegramBotTestDB";
//        String user ="sa";
//        String password = "password";
//
//        System.out.println("Connecting to DB");
//
//        try {
//            Connection connection = DriverManager.getConnection(url, user, password);
//
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from expenses");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(1));
//            }
//            resultSet.close();
//            statement.close();
//            System.out.println("Connection success!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void sessionFactoryTest() {
//
//		String TEXT_MESSAGE = "Sum added \nDo ya wanna try again?";
//
//		System.out.println(TEXT_MESSAGE);
//    }
//}
