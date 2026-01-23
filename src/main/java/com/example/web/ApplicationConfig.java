package com.example.web;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {
    @Bean
    public DataSource datasource(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3330");
        config.setUsername("root");
        config.setPassword("1234");
        config.setMaximumPoolSize(5);
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}
