package br.com.mutant.profilemanagementgithub.domain.ports.required;

import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;

import java.util.List;

public interface GitHubUserRepository {

    void save(GitHubUser user);
    void saveAll(List<GitHubUser> users);
    void deleteAll();

    List<GitHubUser> findAll();

    GitHubUser findById(Long userId);

    GitHubUser findByLogin(String login);
}
