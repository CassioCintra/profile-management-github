package br.com.mutant.profilemanagementgithub.containers;

import br.com.mutant.profilemanagementgithub.config.ContainerTest;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.assertj.core.api.Assertions.assertThat;

@ContainerTest(imports =  {PostgresContainerTest.class})
public class PostgresContainerTest {

    @Container
    private final PostgreSQLContainer<?> container = PostgresContainer.container;

    @Test
    void postgres_container_should_be_created() {
        assertThat(container.isCreated()).isTrue();
    }

    @Test
    void postgres_container_should_be_running() {
        assertThat(container.isRunning()).isTrue();
    }
}
