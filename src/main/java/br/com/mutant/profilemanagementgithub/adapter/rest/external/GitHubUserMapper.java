package br.com.mutant.profilemanagementgithub.adapter.rest.external;

import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;

import java.util.List;

public class GitHubUserMapper {

    public static List<GitHubUser> toGitHubUserList(List<GitHubUserResponse> gitHubUsersResponse) {
        return gitHubUsersResponse.stream()
                .map(GitHubUserMapper::toGitHubUserList)
                .toList();
    }

    public static GitHubUser toGitHubUserList(GitHubUserResponse gitHubUserResponse) {
        return new GitHubUser(
                gitHubUserResponse.id(),
                gitHubUserResponse.login(),
                gitHubUserResponse.url()
        );
    }
}
