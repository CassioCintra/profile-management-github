package br.com.mutant.profilemanagementgithub.domain.exceptions.auth;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }

    public static AuthenticationException wrongPassword() {
        return new AuthenticationException("Request password don't match.");
    }
}
