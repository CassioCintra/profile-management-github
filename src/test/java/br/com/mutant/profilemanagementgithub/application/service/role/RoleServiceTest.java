package br.com.mutant.profilemanagementgithub.application.service.role;

import br.com.mutant.profilemanagementgithub.domain.exceptions.role.RoleException;
import br.com.mutant.profilemanagementgithub.domain.model.role.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.required.user.RoleRepository;
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
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    void should_save_role() {
        Role role = RolesFactory.generateRole("Test");
        when(roleRepository.existsByName(any())).thenReturn(false);

        roleService.createNewRole(role);

        verify(roleRepository).existsByName(role.getName());
        verify(roleRepository).save(role);
    }

    @Test
    void should_throws_a_exception_when_role_is_null() {
        assertThatThrownBy(() -> roleService.createNewRole(null))
                .isInstanceOf(RoleException.class)
                .hasMessageContaining("Role cannot be null");
    }

    @Test
    void should_throws_a_exception_when_role_name_is_null() {
        Role role = RolesFactory.generateRole(null);

        assertThatThrownBy(() -> roleService.createNewRole(role))
                .isInstanceOf(RoleException.class)
                .hasMessageContaining("Role cannot be null");
    }

    @Test
    void should_throws_a_exception_when_role_already_exists() {
        Role role = RolesFactory.generateRole("Test");
        when(roleRepository.existsByName(any())).thenReturn(true);

        assertThatThrownBy(() -> roleService.createNewRole(role))
                .isInstanceOf(RoleException.class)
                .hasMessageContaining("Role Test already exists");
    }
}