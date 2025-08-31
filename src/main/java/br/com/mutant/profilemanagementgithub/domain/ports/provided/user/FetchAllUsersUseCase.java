package br.com.mutant.profilemanagementgithub.domain.ports.provided.user;

import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;

import java.util.List;

public interface FetchAllUsersUseCase {

    List<ApplicationUser> findAllUsers();

}
