package org.example.avanceradjavafredrikadolfssonslutprojekt.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.avanceradjavafredrikadolfssonslutprojekt.models.ToDoTask;
import org.example.avanceradjavafredrikadolfssonslutprojekt.utility.Utility;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class UpdateTaskList extends Utility {
    private ObservableList<String> taskName;
    private ObservableList<String> taskDescription;
    private ObservableList<Integer> taskId;

    public List<ToDoTask> updateTaskList(String urlString, List<ToDoTask> tasks) {
        String response;
        try {

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            response = readResponse(connection);
            tasks = parseTask(response);


            this.taskName = FXCollections.observableArrayList();
            this.taskDescription = FXCollections.observableArrayList();
            this.taskId = FXCollections.observableArrayList();

            for (ToDoTask task : tasks) {
                taskName.add(task.getName());
                taskDescription.add(task.getDescription());
                taskId.add(task.getId());
            }
        } catch (Exception e) {
           getDialogBox().createDialogBox("Errror: " + e.getMessage(), "Error");
        }
        return tasks;
    }

    public ObservableList<String> getTaskName() {
        return taskName;
    }

    public ObservableList<String> getTaskDescription() {
        return taskDescription;
    }

    public ObservableList<Integer> getTaskId() {
        return taskId;
    }
}
