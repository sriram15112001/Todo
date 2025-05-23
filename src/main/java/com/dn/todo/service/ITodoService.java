package com.dn.todo.service;

import com.dn.todo.model.Todo;

import java.time.LocalDateTime;
import java.util.List;

public interface ITodoService {
    public Todo findById(int id);

    public List<Todo> findAll();

    public int saveTodo(String name, String description, LocalDateTime completionDate);

    public boolean updateTodo(int id, String name, String description, LocalDateTime completionDate, boolean isCompleted);

    public boolean deleteTodo(int id);
}
