package br.com.mutant.profilemanagementgithub.domain.model.user;

import br.com.mutant.profilemanagementgithub.domain.exceptions.auth.AuthenticationException;
import br.com.mutant.profilemanagementgithub.domain.exceptions.role.RoleConflictException;
import br.com.mutant.profilemanagementgithub.domain.model.role.Role;
import br.com.mutant.profilemanagementgithub.helpers.ApplicationUsersFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationUserTest {

    @Test
    void should_add_a_new_role() {
        ApplicationUser applicationUser = ApplicationUsersFactory.generateGitHubUser(1L, "Login", "www.url.com");
        Role role = new Role(1L, "Login");

        applicationUser.addRole(role);

        assertThat(applicationUser.getRoles().size()).isNotZero();
        assertThat(applicationUser.getRoles().contains(role)).isTrue();
    }

    @Test
    void should_throw_a_exception_if_already_has_the_role() {
        ApplicationUser applicationUser = ApplicationUsersFactory.generateGitHubUser(1L, "Login", "www.url.com");
        Role role = new Role(1L, "Login");
        applicationUser.addRole(role);

        assertThatThrownBy(() -> applicationUser.addRole(role))
            .isInstanceOf(RoleConflictException.class)
            .hasMessageContaining("User already has role");
    }

    @Test
    void should_throw_a_exception_if_password_dont_match(){
        ApplicationUser applicationUser = ApplicationUsersFactory.generateGitHubUser(1L, "Login", "www.url.com");
        String wrongPassword = "wrongPassword";

        assertThatThrownBy(() -> applicationUser.validatePassword(wrongPassword))
                .isInstanceOf(AuthenticationException.class)
                .hasMessageContaining(AuthenticationException.wrongPassword().getMessage());
    }
}