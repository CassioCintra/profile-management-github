package br.com.mutant.profilemanagementgithub.domain.exceptions.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }

    public static AuthenticationException wrongPassword() {
        return new AuthenticationException("Request password don't match.");
    }
}
