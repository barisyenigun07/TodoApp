package com.barisyenigun.ToDo.request;

import com.barisyenigun.ToDo.entity.User;
import lombok.*;

@Data
@ToString
public class TodoRequest {
    private Long id;
    private String text;
    private User user;
}
