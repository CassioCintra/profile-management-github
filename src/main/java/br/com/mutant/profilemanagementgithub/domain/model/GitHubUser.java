package br.com.mutant.profilemanagementgithub.domain.model;

import br.com.mutant.profilemanagementgithub.domain.exceptions.RoleException;
import br.com.mutant.profilemanagementgithub.domain.exceptions.auth.AuthenticationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class GitHubUser {
    private Long id;
    private String login;
    private String password;
    private String url;
    private Set<Role> roles = new HashSet<>();;

    public void addRole(Role role) {
        verifyIfAlreadyHasRole(role);
        this.roles.add(role);
    }

    private void verifyIfAlreadyHasRole(Role requestRole) {
        if(this.roles.contains(requestRole)){
            throw RoleException.alreadyHasRole(requestRole.getName());
        };
    }

    public void validatePassword(String password) {
        if (!this.password.equals(password)) {
            throw AuthenticationException.wrongPassword();
        }
    }
}
