package br.com.mutant.profilemanagementgithub.domain.model.user;

import br.com.mutant.profilemanagementgithub.domain.exceptions.role.RoleException;
import br.com.mutant.profilemanagementgithub.domain.exceptions.auth.AuthenticationException;
import br.com.mutant.profilemanagementgithub.domain.model.role.Role;
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
public class ApplicationUser {
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
