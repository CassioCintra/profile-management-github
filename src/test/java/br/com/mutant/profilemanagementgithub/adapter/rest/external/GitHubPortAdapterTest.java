package br.com.mutant.profilemanagementgithub.adapter.rest.external;

import br.com.mutant.profilemanagementgithub.domain.exceptions.GitHubException;
import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.helpers.GitHubApiFactory;
import br.com.mutant.profilemanagementgithub.helpers.GitHubUsersFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GitHubPortAdapterTest {

    @Mock
    private GitHubClient gitHubClient;

    @InjectMocks
    private GitHubPortAdapter gitHubPortAdapter;

    private static final Integer USERS_QUANTITY = 30;

    @Test
    void should_return_github_users_list_when_api_call_is_successful() {
        List<GitHubUserResponse> mockResponse = GitHubApiFactory.generateGitHubUsersResponse(USERS_QUANTITY);
        GitHubUser mockUser = GitHubUsersFactory.generateGitHubUser(0L, "login0", "https://www.url0.test/");
        when(gitHubClient.getUsers(any())).thenReturn(mockResponse);

        List<GitHubUser> result = gitHubPortAdapter.getGitHubUsers(USERS_QUANTITY);

        assertThat(result).hasSize(USERS_QUANTITY);
        assertThat(result.getFirst()).isEqualTo(mockUser);
        verify(gitHubClient).getUsers(USERS_QUANTITY);
    }

    @Test
    void should_return_empty_list_when_api_returns_empty_response() {
        when(gitHubClient.getUsers(USERS_QUANTITY)).thenReturn(List.of());

        List<GitHubUser> result = gitHubPortAdapter.getGitHubUsers(USERS_QUANTITY);

        assertThat(result).isEmpty();
        verify(gitHubClient).getUsers(USERS_QUANTITY);
    }

    @Test
    void should_throw_github_exception_when_api_call_fails(){
        when(gitHubClient.getUsers(USERS_QUANTITY)).thenThrow(GitHubException.serviceUnavailable(new Exception()));

        assertThatThrownBy(() -> gitHubPortAdapter.getGitHubUsers(USERS_QUANTITY))
                .isInstanceOf(GitHubException.class)
                .hasMessageContaining("cannot connect to GitHub API");
    }
}