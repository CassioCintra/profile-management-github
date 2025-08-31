package br.com.mutant.profilemanagementgithub.adapter.rest.controller.user.dto;

import br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.dto.RoleResponse;

import java.util.List;

public record UserResponse(
        Long id,
        String login,
        String url,
        List<RoleResponse> roles
) {
}
