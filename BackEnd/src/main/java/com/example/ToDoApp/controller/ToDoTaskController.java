package com.example.ToDoApp.controller;

import com.example.ToDoApp.Model.ToDoTask;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoTaskController implements ToDoTaskControllerInterface {
    private List<ToDoTask> tasks = new ArrayList<>();


    public ToDoTaskController(){
        tasks.add(new ToDoTask("Clean", "Clean"));
    }

    @Override
    @GetMapping
    public List<ToDoTask> getAllTasks() {
        return tasks;
    }

    @Override
    @PostMapping
    public ResponseEntity<ToDoTask> addNewTask(ObjectNode objectNode) {
        if(objectNode != null){
            ToDoTask toDoTask = new ToDoTask(objectNode.get("name").asText(), objectNode.get("description").asText());
            toDoTask.setId(tasks.size() + 1);
            tasks.add(toDoTask);

            System.out.println(objectNode.get("name").asText() + " " + objectNode.get("description").asText());

            return ResponseEntity.status(HttpStatus.CREATED).body(toDoTask);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ToDoTask> updateTask(int id, ToDoTask toDoTask) {
        for(ToDoTask task : tasks){
            if(task.getId() == id){
                task.setName(toDoTask.getName());
                task.setDescription(toDoTask.getDescription());
                System.out.println(toDoTask.getId() + " " + toDoTask.getName() + " " + toDoTask.getDescription());
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(int id) {
        boolean removed = false;
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getId() == id){
                tasks.remove(i);
                removed = true;
            }
        }
        if(removed){
            return ResponseEntity.ok("Task with ID: " + id + " was deleted.");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with ID: " + id + " Not Found");
        }
    }
}
