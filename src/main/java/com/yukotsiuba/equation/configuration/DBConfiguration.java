package com.yukotsiuba.equation.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfiguration {

    @Value("${db.link}")
    private String password;
    @Value("${db.link}")
    private String username;
    @Value("${db.link}")
    private String link;

    @Bean
    public DataSource getDataSoure() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl(link);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
