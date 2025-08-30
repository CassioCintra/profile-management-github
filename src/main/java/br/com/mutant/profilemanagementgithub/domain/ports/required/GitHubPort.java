package br.com.mutant.profilemanagementgithub.domain.ports.required;

import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;

import java.util.List;

public interface GitHubPort {

    List<GitHubUser> getGitHubUsers(Integer quantity);
}
