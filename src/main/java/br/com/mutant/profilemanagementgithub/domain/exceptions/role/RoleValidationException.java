package br.com.mutant.profilemanagementgithub.domain.exceptions.role;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoleValidationException extends RuntimeException {
    public RoleValidationException(String message) {
        super(message);
    }

    public static RoleValidationException roleCannotBeNull() {
        return new RoleValidationException("Role cannot be null");
    }
}
