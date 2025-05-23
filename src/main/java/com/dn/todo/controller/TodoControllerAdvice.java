package com.dn.todo.controller;

import com.dn.todo.exception.TodoNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TodoControllerAdvice {

    @ExceptionHandler(TodoNotFound.class)
    public ResponseEntity<String> todoNotFoundHandler(TodoNotFound todoNotFound) {
        return new ResponseEntity<>(todoNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }
}
