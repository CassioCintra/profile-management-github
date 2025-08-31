package br.com.mutant.profilemanagementgithub.domain.ports.provided.role;

import br.com.mutant.profilemanagementgithub.domain.model.role.Role;

public interface CreateRoleUseCase {

    void createNewRole(Role role);

}
