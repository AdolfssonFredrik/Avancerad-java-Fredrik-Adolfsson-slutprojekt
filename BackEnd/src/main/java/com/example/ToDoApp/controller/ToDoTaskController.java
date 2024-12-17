package com.example.ToDoApp.controller;

import com.example.ToDoApp.Model.ToDoTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoTaskController {
    private List<ToDoTask> tasks = new ArrayList<>();

    public ToDoTaskController(){
        tasks.add(new ToDoTask("Clean", "Clean the house"));
        tasks.get(0).setId(tasks.size());
        tasks.add(new ToDoTask("Read", "Read the house"));
        tasks.get(1).setId(tasks.size());
    }

    @GetMapping
    public 
}
