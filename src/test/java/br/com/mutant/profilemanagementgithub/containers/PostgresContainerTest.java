package br.com.mutant.profilemanagementgithub.containers;

import br.com.mutant.profilemanagementgithub.config.ContainerTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@ContainerTest(imports = PostgresContainer.class)
public class PostgresContainerTest {

    @Test
    void postgres_container_should_be_created() {
        assertThat(PostgresContainer.container.isCreated()).isTrue();
    }

    @Test
    void postgres_container_should_be_running() {
        assertThat(PostgresContainer.container.isRunning()).isTrue();
    }
}
