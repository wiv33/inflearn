package org.psawesome.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class InitConfig {
    @Bean
    ConnectionFactoryInitializer factoryInitializer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        ResourceDatabasePopulator databasePopular = new ResourceDatabasePopulator(new ClassPathResource("schema.sql"));
//        databasePopular.addScript(new ClassPathResource("/data/data.sql"));
        initializer.setDatabasePopulator(databasePopular);
        return initializer;
    }
}
