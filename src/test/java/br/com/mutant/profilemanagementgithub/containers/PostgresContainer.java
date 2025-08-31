package br.com.mutant.profilemanagementgithub.containers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Testcontainers
public class PostgresContainer {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15.3-alpine3.17");

    @Bean
    @Primary
    @ServiceConnection
    public PostgreSQLContainer<?> postgreSQLContainer() {
        log.info("-------- Initialize PostgresSQL Container --------");
        return container
                .withDatabaseName("github")
                .withUsername("postgres");
    }
}
