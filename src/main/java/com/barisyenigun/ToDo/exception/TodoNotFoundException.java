package com.barisyenigun.ToDo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "ID NOT FOUND")
public class TodoNotFoundException extends RuntimeException{

    public TodoNotFoundException(){
        super("Todo with that ID not found!");
    }

}
