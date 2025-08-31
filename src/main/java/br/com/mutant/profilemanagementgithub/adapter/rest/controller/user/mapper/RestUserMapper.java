package br.com.mutant.profilemanagementgithub.adapter.rest.controller.user.mapper;

import br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.mapper.RestRoleMapper;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.user.dto.UserCreateRequest;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.user.dto.UserResponse;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;

import java.util.HashSet;

public class RestUserMapper {
    public static ApplicationUser mapperToUser(UserCreateRequest request) {
        return new ApplicationUser(
                null,
                request.login(),
                request.password(),
                request.url(),
                new HashSet<>()
        );
    }

    public static UserResponse mapperToUserResponse(ApplicationUser applicationUser) {
        return new UserResponse(
                applicationUser.getId(),
                applicationUser.getLogin(),
                applicationUser.getUrl(),
                applicationUser.getRoles().stream()
                        .map(RestRoleMapper::mapperToRoleResponse)
                        .toList()
        );
    }
}
