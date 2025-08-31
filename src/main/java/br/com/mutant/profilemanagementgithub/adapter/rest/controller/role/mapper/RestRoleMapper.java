package br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.mapper;

import br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.dto.RoleCreateRequest;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.dto.RoleResponse;
import br.com.mutant.profilemanagementgithub.domain.model.role.Role;

public class RestRoleMapper {
    public static RoleResponse mapperToRoleResponse(Role role) {
        return new RoleResponse(
                role.getId(),
                role.getName()
        );
    }

    public static Role mapperToRole(RoleCreateRequest request) {
        return new Role(
                null,
                request.name()
        );
    }
}
