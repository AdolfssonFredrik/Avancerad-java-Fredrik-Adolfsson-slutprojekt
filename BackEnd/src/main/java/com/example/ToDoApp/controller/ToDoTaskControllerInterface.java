package com.example.ToDoApp.controller;

import com.example.ToDoApp.Model.ToDoTask;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ToDoTaskControllerInterface {

    public List<ToDoTask> getAllTasks();


    public ResponseEntity<ToDoTask> addNewTask(@RequestBody ObjectNode objectNode);


    public ResponseEntity<ToDoTask> updateTask(@PathVariable int id, @RequestBody ToDoTask toDoTask);


    public ResponseEntity<String> deleteTask(@PathVariable int id);


}
