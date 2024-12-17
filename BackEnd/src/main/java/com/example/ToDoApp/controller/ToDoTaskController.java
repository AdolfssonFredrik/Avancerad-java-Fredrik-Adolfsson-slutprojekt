package com.example.ToDoApp.controller;

import com.example.ToDoApp.Model.ToDoTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoTaskController implements ToDoTaskControllerInterface {
    private List<ToDoTask> tasks = new ArrayList<>();

    public ToDoTaskController(){
        tasks.add(new ToDoTask("Clean", "Clean the house"));
        tasks.get(0).setId(tasks.size());
        tasks.add(new ToDoTask("Read", "Read the house"));
        tasks.get(1).setId(tasks.size());
    }


    @Override
    public List<ToDoTask> getAllTasks() {
        return tasks;
    }

    @Override
    public ResponseEntity<ToDoTask> addNewTask(ToDoTask toDoTask) {
        if(toDoTask != null && toDoTask.getId() > 0){
            tasks.add(toDoTask);
            return ResponseEntity.status(HttpStatus.CREATED).body(toDoTask);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<ToDoTask> updateTask(int id, ToDoTask toDoTask) {
        for(ToDoTask task : tasks){
            if(task.getId() == id){
                task.setName(toDoTask.getName());
                task.setDescription(toDoTask.getDescription());
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<String> deleteTask(int id) {
        boolean removed = false;
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getId() == id){
                tasks.remove(i);
                removed = true;
            }
        }
        if(removed){
            return ResponseEntity.ok("Book with ID: " + id + " was deleted.");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID: " + id + " Not Found");
        }
    }
}
