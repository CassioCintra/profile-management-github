package br.com.mutant.profilemanagementgithub.adapter.persistence.mapper;

import br.com.mutant.profilemanagementgithub.adapter.persistence.entity.GitHubUserEntity;
import br.com.mutant.profilemanagementgithub.adapter.persistence.entity.RoleEntity;
import br.com.mutant.profilemanagementgithub.domain.model.Role;

import java.util.HashSet;

public class RolesEntityMapper {

    public static Role mapToRole(RoleEntity entity){
        return new Role(
                entity.getId(),
                entity.getName()
        );
    }

    public static RoleEntity mapToRoleEntity(Role role, Long userId){
        RoleEntity entity = new RoleEntity(
                role.getId(),
                role.getName(),
                new HashSet<>()
        );

        entity.getUsers().add(new GitHubUserEntity(userId));
        return entity;
    }
}
