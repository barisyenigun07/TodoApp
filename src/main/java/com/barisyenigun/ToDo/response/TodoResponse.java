package com.barisyenigun.ToDo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class TodoResponse {
    private Long id;
    private String text;
    private UserResponse userResponse;
}
