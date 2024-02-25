package dev.yudin.yourpersonalfinancesbot.impl;//package com.yourcodereview.java01.yourpersonalfinancesbot.dao.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
//@SpringBootTest()
//@ContextConfiguration(classes = AppConfigTest.class)
@Sql(scripts = {
        "file:src/test/resources/cleanUpTables.sql",
        "file:src/test/resources/createTables.sql",
        "file:src/test/resources/populateTables.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "file:src/test/resources/cleanUpTables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WebAppConfiguration
class ConnectionTest {

    @Test
    void connectionTest() {
        String url = "jdbc:h2:mem:test";
        String user ="sa";
        String password = "password";

        System.out.println("Connecting to DB");

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from expense");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            resultSet.close();
            statement.close();
            System.out.println("Connection success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
