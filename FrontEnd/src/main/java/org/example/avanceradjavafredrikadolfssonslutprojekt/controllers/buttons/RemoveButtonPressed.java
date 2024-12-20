package org.example.avanceradjavafredrikadolfssonslutprojekt.controllers.buttons;

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
                    getDialogBox().createDialogBox("Task with name: \"" + task.getName() + "\" has been removed.", "Success");
                }
            }
        } catch (Exception e) {
            getDialogBox().createDialogBox("Error: " + e.getMessage(), "Error");
        }
    }
}
