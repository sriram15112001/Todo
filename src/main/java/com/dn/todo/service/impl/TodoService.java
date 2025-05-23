package com.dn.todo.service.impl;

import com.dn.todo.model.Todo;
import com.dn.todo.repository.TodoRepository;
import com.dn.todo.service.ITodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService implements ITodoService {
    private final TodoRepository todoRepository;

    @Override
    public Todo findById(int id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public int saveTodo(String name, String description, LocalDateTime completionDate) {
        Todo todo = Todo.builder()
                .name(name)
                .description(description)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .expectedCompletionDate(completionDate)
                .isCompleted(false)
                .build();

        Todo save = todoRepository.save(todo);
        return save.getId();
    }

    @Override
    public boolean updateTodo(int id, String name, String description, LocalDateTime completionDate, boolean isCompleted) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if(todo != null) {
            todo.setName(name);
            todo.setDescription(description);
            todo.setExpectedCompletionDate(completionDate);
            todo.setCompleted(isCompleted);
            todo.setUpdatedDate(LocalDateTime.now());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTodo(int id) {
        if(todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
