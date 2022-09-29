package com.langworthytech.simplebillingsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

//@Configuration
////@PropertySource("classpath:application.properties")
//public class DataSourceConfig {
//
////    @Autowired
////    private Environment env;
//
//    @Bean
//    public DataSource getDataSource() {
//        DataSourceBuilder builder = DataSourceBuilder.create();
//        builder.driverClassName("com.mysql.cj.jdbc.Driver");
//        builder.url("jdbc:mysql://localhost:3306/simple_billing?useSSL=false&serverTimezone=UTC");
//        builder.username("root");
//        builder.password("Acura21");
//        return builder.build();
//    }
//
//}
