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
    @ServiceConnection
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:12");

    @Bean
    @Primary
    public PostgreSQLContainer<?> postgreSQLContainer() {
        log.info("-------- Initialize PostgresSQL Container --------");
        return container;
    }

    @Test
    void postgres_container_should_be_created() {
        assertThat(container.isCreated()).isTrue();
    }

    @Test
    void postgres_container_should_be_running() {
        assertThat(container.isRunning()).isTrue();
    }
}
