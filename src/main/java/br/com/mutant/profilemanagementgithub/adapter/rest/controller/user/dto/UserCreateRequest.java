package br.com.mutant.profilemanagementgithub.adapter.rest.controller.user.dto;

public record UserCreateRequest(
        String login,
        String password,
        String url
) {
}
