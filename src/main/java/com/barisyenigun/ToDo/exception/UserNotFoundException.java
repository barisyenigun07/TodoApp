package com.barisyenigun.ToDo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Credentials are not matched!")
public class UserNotFoundException extends RuntimeException{
    
    public UserNotFoundException(){
        super("User Not Found!");
    }
}
