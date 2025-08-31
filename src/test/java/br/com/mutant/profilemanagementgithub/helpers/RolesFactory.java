package br.com.mutant.profilemanagementgithub.helpers;

import br.com.mutant.profilemanagementgithub.domain.model.role.Role;

public class RolesFactory {

    public static Role generateRole(String roleName) {
        return new Role(roleName);
    }

    public static Role generateRole(Long roleId, String roleName) {
        return new Role(roleId, roleName);
    }
}
