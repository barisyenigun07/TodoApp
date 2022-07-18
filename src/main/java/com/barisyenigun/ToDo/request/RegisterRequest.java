package com.barisyenigun.ToDo.request;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterRequest {
    private String username;
    private String password;
}
