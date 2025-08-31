package br.com.mutant.profilemanagementgithub.adapter.rest.external;

import br.com.mutant.profilemanagementgithub.adapter.rest.external.client.GitHubClient;
import br.com.mutant.profilemanagementgithub.adapter.rest.external.dto.GitHubApiUserResponse;
import br.com.mutant.profilemanagementgithub.domain.exceptions.githubapi.GitHubApiException;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.ports.required.githubapi.GitHubApiPort;
import feign.FeignException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Component
public record GitHubApiPortAdapter(GitHubClient gitHubClient) implements GitHubApiPort {

    @Override
    public List<ApplicationUser> getGitHubUsers(Integer quantity) {
        try {
            List<GitHubApiUserResponse> gitHubUsersResponse = gitHubClient.getUsers(quantity);

            if (CollectionUtils.isEmpty(gitHubUsersResponse)) {
                return Collections.emptyList();
            }
            return GitHubApiUserMapper.toGitHubUsersList(gitHubUsersResponse);
        }catch (FeignException e) {
            throw GitHubApiException.serviceUnavailable(e);
        }
    }

}
