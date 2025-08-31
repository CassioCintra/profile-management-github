package br.com.mutant.profilemanagementgithub.domain.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public static UserAlreadyExistsException loginAlreadyExists() {
        return new UserAlreadyExistsException("Login already exists");
    }
}
