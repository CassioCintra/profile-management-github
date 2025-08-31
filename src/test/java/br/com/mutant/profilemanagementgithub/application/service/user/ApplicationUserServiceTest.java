package br.com.mutant.profilemanagementgithub.application.service.user;

import br.com.mutant.profilemanagementgithub.domain.exceptions.role.RoleValidationException;
import br.com.mutant.profilemanagementgithub.domain.exceptions.user.InvalidUserDataException;
import br.com.mutant.profilemanagementgithub.domain.exceptions.user.UserAlreadyExistsException;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.model.role.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.required.user.ApplicationUserRepository;
import br.com.mutant.profilemanagementgithub.domain.ports.required.role.RoleRepository;
import br.com.mutant.profilemanagementgithub.helpers.ApplicationUsersFactory;
import br.com.mutant.profilemanagementgithub.helpers.RolesFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationUserServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ApplicationUserRepository applicationUserRepository;

    @InjectMocks
    private ApplicationUserService applicationUserService;

    @Test
    void should_add_role_to_user() {
        ApplicationUser applicationUser = ApplicationUsersFactory.generateGitHubUser(1L, "Login", "www.url.com");
        Role role = RolesFactory.generateRole(1L, "Test");
        when(applicationUserRepository.findById(any())).thenReturn(applicationUser);
        when(roleRepository.findById(any())).thenReturn(role);

        applicationUserService.addRoleToUser(applicationUser.getId(), role.getId());

        verify(roleRepository).findById(role.getId());
        verify(roleRepository).findById(role.getId());
        verify(applicationUserRepository).save(applicationUser);
    }

    @Test
    void should_throw_exception_when_user_id_is_null() {
        assertThatThrownBy(() -> applicationUserService.addRoleToUser(1L, null))
                .isInstanceOf(InvalidUserDataException.class)
                .hasMessageContaining("User ID is required and cannot be null");
    }

    @Test
    void should_throw_exception_when_role_id_is_null() {
        assertThatThrownBy(() -> applicationUserService.addRoleToUser(null, 1L))
                .isInstanceOf(RoleValidationException.class)
                .hasMessageContaining("Role cannot be null");
    }

    @Test
    void should_return_all_users() {
        List<ApplicationUser> users = ApplicationUsersFactory.generateGitHubUsers(30);
        when(applicationUserRepository.findAll()).thenReturn(users);

        List<ApplicationUser> applicationUsers = applicationUserService.findAllUsers();

        verify(applicationUserRepository).findAll();
        assertThat(applicationUsers).isEqualTo(users);
    }

    @Test
    void should_return_empty_list_when_users_is_empty() {
        when(applicationUserRepository.findAll()).thenReturn(Collections.emptyList());

        List<ApplicationUser> applicationUsers = applicationUserService.findAllUsers();

        verify(applicationUserRepository).findAll();
        assertThat(applicationUsers).isEmpty();
    }

    @Test
    void should_save_user(){
        ApplicationUser applicationUser = ApplicationUsersFactory.generateGitHubUser(1L, "Login", "www.url.com");
        when(applicationUserRepository.existsByLogin(any())).thenReturn(false);
        when(applicationUserRepository.save(any())).thenReturn(applicationUser);

        applicationUser = applicationUserService.create(applicationUser);

        verify(applicationUserRepository).existsByLogin(applicationUser.getLogin());
        verify(applicationUserRepository).save(applicationUser);
        assertThat(applicationUser.getId()).isNotNull();
    }

    @Test
    void should_throw_exception_when_user_is_null(){
        assertThatThrownBy(() -> applicationUserService.create(null))
                .isInstanceOf(InvalidUserDataException.class)
                .hasMessageContaining("User cannot be null");
    }

    @Test
    void should_throw_exception_when_user_login_already_exists(){
        ApplicationUser applicationUser = ApplicationUsersFactory.generateGitHubUser(1L, "Login", "www.url.com");
        when(applicationUserRepository.existsByLogin(any())).thenReturn(true);

        assertThatThrownBy(() -> applicationUserService.create(applicationUser))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessageContaining("Login already exists");
    }
}