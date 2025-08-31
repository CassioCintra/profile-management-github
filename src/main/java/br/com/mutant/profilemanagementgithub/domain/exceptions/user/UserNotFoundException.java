package br.com.mutant.profilemanagementgithub.domain.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public static UserNotFoundException notFoundById(){
        return new UserNotFoundException("User not found by id");
    }

    public static UserNotFoundException notFoundByLogin(){
        return new UserNotFoundException("User not found by login");
    }
}
