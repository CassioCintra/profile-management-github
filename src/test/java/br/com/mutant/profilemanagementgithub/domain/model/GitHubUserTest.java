package br.com.mutant.profilemanagementgithub.domain.model;

import br.com.mutant.profilemanagementgithub.domain.exceptions.RoleException;
import br.com.mutant.profilemanagementgithub.helpers.GitHubUsersFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GitHubUserTest {

    @Test
    void should_add_a_new_role() {
        GitHubUser gitHubUser = GitHubUsersFactory.generateGitHubUser(1L, "Login", "www.url.com");
        Role role = new Role(1L, "Login");

        gitHubUser.addRole(role);

        assertThat(gitHubUser.getRoles().size()).isNotZero();
        assertThat(gitHubUser.getRoles().contains(role)).isTrue();
    }

    @Test
    void should_throw_a_exception_if_already_has_the_role() {
        GitHubUser gitHubUser = GitHubUsersFactory.generateGitHubUser(1L, "Login", "www.url.com");
        Role role = new Role(1L, "Login");
        gitHubUser.addRole(role);

        assertThatThrownBy(() -> gitHubUser.addRole(role))
            .isInstanceOf(RoleException.class)
            .hasMessageContaining("User already have role");
    }
}