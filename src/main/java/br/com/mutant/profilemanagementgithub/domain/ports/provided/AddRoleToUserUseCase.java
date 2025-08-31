package br.com.mutant.profilemanagementgithub.domain.ports.provided;

import br.com.mutant.profilemanagementgithub.domain.model.Role;

public interface AddRoleToUserUseCase {

    void addRoleToUser(Long roleId, Long userId);

}
