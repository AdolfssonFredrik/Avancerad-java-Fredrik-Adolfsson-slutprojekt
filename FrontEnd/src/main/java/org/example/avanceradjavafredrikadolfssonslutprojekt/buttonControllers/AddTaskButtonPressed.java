package org.example.avanceradjavafredrikadolfssonslutprojekt.buttonControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.avanceradjavafredrikadolfssonslutprojekt.dialogBoxes.DialogBox;
import org.example.avanceradjavafredrikadolfssonslutprojekt.models.ToDoTask;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AddTaskButtonPressed extends ButtonPressed {

    @Override
    public void buttonPressed(String urlString, List<ToDoTask> tasks, String taskName, String taskDescription) {

        try{
            if(taskName.isEmpty() || taskDescription.isEmpty()){
               new DialogBox("Please fill out entire form", "Error");
            }
            else{
                ToDoTask toDoTask = new ToDoTask(taskName, taskDescription);

                toDoTask.setId(tasks.size() + 1);

                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(toDoTask);

                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                try(OutputStream outputStream = connection.getOutputStream()){
                    byte[] input = json.getBytes();
                    outputStream.write(input);
                }

                String response = readResponse(connection);
                System.out.println(response);

                new DialogBox("Task with name: \"" + toDoTask.getName() + "\" has been added.", "Success");
            }
            }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }
}
