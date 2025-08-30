package br.com.mutant.profilemanagementgithub.adapter.rest.external;

import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;

import java.util.HashSet;
import java.util.List;

public class GitHubUserMapper {

    public static List<GitHubUser> toGitHubUsersList(List<GitHubUserResponse> gitHubUsersResponse) {
        return gitHubUsersResponse.stream()
                .map(GitHubUserMapper::toGitHubUser)
                .toList();
    }

    public static GitHubUser toGitHubUser(GitHubUserResponse gitHubUserResponse) {
        return new GitHubUser(
                gitHubUserResponse.id(),
                gitHubUserResponse.login(),
                gitHubUserResponse.url(),
                new HashSet<>()
        );
    }
}
