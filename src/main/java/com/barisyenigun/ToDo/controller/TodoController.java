package com.barisyenigun.ToDo.controller;
import com.barisyenigun.ToDo.request.TodoRequest;
import com.barisyenigun.ToDo.response.TodoResponse;
import com.barisyenigun.ToDo.service.TodoService;
import com.barisyenigun.ToDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/me/todo")
public class TodoController {

    private final TodoService todoService;
    private final UserService userService;

    @Autowired
    public TodoController(TodoService todoService, UserService userService){
        this.todoService = todoService;
        this.userService = userService;
    }
    @GetMapping
    public List<TodoResponse> getTodos(){
        return todoService.getTodos();
    }

    @GetMapping(path = "/{todoId}")
    public TodoResponse getTodo(@PathVariable Long todoId){
        return todoService.getTodo(todoId);
    }
    @PostMapping
    public void createTodo(@RequestBody TodoRequest body){
        todoService.createTodo(body);
    }
    @DeleteMapping(path = "/{todoId}")
    public void deleteTodo(@PathVariable("todoId") Long todoId){
        todoService.deleteTodo(todoId);
    }
    @PutMapping(path = "/{todoId}")
    public void updateTodo(@PathVariable("todoId") Long todoId, @RequestBody TodoRequest newTodo){
        todoService.updateTodo(todoId,newTodo);
    }

}
