package br.com.mutant.profilemanagementgithub.helpers;

import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.domain.model.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GitHubUsersFactory {

    public static List<GitHubUser> generateGitHubUsers(Integer quantity) {
        List<GitHubUser> gitHubUserList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            GitHubUser gitHubUser = GitHubUsersFactory.generateGitHubUser(
                    (long) i,
                    "login" + i,
                    "https://www.url" + i + ".test/"
            );
            gitHubUserList.add(gitHubUser);
        }
        return gitHubUserList;
    }

    public static GitHubUser generateGitHubUser(Long id, String login, String url) {
        return new GitHubUser(
                id,
                login,
                url,
                new HashSet<>()
        );
    }

    public static GitHubUser generateGitHubUserWithRole(Long id, String login, String url) {
        return new GitHubUser(
                id,
                login,
                url,
                Set.of(new Role("Test"), new Role("ADM"))
        );
    }
}
