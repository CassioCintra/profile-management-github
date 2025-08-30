package br.com.mutant.profilemanagementgithub.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubUser {
    private Long id;
    private String login;
    private String url;
}
