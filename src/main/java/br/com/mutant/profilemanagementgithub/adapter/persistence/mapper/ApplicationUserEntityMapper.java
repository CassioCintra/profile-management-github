package br.com.mutant.profilemanagementgithub.adapter.persistence.mapper;

import br.com.mutant.profilemanagementgithub.adapter.persistence.entity.user.ApplicationUserEntity;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;

import java.util.HashSet;

public class ApplicationUserEntityMapper {

    public static ApplicationUser mapToGitHubUser(ApplicationUserEntity entity){
        ApplicationUser user = new ApplicationUser(
                entity.getId(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getUrl(),
                new HashSet<>()
        );
        entity.getRoles().forEach(role -> user.getRoles()
                .add(RolesEntityMapper.mapToRole(role))
        );
        return user;
    }

    public static ApplicationUserEntity mapToGitHubUserEntity(ApplicationUser user){
        ApplicationUserEntity entity = new ApplicationUserEntity(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getUrl(),
                new HashSet<>()
        );
        user.getRoles().forEach(role -> entity.getRoles()
                .add(RolesEntityMapper.mapToRoleEntity(role))
        );
        return entity;
    }
}
