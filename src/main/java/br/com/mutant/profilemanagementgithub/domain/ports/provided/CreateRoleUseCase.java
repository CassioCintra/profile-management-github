package br.com.mutant.profilemanagementgithub.domain.ports.provided;

import br.com.mutant.profilemanagementgithub.domain.model.Role;

public interface CreateRoleUseCase {

    void createNewRole(Role role);

}
