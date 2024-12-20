package org.example.avanceradjavafredrikadolfssonslutprojekt.controllers.buttons;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.avanceradjavafredrikadolfssonslutprojekt.models.ToDoTask;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class UpdateTaskButtonPressed extends ButtonPressed {


    @Override
    public void buttonPressed(String urlString, List<ToDoTask> tasks, String taskName, String taskDescripton, String taskId) {

        try {
            URL url = new URL(urlString + "/" + taskId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            ToDoTask toDoTask = new ToDoTask(taskName, taskDescripton);

            toDoTask.setId(Integer.parseInt(taskId));

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(toDoTask);

            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = json.getBytes();
                outputStream.write(input);
            }

            String response = readResponse(connection);
            System.out.println(response);
        } catch (Exception e) {
            getDialogBox().createDialogBox("Error: " + e.getMessage(), "Error");
        }
    }

}
