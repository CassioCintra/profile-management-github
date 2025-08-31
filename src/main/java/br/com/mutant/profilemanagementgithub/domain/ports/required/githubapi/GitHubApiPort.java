package br.com.mutant.profilemanagementgithub.domain.ports.required.githubapi;

import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;

import java.util.List;

public interface GitHubApiPort {

    List<ApplicationUser> getGitHubUsers(Integer quantity);
}
