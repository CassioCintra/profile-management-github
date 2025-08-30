package br.com.mutant.profilemanagementgithub.helpers;

import br.com.mutant.profilemanagementgithub.adapter.rest.external.GitHubUserResponse;

import java.util.ArrayList;
import java.util.List;

public class GitHubApiFactory {

    public static List<GitHubUserResponse> generateGitHubUsersResponse(Integer quantity){
        List<GitHubUserResponse> users = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            GitHubUserResponse response = GitHubApiFactory.generateGitHubUserResponse(
                    (long) i,
                    "login" + i,
                    "https://www.url" + i + ".test/"
            );
            users.add(response);
        }
        return users;
    }

    public static GitHubUserResponse generateGitHubUserResponse(Long id, String login, String url){
        return new GitHubUserResponse(
                id,
                login,
                "",
                "",
                "",
                url,
                "",
                "",
                "",
                false
        );
    }
}
