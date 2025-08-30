package br.com.mutant.profilemanagementgithub.helpers;

import br.com.mutant.profilemanagementgithub.domain.model.Role;

public class RolesFactory {

    public static Role generateRole(String roleName) {
        return new Role(roleName);
    }
}
