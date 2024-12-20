package org.example.avanceradjavafredrikadolfssonslutprojekt.buttonControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.avanceradjavafredrikadolfssonslutprojekt.dialogBoxes.DialogBox;
import org.example.avanceradjavafredrikadolfssonslutprojekt.models.ToDoTask;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class UpdateButtonPressed extends ButtonPressed {
    private ObservableList<String> taskName;
    private ObservableList<String> taskDescription;
    private ObservableList<Integer> taskId;

    @Override
    public List<ToDoTask> buttonPressed(String urlString, List<ToDoTask> tasks) {
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
           new DialogBox("Errror: " + e.getMessage(), "Error");
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
