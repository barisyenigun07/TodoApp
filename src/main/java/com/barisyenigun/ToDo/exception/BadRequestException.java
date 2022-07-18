package com.barisyenigun.ToDo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "TEXT IS EMPTY")
public class BadRequestException extends RuntimeException{
    public BadRequestException(){
        super("Bad Request");
    }
}
