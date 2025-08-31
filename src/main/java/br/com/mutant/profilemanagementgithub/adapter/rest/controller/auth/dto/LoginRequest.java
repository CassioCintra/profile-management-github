package br.com.mutant.profilemanagementgithub.adapter.rest.controller.auth.dto;

public record LoginRequest(
        String login,
        String password
) {
}
