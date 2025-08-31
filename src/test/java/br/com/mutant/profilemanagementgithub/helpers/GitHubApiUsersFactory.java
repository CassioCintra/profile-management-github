package br.com.mutant.profilemanagementgithub.helpers;

import br.com.mutant.profilemanagementgithub.adapter.rest.external.dto.GitHubApiUserResponse;

import java.util.ArrayList;
import java.util.List;

public class GitHubApiUsersFactory {

    public static List<GitHubApiUserResponse> generateGitHubUsersResponse(Integer quantity){
        List<GitHubApiUserResponse> users = new ArrayList<>();
        for (Integer i = 0; i < quantity; i++) {
            GitHubApiUserResponse response = GitHubApiUsersFactory.generateGitHubUserResponse(
                    i.longValue(),
                    "login" + i,
                    "https://www.url" + i + ".test/"
            );
            users.add(response);
        }
        return users;
    }

    public static GitHubApiUserResponse generateGitHubUserResponse(Long id, String login, String url){
        return new GitHubApiUserResponse(
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
