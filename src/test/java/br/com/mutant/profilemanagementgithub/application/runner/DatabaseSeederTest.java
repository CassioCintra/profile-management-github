package br.com.mutant.profilemanagementgithub.application.runner;

import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.domain.ports.required.GitHubPort;
import br.com.mutant.profilemanagementgithub.domain.ports.required.GitHubUserRepository;
import br.com.mutant.profilemanagementgithub.helpers.GitHubUsersFactory;
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
    private GitHubPort gitHubPort;

    @Mock
    private GitHubUserRepository gitHubUserRepository;

    @InjectMocks
    private DatabaseSeeder databaseSeeder;

    private static final Integer USERS_QUANTITY = 30;

    @Test
    void should_save_users_when_api_returns_data() {
        List<GitHubUser> gitHubUsers = GitHubUsersFactory.generateGitHubUsers(USERS_QUANTITY);
        when(gitHubPort.getGitHubUsers(USERS_QUANTITY)).thenReturn(gitHubUsers);

        databaseSeeder.run();

        verify(gitHubPort).getGitHubUsers(USERS_QUANTITY);
        verify(gitHubUserRepository).deleteAll();
        verify(gitHubUserRepository).saveAll(gitHubUsers);
    }

    @Test
    void should_not_save_users_when_api_returns_empty_list() {
        when(gitHubPort.getGitHubUsers(USERS_QUANTITY)).thenReturn(List.of());

        databaseSeeder.run();

        verify(gitHubPort).getGitHubUsers(USERS_QUANTITY);
        verify(gitHubUserRepository, never()).deleteAll();
        verify(gitHubUserRepository, never()).saveAll(anyList());
    }
}