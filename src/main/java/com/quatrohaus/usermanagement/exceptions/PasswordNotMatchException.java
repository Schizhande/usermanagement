package com.quatrohaus.usermanagement.exceptions;

public class PasswordNotMatchException extends Exception {

    private String message;

    public PasswordNotMatchException(String password_not_does_not_matche) {
        message=password_not_does_not_matche;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
