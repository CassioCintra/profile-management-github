package br.com.mutant.profilemanagementgithub.domain.ports.required;

import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.domain.model.Role;

import java.util.List;

public interface RoleRepository {

    Role save(Role role);

    Boolean existsByName(String name);

    Role findById(Long roleId);
}
