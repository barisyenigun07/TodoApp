package com.barisyenigun.ToDo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Password credentials in request and user do not match!")
public class PasswordsNotMatchedException extends RuntimeException{
    public PasswordsNotMatchedException(){
        super("Passwords do not match!");
    }
}
