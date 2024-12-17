package com.example.ToDoApp.controller;

import com.example.ToDoApp.Model.ToDoTask;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public interface ToDoTaskControllerInterface {

    @GetMapping
    public List<ToDoTask> getAllTasks();

    @PostMapping
    public ResponseEntity<ToDoTask> addNewTask(@RequestBody ToDoTask toDoTask);

    @PostMapping("/{id}")
    public ResponseEntity<ToDoTask> updateTask(@PathVariable int id, @RequestBody ToDoTask toDoTask);

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id);


}
