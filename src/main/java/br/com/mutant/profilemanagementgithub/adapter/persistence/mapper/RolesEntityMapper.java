package br.com.mutant.profilemanagementgithub.adapter.persistence.mapper;

import br.com.mutant.profilemanagementgithub.adapter.persistence.entity.role.RoleEntity;
import br.com.mutant.profilemanagementgithub.domain.model.role.Role;

import java.util.HashSet;

public class RolesEntityMapper {

    public static Role mapToRole(RoleEntity entity){
        return new Role(
                entity.getId(),
                entity.getName()
        );
    }

    public static RoleEntity mapToRoleEntity(Role role){
        return new RoleEntity(
                role.getId(),
                role.getName(),
                new HashSet<>()
        );
    }
}
