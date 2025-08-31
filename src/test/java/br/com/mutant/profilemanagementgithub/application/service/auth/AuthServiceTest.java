package br.com.mutant.profilemanagementgithub.application.service.auth;

import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.model.auth.AuthRequest;
import br.com.mutant.profilemanagementgithub.domain.ports.required.user.ApplicationUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private ApplicationUserRepository applicationUserRepository;

    @InjectMocks
    private AuthService authService;

    @Test
    void should_return_user_with_login_and_password() {
        ApplicationUser user = mock(ApplicationUser.class);
        AuthRequest request = new AuthRequest(user.getLogin(), user.getPassword());
        when(applicationUserRepository.findByLogin(any())).thenReturn(user);

        ApplicationUser returnedUser = authService.authorize(request);

        verify(applicationUserRepository).findByLogin(any());
        verify(user).validatePassword(any());
        assertThat(user.getLogin()).isEqualTo(returnedUser.getLogin());
    }
}