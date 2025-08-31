package br.com.mutant.profilemanagementgithub.application.service.security;

import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.ports.required.user.ApplicationUserRepository;
import br.com.mutant.profilemanagementgithub.helpers.ApplicationUsersFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {
    @Mock
    private ApplicationUserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void should_find_user_by_login() {
        ApplicationUser user = ApplicationUsersFactory.generateGitHubUser(1L, "login", "www.url.com");
        when(userRepository.findByLogin(any())).thenReturn(user);

        UserDetails result = customUserDetailsService.loadUserByUsername(user.getLogin());

        verify(userRepository).findByLogin(user.getLogin());
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(user.getLogin());
        assertThat(result.getPassword()).isEmpty();
        assertThat(result.getAuthorities().toString()).isEqualTo(user.getRoles().toString());
    }

    @Test
    void should_load_user_by_username_with_empty_roles() {
        ApplicationUser user = ApplicationUsersFactory.generateGitHubUser(1L, "login", "www.url.com");
        user.setRoles(Collections.emptySet());
        when(userRepository.findByLogin(any())).thenReturn(user);

        UserDetails result = customUserDetailsService.loadUserByUsername(user.getLogin());

        verify(userRepository).findByLogin(user.getLogin());
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(user.getLogin());
        assertThat(result.getPassword()).isEmpty();
        assertThat(result.getAuthorities()).isEmpty();
    }

    @Test
    void should_load_user_by_username_with_null_roles() {
        ApplicationUser user = ApplicationUsersFactory.generateGitHubUser(1L, "login", "www.url.com");
        user.setRoles(null);
        when(userRepository.findByLogin(any())).thenReturn(user);

        UserDetails result = customUserDetailsService.loadUserByUsername(user.getLogin());

        verify(userRepository).findByLogin(user.getLogin());
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(user.getLogin());
        assertThat(result.getAuthorities()).isEmpty();
    }
}