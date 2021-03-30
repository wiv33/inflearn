package org.psawesome.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableR2dbcRepositories(basePackages = "org.psawesome")
public class R2dbcConfig extends AbstractR2dbcConfiguration {
    @Bean
    public R2dbcTransactionManager h2TransactionManager() {
        return new R2dbcTransactionManager(connectionFactory());
    }

    @Override
    @Bean(name = "connectionFactory")
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories
                .get("r2dbc:h2:mem:///jpashop?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
    }
}