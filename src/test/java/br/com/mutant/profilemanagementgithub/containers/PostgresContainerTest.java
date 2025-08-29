package br.com.mutant.profilemanagementgithub.containers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(PostgresContainer.class)
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
