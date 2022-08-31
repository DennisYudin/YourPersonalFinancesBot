package dev.yudin.YourPersonalFinancesBot.config;//package com.yourcodereview.java01.yourpersonalfinancesbot.config;
//
//import com.yourcodereview.java01.yourpersonalfinancesbot.entities.PersonalFinancesBot;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import java.util.Objects;
//import javax.sql.DataSource;
//
//@Configuration
//@PropertySource("classpath:application.properties")
//public class AppConfig {
//
//    @Autowired
//    private Environment propertyDataHolder;
//
//    @Autowired
//    private Environment telegramBotDataHolder;
//
//    @Bean
//    public DataSource dataSource() {
//
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        String jdbcDriver = propertyDataHolder.getProperty("jdbc.driver");
//        String jdbcUrl = propertyDataHolder.getProperty("jdbc.url");
//        String jdbcUser = propertyDataHolder.getProperty("jdbc.user");
//        String jdbcPassword = propertyDataHolder.getProperty("jdbc.password");
//
//        dataSource.setDriverClassName(Objects.requireNonNull(jdbcDriver));
//        dataSource.setUrl(jdbcUrl);
//        dataSource.setUsername(jdbcUser);
//        dataSource.setPassword(jdbcPassword);
//
//        return dataSource;
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate(dataSource());
//    }
//
////    @Bean
////    public PersonalFinancesBot personalFinancesBot() {
////
////        PersonalFinancesBot personalFinancesBot = new PersonalFinancesBot();
////
////        personalFinancesBot.setBotName(telegramBotDataHolder.getProperty("telegramBot.userName"));
////        personalFinancesBot.setBotToken(telegramBotDataHolder.getProperty("telegramBot.botToken"));
////
////        return personalFinancesBot;
////    }
//}
