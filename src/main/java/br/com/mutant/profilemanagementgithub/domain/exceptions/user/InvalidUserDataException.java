package br.com.mutant.profilemanagementgithub.domain.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserDataException extends RuntimeException {
    public InvalidUserDataException(String message) {
        super(message);
    }

    public static InvalidUserDataException userIdNull() {
        return new InvalidUserDataException("User ID is required and cannot be null");
    }

    public static InvalidUserDataException userNull() {
        return new InvalidUserDataException("User cannot be null");
    }
}
