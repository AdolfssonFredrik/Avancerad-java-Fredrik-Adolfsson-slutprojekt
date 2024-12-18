package org.example.avanceradjavafredrikadolfssonslutprojekt.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToDoTask {
    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;

    public ToDoTask(){}

    public ToDoTask(String name, String description){
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
