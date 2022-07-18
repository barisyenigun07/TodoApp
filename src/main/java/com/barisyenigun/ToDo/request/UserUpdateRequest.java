package com.barisyenigun.ToDo.request;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class UserUpdateRequest {
    private String username;
    private String password;
}
