package com.barisyenigun.ToDo.response;

import com.barisyenigun.ToDo.entity.Todo;
import com.barisyenigun.ToDo.entity.User;
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
    private User user;

    public static TodoResponse fromEntity(Todo todo, User user){
        if (todo == null){
            return null;
        }
        else{
            return TodoResponse.builder()
                    .id(todo.getId())
                    .text(todo.getText())
                    .user(todo.getUser())
                    .build();
        }
    }
}
