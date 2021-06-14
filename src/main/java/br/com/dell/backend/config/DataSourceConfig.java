package br.com.dell.backend.config;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    
    @Bean
    public DataSource getDataSource() {
    	var dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:usuariodb");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("password");
        return dataSourceBuilder.build();
    }
}
