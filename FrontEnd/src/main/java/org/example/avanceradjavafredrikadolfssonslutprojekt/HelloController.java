package org.example.avanceradjavafredrikadolfssonslutprojekt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.avanceradjavafredrikadolfssonslutprojekt.models.ToDoTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public ListView<String> listView_TaskName;
    public ListView<String> listView_TaskDescription;
    public TextArea input_TaskDescription;
    public TextField input_TaskName;
    public TextArea input_EditTaskDescription;
    public TextField input_EditTaskName;
    public TextField input_EditTaskId;
    public ListView listView_TaskId;
    List<ToDoTask> myTasks;

    //Updates the listView when the update button is pressed
    @FXML
    @SuppressWarnings("unchecked")
    public void updateButtonClicked(MouseEvent mouseEvent) throws JsonProcessingException {
        updateListView();
   }


    //Reads the response from the server
    public String readResponse(HttpURLConnection connection) throws IOException {
        BufferedReader reader;
        //All codes between 200 and 300 are good for us
        if(connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300){
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        //Ever other code is bad
        else{
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        StringBuilder response = new StringBuilder();
        String line;

        while((line = reader.readLine()) != null){
            response.append(line);
        }
        reader.close();
        return response.toString();

    }

    //Parses a given json String to a list of that object
    public List<ToDoTask> parseTask(String json) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<ToDoTask>>() {});
    }

    //Updates the listview
    @SuppressWarnings("unchecked")
    public void updateListView(){
        String response = "";
        try{
            URL url = new URL("http://localhost:8080/api/todo");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            response = readResponse(connection);
            System.out.println(response);
            myTasks = parseTask(response);

            for(ToDoTask task : myTasks){
                System.out.println("Name: " + task.getName() + ", Description: " + task.getDescription());
            }

        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return;
        }

        ObservableList<String> taskName = FXCollections.observableArrayList();
        ObservableList<String> taskDescription = FXCollections.observableArrayList();
        ObservableList<Integer> taskId = FXCollections.observableArrayList();

        for(ToDoTask task : myTasks){
            taskName.add(task.getName());
            taskDescription.add(task.getDescription());
            taskId.add(task.getId());
        }

        listView_TaskName.setItems(taskName);
        listView_TaskDescription.setItems(taskDescription);
        listView_TaskId.setItems(taskId);

    }

    //Reads the existing data from the server on boot
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateListView();
    }
    //Adds a given task and task description to the list of existing tasks
    //Also calls the update listView method to update the list automatically
    @FXML
    public void addTaskButtonPressed(MouseEvent mouseEvent) {
        try{
            String taskName = input_TaskName.getText();
            String taskDescription = input_TaskDescription.getText();

            ToDoTask toDoTask = new ToDoTask(taskName, taskDescription);

            toDoTask.setId(myTasks.size() + 1);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(toDoTask);

            URL url = new URL("http://localhost:8080/api/todo");
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
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        input_TaskName.clear();
        input_TaskDescription.clear();
        updateListView();
    }

    @FXML
    public void updateTaskButtonClicked(MouseEvent mouseEvent) {
        try{
        String newTaskName = input_EditTaskName.getText();
        String newTaskDescription = input_EditTaskDescription.getText();
        String taskId = input_EditTaskId.getText();

        URL url = new URL("http://localhost:8080/api/todo/" + taskId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        ToDoTask toDoTask = new ToDoTask(newTaskName, newTaskDescription);

        toDoTask.setId(myTasks.size() + 1);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(toDoTask);

        try(OutputStream outputStream = connection.getOutputStream()){
            byte[] input = json.getBytes();
            outputStream.write(input);
        }

        String response = readResponse(connection);
        System.out.println(response);

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        input_EditTaskName.clear();
        input_EditTaskDescription.clear();
        input_EditTaskId.clear();
        updateListView();
    }

    public void removeButtonClicked(MouseEvent mouseEvent) {

    }
}