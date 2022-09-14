package com.barisyenigun.ToDo.service;
import com.barisyenigun.ToDo.entity.Todo;
import com.barisyenigun.ToDo.entity.User;
import com.barisyenigun.ToDo.exception.TodoNotFoundException;
import com.barisyenigun.ToDo.repository.TodoRepository;
import com.barisyenigun.ToDo.request.TodoRequest;
import com.barisyenigun.ToDo.response.TodoResponse;
import com.barisyenigun.ToDo.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TodoService {
    private final TodoRepository todoRepository;

    private final UserService userService;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserService userService){
        this.todoRepository = todoRepository;
        this.userService = userService;
    }
    public List<TodoResponse> getTodos(){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new IllegalStateException());
        List<Todo> todos = todoRepository.findAllByUser(user);
        return todos.stream().map(todo -> fromEntity(todo)).collect(Collectors.toList());
    }

    public TodoResponse getTodo(Long todoId){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new IllegalStateException());
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException());
        if (!todo.getUser().getId().equals(user.getId())){
            throw new TodoNotFoundException();
        }
        return fromEntity(todo);
    }

    public void createTodo(TodoRequest body){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new IllegalStateException());
        Todo todo = new Todo();
        todo.setUser(user);
        todo.setText(body.getText());
        todoRepository.save(todo);
    }

    public void deleteTodo(Long todoId){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new IllegalStateException());
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException());
        if (!todo.getUser().getId().equals(user.getId())){
            throw new IllegalStateException();
        }
        todoRepository.deleteById(todoId);

    }
    public void updateTodo(Long todoId, TodoRequest body){
        User user = userService.getAuthenticatedUser().orElseThrow(() -> new IllegalStateException());
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException());

        if (!todo.getUser().getId().equals(user.getId())){
            throw new IllegalStateException();
        }
        todo.setText(body.getText());
        todoRepository.save(todo);
    }

    private TodoResponse fromEntity(Todo todo){
        return TodoResponse.builder()
                .id(todo.getId())
                .text(todo.getText())
                .userResponse(UserResponse.fromEntity(todo.getUser()))
                .build();
    }

}
