package br.com.mutant.profilemanagementgithub.adapter.rest.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubApiUserResponse(
        Long id,
        String login,
        @JsonProperty("node_id") String nodeId,
        @JsonProperty("avatar_url") String avatarUrl,
        @JsonProperty("gravatar_id") String gravatarId,
        String url,
        @JsonProperty("html_url") String htmlUrl,
        @JsonProperty("followers_url") String followersUrl,
        String type,
        @JsonProperty("site_admin") boolean siteAdmin
) {}
