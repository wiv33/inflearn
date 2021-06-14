package org.psawesome.config;

import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.h2.Driver;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import reactor.core.publisher.Mono;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableTransactionManagement
@EnableR2dbcRepositories(basePackages = "org.psawesome")
@EnableR2dbcAuditing
public class R2dbcConfig extends AbstractR2dbcConfiguration {
    @Bean
    public R2dbcTransactionManager h2TransactionManager() {
        return new R2dbcTransactionManager(connectionFactory());
    }

    @Override
    @Bean(name = "connectionFactory")
    public ConnectionFactory connectionFactory() {
//        ConnectionFactory connectionFactory = ConnectionFactories
//                .get("r2dbc:h2:tcp://localhost:1521/jpashop?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                                                                   .option(DRIVER, "h2")
                                                                   .option(PROTOCOL, "mem")
                                                                   .option(HOST, "localhost")
                                                                   .option(PORT, 1521)
                                                                   .option(USER, "sa")
                                                                   .option(PASSWORD, "")
                                                                   .option(DATABASE, "jpashop")
//                                                                   .option()
                                                                   .build();
        ConnectionFactory cf = ConnectionFactories.get(options);
//        Publisher<? extends Connection> publisher = cf.create();
//        Mono<? extends Connection> connectionMono = Mono.from(publisher);
        return cf;
    }
}
