package org.example.avanceradjavafredrikadolfssonslutprojekt.buttonControllers;

import org.example.avanceradjavafredrikadolfssonslutprojekt.HelloApplication;
import org.example.avanceradjavafredrikadolfssonslutprojekt.dialogBoxes.DialogBox;
import org.example.avanceradjavafredrikadolfssonslutprojekt.models.ToDoTask;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RemoveButtonPressed extends ButtonPressed {

    @Override
    public void buttonPressed(String urlString, List<ToDoTask> tasks, String taskName) {
        try {
            for (ToDoTask task : tasks) {
                if (task.getName().equals(taskName)) {
                    URL url = new URL(urlString + "/" + task.getId());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("DELETE");
                    connection.connect();

                    String response = connection.getResponseMessage();
                    new DialogBox("Task with name: \"" + task.getName() + "\" has been removed.", "Success");
                }
            }
        } catch (Exception e) {
            new DialogBox("Error: " + e.getMessage(), "Error");
        }
    }
}
