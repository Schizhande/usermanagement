package com.quatrohaus.usermanagement.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RecordNotFoundException.class})
    public ResponseEntity<ErrorBody> handleNotFound(RecordNotFoundException ex) {
        log.error(ex.getMessage());
        ErrorBody errorBody = ErrorBody.builder().message(ex.getMessage()).debugMessage(new RecordNotFoundException(ex.getMessage()).toString()).build();
        return new ResponseEntity(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({PasswordNotMatchException.class})
    public ResponseEntity<ErrorBody> handleNotMatch(PasswordNotMatchException ex) {
        log.error(ex.getMessage());
        ErrorBody errorBody = ErrorBody.builder().message(ex.getMessage()).debugMessage(new PasswordNotMatchException(ex.getMessage()).toString()).build();
        return new ResponseEntity(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UsernameAlreadyExistsException.class})
    public ResponseEntity<ErrorBody> handleUsernameAlreadyExist(UsernameAlreadyExistsException ex) {
        log.error(ex.getMessage());
        ErrorBody errorBody = ErrorBody.builder().message(ex.getMessage()).debugMessage(new UsernameAlreadyExistsException(ex.getMessage()).toString()).build();
        return new ResponseEntity(errorBody, HttpStatus.CONFLICT);
    }

}
