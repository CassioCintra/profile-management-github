package br.com.mutant.profilemanagementgithub.domain.ports.provided.user;

import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;

public interface CreateUserUseCase {

    ApplicationUser create(ApplicationUser applicationUser);

}
