package br.com.mutant.profilemanagementgithub.application.service;

import br.com.mutant.profilemanagementgithub.domain.exceptions.GitHubUserException;
import br.com.mutant.profilemanagementgithub.domain.exceptions.RoleException;
import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.domain.model.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.required.GitHubUserRepository;
import br.com.mutant.profilemanagementgithub.domain.ports.required.RoleRepository;
import br.com.mutant.profilemanagementgithub.helpers.GitHubUsersFactory;
import br.com.mutant.profilemanagementgithub.helpers.RolesFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GitHubUserServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private GitHubUserRepository gitHubUserRepository;

    @InjectMocks
    private GitHubUserService gitHubUserService;

    @Test
    void should_add_role_to_user() {
        GitHubUser gitHubUser = GitHubUsersFactory.generateGitHubUser(1L, "Login", "www.url.com");
        Role role = RolesFactory.generateRole(1L, "Test");
        when(gitHubUserRepository.findById(any())).thenReturn(gitHubUser);
        when(roleRepository.findById(any())).thenReturn(role);

        gitHubUserService.addRoleToUser(gitHubUser.getId(), role.getId());

        verify(roleRepository).findById(role.getId());
        verify(roleRepository).findById(role.getId());
        verify(gitHubUserRepository).save(gitHubUser);
    }

    @Test
    void should_throw_exception_when_user_id_is_null() {
        assertThatThrownBy(() -> gitHubUserService.addRoleToUser(1L, null))
                .isInstanceOf(GitHubUserException.class)
                .hasMessageContaining("User ID is required and cannot be null");
    }

    @Test
    void should_throw_exception_when_role_id_is_null() {
        assertThatThrownBy(() -> gitHubUserService.addRoleToUser(null, 1L))
                .isInstanceOf(RoleException.class)
                .hasMessageContaining("Role cannot be null");
    }
}