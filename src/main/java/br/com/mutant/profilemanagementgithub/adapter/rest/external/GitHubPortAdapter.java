package br.com.mutant.profilemanagementgithub.adapter.rest.external;

import br.com.mutant.profilemanagementgithub.domain.exceptions.GitHubException;
import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.domain.ports.required.GitHubPort;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public record GitHubPortAdapter(GitHubClient gitHubClient) implements GitHubPort {

    @Override
    public List<GitHubUser> getGitHubUsers(Integer quantity) {
        try {
            List<GitHubUserResponse> gitHubUsersResponse = gitHubClient.getUsers(quantity);

            if (CollectionUtils.isEmpty(gitHubUsersResponse)) {
                return Collections.emptyList();
            }
            return GitHubUserMapper.toGitHubUsersList(gitHubUsersResponse);
        }catch (FeignException e) {
            throw GitHubException.serviceUnavailable(e);
        }
    }

}
