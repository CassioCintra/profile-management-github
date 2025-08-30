package br.com.mutant.profilemanagementgithub.adapter.persistence.repository;

import br.com.mutant.profilemanagementgithub.config.RepositoryIntegrationTest;
import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.helpers.DatabaseCleaner;
import br.com.mutant.profilemanagementgithub.helpers.GitHubUsersFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RepositoryIntegrationTest
class GitHubUserRepositoryAdapterTest {

    @Autowired
    private GitHubUserRepositoryAdapter repository;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    void setUp() {
        databaseCleaner.cleanUp();
    }

    @Test
    void should_save_all_github_users() {
        List<GitHubUser> stubGitHubUsers = GitHubUsersFactory.generateGitHubUsers(10);

        repository.saveAll(stubGitHubUsers);

        List<GitHubUser> findUsers = repository.findAll();
        assertThat(findUsers.size()).isEqualTo(stubGitHubUsers.size());
        IntStream.range(0, findUsers.size()).forEach(i -> {
            assertThat(findUsers.get(i).getUrl()).isEqualTo(stubGitHubUsers.get(i).getUrl());
            assertThat(findUsers.get(i).getLogin()).isEqualTo(stubGitHubUsers.get(i).getLogin());
        });
    }

    @Test
    void should_delete_all_github_users() {
        List<GitHubUser> stubGitHubUsers = GitHubUsersFactory.generateGitHubUsers(10);
        repository.saveAll(stubGitHubUsers);

        repository.deleteAll();

        List<GitHubUser> findUsers = repository.findAll();
        assertThat(findUsers.size()).isZero();
    }

    @Test
    void should_find_all_github_users() {
        List<GitHubUser> stubGitHubUsers = GitHubUsersFactory.generateGitHubUsers(10);
        repository.saveAll(stubGitHubUsers);

        List<GitHubUser> persistedUsers = repository.findAll();

        assertThat(persistedUsers.size()).isEqualTo(stubGitHubUsers.size());
    }

    @Test
    void should_save_a_github_user_with_role(){
        GitHubUser stubGitHubUser = GitHubUsersFactory.generateGitHubUserWithRole(0L, "login", "http://url.com/");

        repository.save(stubGitHubUser);

        List<GitHubUser> findUsers = repository.findAll();
        assertThat(findUsers.size()).isNotZero();
        assertThat(findUsers.getFirst().getRoles().size()).isEqualTo(stubGitHubUser.getRoles().size());
    }

    @Test
    void should_find_all_github_users_and_yours_roles() {
        GitHubUser stubGitHubUser = GitHubUsersFactory.generateGitHubUserWithRole(0L, "login", "http://url.com/");
        repository.save(stubGitHubUser);

        List<GitHubUser> findUsers = repository.findAll();

        assertThat(findUsers.size()).isNotZero();
        assertThat(findUsers.getFirst().getRoles().size()).isEqualTo(stubGitHubUser.getRoles().size());
    }


}