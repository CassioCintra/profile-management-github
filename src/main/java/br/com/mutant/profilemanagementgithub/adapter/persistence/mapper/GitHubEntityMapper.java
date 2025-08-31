package br.com.mutant.profilemanagementgithub.adapter.persistence.mapper;

import br.com.mutant.profilemanagementgithub.adapter.persistence.entity.GitHubUserEntity;
import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;

import java.util.HashSet;

public class GitHubEntityMapper {

    public static GitHubUser mapToGitHubUser(GitHubUserEntity entity){
        GitHubUser user = new GitHubUser(
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

    public static GitHubUserEntity mapToGitHubUserEntity(GitHubUser user){
        GitHubUserEntity entity = new GitHubUserEntity(
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
