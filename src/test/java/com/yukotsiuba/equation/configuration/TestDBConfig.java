package com.yukotsiuba.equation.configuration;

import com.yukotsiuba.equation.dao.extractor.EquationRowMapper;
import com.yukotsiuba.equation.dao.extractor.RootRowMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Import({EquationRowMapper.class, RootRowMapper.class})
public class TestDBConfig {
    
    public DataSource postgresDataSource() {
        final DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/db/equation/schema.sql")
                .addScript("classpath:/db/equation/test-data.sql")
                .addScript("classpath:/db/root/schema.sql")
                .addScript("classpath:/db/root/test-data.sql")
                .addScript("classpath:/db/equations_roots/schema.sql")
                .addScript("classpath:/db/equations_roots/test-data.sql")
                .build();

        return dataSource;
    }
    
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(postgresDataSource());
    }

}
