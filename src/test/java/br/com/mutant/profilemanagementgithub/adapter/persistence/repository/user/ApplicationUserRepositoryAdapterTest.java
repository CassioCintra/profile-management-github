package br.com.mutant.profilemanagementgithub.adapter.persistence.repository.user;

import br.com.mutant.profilemanagementgithub.config.RepositoryIntegrationTest;
import br.com.mutant.profilemanagementgithub.containers.PostgresContainer;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.helpers.DatabaseCleaner;
import br.com.mutant.profilemanagementgithub.helpers.ApplicationUsersFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RepositoryIntegrationTest(imports = {ApplicationUserRepositoryAdapter.class, PostgresContainer.class, DatabaseCleaner.class})
class ApplicationUserRepositoryAdapterTest {

    @Autowired
    private ApplicationUserRepositoryAdapter repository;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    void setUp() {
        databaseCleaner.cleanUp();
    }

    @Test
    void should_save_all_github_users() {
        List<ApplicationUser> stubApplicationUsers = ApplicationUsersFactory.generateGitHubUsers(10);

        repository.saveAll(stubApplicationUsers);

        List<ApplicationUser> findUsers = repository.findAll();
        assertThat(findUsers.size()).isEqualTo(stubApplicationUsers.size());
        IntStream.range(0, findUsers.size()).forEach(i -> {
            assertThat(findUsers.get(i).getUrl()).isEqualTo(stubApplicationUsers.get(i).getUrl());
            assertThat(findUsers.get(i).getLogin()).isEqualTo(stubApplicationUsers.get(i).getLogin());
        });
    }

    @Test
    void should_delete_all_github_users() {
        List<ApplicationUser> stubApplicationUsers = ApplicationUsersFactory.generateGitHubUsers(10);
        repository.saveAll(stubApplicationUsers);

        repository.deleteAll();

        List<ApplicationUser> findUsers = repository.findAll();
        assertThat(findUsers.size()).isZero();
    }

    @Test
    void should_find_all_github_users() {
        List<ApplicationUser> stubApplicationUsers = ApplicationUsersFactory.generateGitHubUsers(10);
        repository.saveAll(stubApplicationUsers);

        List<ApplicationUser> persistedUsers = repository.findAll();

        assertThat(persistedUsers.size()).isEqualTo(stubApplicationUsers.size());
    }

    @Test
    void should_save_a_github_user_with_role(){
        ApplicationUser stubApplicationUser = ApplicationUsersFactory.generateGitHubUserWithRole(0L, "login", "http://url.com/");

        repository.save(stubApplicationUser);

        List<ApplicationUser> findUsers = repository.findAll();
        assertThat(findUsers.size()).isNotZero();
        assertThat(findUsers.getFirst().getRoles().size()).isEqualTo(stubApplicationUser.getRoles().size());
    }

    @Test
    void should_find_all_github_users_and_yours_roles() {
        ApplicationUser stubApplicationUser = ApplicationUsersFactory.generateGitHubUserWithRole(0L, "login", "http://url.com/");
        repository.save(stubApplicationUser);

        List<ApplicationUser> findUsers = repository.findAll();

        assertThat(findUsers.size()).isNotZero();
        assertThat(findUsers.getFirst().getRoles().size()).isEqualTo(stubApplicationUser.getRoles().size());
    }

    @Test
    void should_find_github_user_by_id() {
        ApplicationUser stubApplicationUser = ApplicationUsersFactory.generateGitHubUserWithRole(1L, "login", "http://url.com/");
        repository.save(stubApplicationUser);

        ApplicationUser findUser = repository.findById(stubApplicationUser.getId());

        assertThat(findUser.getId()).isNotNull();
        assertThat(findUser.getLogin()).isEqualTo(stubApplicationUser.getLogin());
        assertThat(findUser.getUrl()).isEqualTo(stubApplicationUser.getUrl());
    }

    @Test
    void should_find_user_by_login(){
        ApplicationUser stubApplicationUser = ApplicationUsersFactory.generateGitHubUserWithRole(1L, "login", "http://url.com/");
        repository.save(stubApplicationUser);

        ApplicationUser findUser = repository.findByLogin(stubApplicationUser.getLogin());

        assertThat(findUser.getId()).isNotNull();
        assertThat(findUser.getLogin()).isEqualTo(stubApplicationUser.getLogin());
        assertThat(findUser.getUrl()).isEqualTo(stubApplicationUser.getUrl());
    }
}