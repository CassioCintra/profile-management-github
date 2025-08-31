package br.com.mutant.profilemanagementgithub.domain.exceptions.user;

public class ApplicationUserException extends RuntimeException {
    public ApplicationUserException(String message) {
        super(message);
    }

    public static ApplicationUserException requiredUserId() {
        return new ApplicationUserException("User ID is required and cannot be null");
    }

    public static ApplicationUserException cannotFindUser() {
        return new ApplicationUserException("User ID is required and cannot be null");
    }

    public static ApplicationUserException userLoginAlreadyExists() {
        return new ApplicationUserException("User login already exists.");
    }

    public static ApplicationUserException userCanNotBeNull() {
        return new ApplicationUserException("User can not be null");
    }
}
