package br.com.mutant.profilemanagementgithub.adapter.persistence.repository;

import br.com.mutant.profilemanagementgithub.config.RepositoryIntegrationTest;
import br.com.mutant.profilemanagementgithub.domain.model.Role;
import br.com.mutant.profilemanagementgithub.helpers.DatabaseCleaner;
import br.com.mutant.profilemanagementgithub.helpers.RolesFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@RepositoryIntegrationTest
class RoleRepositoryAdapterTest {

    @Autowired
    private RoleRepositoryAdapter repositoryAdapter;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    void setUp() {
        databaseCleaner.cleanUp();
    }

    @Test
    void should_save_role() {
        Role stubRole = RolesFactory.generateRole("Test");

        repositoryAdapter.save(stubRole);

        Role findRole = repositoryAdapter.findByName(stubRole.getName());
        assertThat(findRole).isNotNull();
        assertThat(findRole.getName()).isEqualTo(stubRole.getName());
    }

    @Test
    void should_find_role_by_name() {
        Role stubRole = RolesFactory.generateRole("Test");
        repositoryAdapter.save(stubRole);
        Role anotherStubRole = RolesFactory.generateRole("Another");
        repositoryAdapter.save(anotherStubRole);

        Role findRole = repositoryAdapter.findByName(stubRole.getName());

        assertThat(findRole).isNotNull();
        assertThat(findRole.getName()).isEqualTo(stubRole.getName());
        assertThat(findRole.getName()).isNotEqualTo(anotherStubRole.getName());
    }

    @Test
    void should_return_true_when_role_exists() {
        Role stubRole = RolesFactory.generateRole("Test");
        repositoryAdapter.save(stubRole);

        Boolean roleExists = repositoryAdapter.existsByName(stubRole.getName());

        assertThat(roleExists).isTrue();
    }

    @Test
    void should_return_false_when_role_dont_exists() {
        Role stubRole = RolesFactory.generateRole("Test");

        Boolean roleExists = repositoryAdapter.existsByName(stubRole.getName());

        assertThat(roleExists).isFalse();
    }

    @Test
    void should_find_role_by_id() {
        Role stubRole = RolesFactory.generateRole("Test");
        stubRole = repositoryAdapter.save(stubRole);

        Role findRole = repositoryAdapter.findById(stubRole.getId());

        assertThat(findRole).isNotNull();
        assertThat(findRole.getName()).isEqualTo(stubRole.getName());
    }
}