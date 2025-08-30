package br.com.mutant.profilemanagementgithub.domain.ports.required;

import br.com.mutant.profilemanagementgithub.domain.model.Role;

public interface RoleRepository {

    void save(Role role);

    Boolean existsByName(String name);
}
