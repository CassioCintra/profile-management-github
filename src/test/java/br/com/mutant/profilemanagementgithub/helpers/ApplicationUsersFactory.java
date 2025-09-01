package br.com.mutant.profilemanagementgithub.helpers;

import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.model.role.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApplicationUsersFactory {

    public static List<ApplicationUser> generateGitHubUsers(Integer quantity) {
        List<ApplicationUser> applicationUserList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            ApplicationUser applicationUser = ApplicationUsersFactory.generateGitHubUser(
                    (long) i,
                    "login" + i,
                    "https://www.url" + i + ".test/"
            );
            applicationUserList.add(applicationUser);
        }
        return applicationUserList;
    }

    public static List<ApplicationUser> generateGitHubUsersWithoutId(Integer quantity) {
        List<ApplicationUser> applicationUserList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            ApplicationUser applicationUser = ApplicationUsersFactory.generateGitHubUser(
                    null,
                    "login" + i,
                    "https://www.url" + i + ".test/"
            );
            applicationUserList.add(applicationUser);
        }
        return applicationUserList;
    }

    public static ApplicationUser generateGitHubUser(Long id, String login, String url) {
        return new ApplicationUser(
                id,
                login,
                "password",
                url,
                new HashSet<>()
        );
    }

    public static ApplicationUser generateGitHubUserWithRole(Long id, String login, String url) {
        return new ApplicationUser(
                id,
                login,
                "password",
                url,
                Set.of(new Role("Test"), new Role("ADM"))
        );
    }
}
