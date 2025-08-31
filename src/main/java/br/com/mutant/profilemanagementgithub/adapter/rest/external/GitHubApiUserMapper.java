package br.com.mutant.profilemanagementgithub.adapter.rest.external;

import br.com.mutant.profilemanagementgithub.adapter.rest.external.dto.GitHubApiUserResponse;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class GitHubApiUserMapper {

    public static List<ApplicationUser> toGitHubUsersList(List<GitHubApiUserResponse> gitHubUsersResponse) {
        return gitHubUsersResponse.stream()
                .map(GitHubApiUserMapper::toGitHubUser)
                .toList();
    }

    public static ApplicationUser toGitHubUser(GitHubApiUserResponse gitHubApiUserResponse) {
        return new ApplicationUser(
                gitHubApiUserResponse.id(),
                gitHubApiUserResponse.login(),
                UUID.randomUUID().toString(),
                gitHubApiUserResponse.url(),
                new HashSet<>()
        );
    }
}
