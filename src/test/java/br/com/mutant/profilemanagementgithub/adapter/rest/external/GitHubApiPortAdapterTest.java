package br.com.mutant.profilemanagementgithub.adapter.rest.external;

import br.com.mutant.profilemanagementgithub.adapter.rest.external.client.GitHubClient;
import br.com.mutant.profilemanagementgithub.adapter.rest.external.dto.GitHubApiUserResponse;
import br.com.mutant.profilemanagementgithub.domain.exceptions.githubapi.GitHubApiException;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.helpers.GitHubApiUsersFactory;
import br.com.mutant.profilemanagementgithub.helpers.ApplicationUsersFactory;
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
class GitHubApiPortAdapterTest {

    @Mock
    private GitHubClient gitHubClient;

    @InjectMocks
    private GitHubApiPortAdapter gitHubPortAdapter;

    private static final Integer USERS_QUANTITY = 30;

    @Test
    void should_return_github_users_list_when_api_call_is_successful() {
        List<GitHubApiUserResponse> mockResponse = GitHubApiUsersFactory.generateGitHubUsersResponse(USERS_QUANTITY);
        ApplicationUser mockUser = ApplicationUsersFactory.generateGitHubUser(0L, "login0", "https://www.url0.test/");
        when(gitHubClient.getUsers(any())).thenReturn(mockResponse);

        List<ApplicationUser> result = gitHubPortAdapter.getGitHubUsers(USERS_QUANTITY);

        verify(gitHubClient).getUsers(USERS_QUANTITY);
        assertThat(result).hasSize(USERS_QUANTITY);
        assertThat(result.getFirst().getLogin()).isEqualTo(mockUser.getLogin());
        assertThat(result.getFirst().getUrl()).isEqualTo(mockUser.getUrl());
    }

    @Test
    void should_return_empty_list_when_api_returns_empty_response() {
        when(gitHubClient.getUsers(USERS_QUANTITY)).thenReturn(List.of());

        List<ApplicationUser> result = gitHubPortAdapter.getGitHubUsers(USERS_QUANTITY);

        assertThat(result).isEmpty();
        verify(gitHubClient).getUsers(USERS_QUANTITY);
    }

    @Test
    void should_throw_github_exception_when_api_call_fails(){
        when(gitHubClient.getUsers(USERS_QUANTITY)).thenThrow(GitHubApiException.serviceUnavailable(new Exception()));

        assertThatThrownBy(() -> gitHubPortAdapter.getGitHubUsers(USERS_QUANTITY))
                .isInstanceOf(GitHubApiException.class)
                .hasMessageContaining("cannot connect to GitHub API");
    }
}