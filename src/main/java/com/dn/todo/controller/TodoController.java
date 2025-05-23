package com.dn.todo.controller;

import com.dn.todo.exception.TodoNotFound;
import com.dn.todo.model.Todo;
import com.dn.todo.service.ITodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/v1")
@RequiredArgsConstructor
public class TodoController {
    private final ITodoService todoService;

    @GetMapping("{id}")
    public ResponseEntity<Todo> getTodoById(@RequestParam("id") int id) throws TodoNotFound{
        Todo todo = todoService.findById(id);
        if(todo == null) throw new TodoNotFound("Todo with id : " + id + " not found");
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() throws TodoNotFound {
        List<Todo> allTodos = todoService.findAll();
        if(allTodos.isEmpty()) throw new TodoNotFound("No Todos available");
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> postTodo(@RequestBody Todo todo) {
        int id = todoService.saveTodo(todo.getName(), todo.getDescription(), todo.getExpectedCompletionDate());
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    // TODO: Need to add rest of the controller methods
}
