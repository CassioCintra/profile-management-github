package br.com.mutant.profilemanagementgithub.domain.exceptions;

public class RoleException extends RuntimeException {
    public RoleException() {}

    public RoleException(String message) {
        super(message);
    }

    public RoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public static RoleException roleAlreadyExists(String roleName) {
        return new RoleException(String.format("Role %s already exists", roleName));
    }

    public static RoleException roleCannotBeNull() {
        return new RoleException("Role cannot be null");
    }

    public static RoleException alreadyHasRole(String roleName) {
        return new RoleException(String.format("User already have role %s", roleName));
    }

    public static RuntimeException cannotFindRole() {
        return new RoleException("Cannot find role");
    }
}
