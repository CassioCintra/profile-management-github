package br.com.mutant.profilemanagementgithub.domain.ports.required.role;

import br.com.mutant.profilemanagementgithub.domain.model.role.Role;

public interface RoleRepository {

    Role save(Role role);

    Boolean existsByName(String name);

    Role findById(Long roleId);
}
