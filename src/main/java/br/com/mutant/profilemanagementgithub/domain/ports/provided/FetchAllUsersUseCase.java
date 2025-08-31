package br.com.mutant.profilemanagementgithub.domain.ports.provided;

import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;

import java.util.List;

public interface FetchAllUsersUseCase {

    List<GitHubUser> findAllUsers();

}
