package br.com.mutant.profilemanagementgithub.domain.exceptions.role;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RoleConflictException extends RuntimeException {
    public RoleConflictException(String message) {
        super(message);
    }

    public static RoleConflictException roleAlreadyExists(String roleName) {
        return new RoleConflictException(String.format("Role '%s' already exists", roleName));
    }

    public static RoleConflictException alreadyHasRole(String roleName) {
        return new RoleConflictException(String.format("User already has role '%s'", roleName));
    }
}

