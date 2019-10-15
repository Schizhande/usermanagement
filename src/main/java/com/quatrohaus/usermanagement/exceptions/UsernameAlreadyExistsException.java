package com.quatrohaus.usermanagement.exceptions;

import javax.validation.constraints.NotBlank;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(@NotBlank(message = "username cannot be null") String message) {
        super(message);
    }
}
