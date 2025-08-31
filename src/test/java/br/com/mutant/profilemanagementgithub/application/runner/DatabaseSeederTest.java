package br.com.mutant.profilemanagementgithub.application.runner;

import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.ports.required.githubapi.GitHubApiPort;
import br.com.mutant.profilemanagementgithub.domain.ports.required.user.ApplicationUserRepository;
import br.com.mutant.profilemanagementgithub.helpers.ApplicationUsersFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DatabaseSeederTest {

    @Mock
    private GitHubApiPort gitHubApiPort;

    @Mock
    private ApplicationUserRepository applicationUserRepository;

    @InjectMocks
    private DatabaseSeeder databaseSeeder;

    private static final Integer USERS_QUANTITY = 30;

    @Test
    void should_save_users_when_api_returns_data() {
        List<ApplicationUser> applicationUsers = ApplicationUsersFactory.generateGitHubUsers(USERS_QUANTITY);
        when(gitHubApiPort.getGitHubUsers(USERS_QUANTITY)).thenReturn(applicationUsers);

        databaseSeeder.run();

        verify(gitHubApiPort).getGitHubUsers(USERS_QUANTITY);
        verify(applicationUserRepository).deleteAll();
        verify(applicationUserRepository).saveAll(applicationUsers);
    }

    @Test
    void should_not_save_users_when_api_returns_empty_list() {
        when(gitHubApiPort.getGitHubUsers(USERS_QUANTITY)).thenReturn(List.of());

        databaseSeeder.run();

        verify(gitHubApiPort).getGitHubUsers(USERS_QUANTITY);
        verify(applicationUserRepository, never()).deleteAll();
        verify(applicationUserRepository, never()).saveAll(anyList());
    }
}