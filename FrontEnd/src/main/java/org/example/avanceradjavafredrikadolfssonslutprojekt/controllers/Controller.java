package org.example.avanceradjavafredrikadolfssonslutprojekt.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.avanceradjavafredrikadolfssonslutprojekt.controllers.buttons.AddTaskButtonPressed;
import org.example.avanceradjavafredrikadolfssonslutprojekt.controllers.buttons.RemoveButtonPressed;
import org.example.avanceradjavafredrikadolfssonslutprojekt.controllers.buttons.UpdateTaskButtonPressed;
import org.example.avanceradjavafredrikadolfssonslutprojekt.models.ToDoTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final String urlString = "http://localhost:8080/api/todo";

    @FXML
    public ListView<String> listView_TaskName;
    @FXML
    public ListView<String> listView_TaskDescription;
    @FXML
    public TextArea input_TaskDescription;
    @FXML
    public TextField input_TaskName;
    @FXML
    public TextArea input_EditTaskDescription;
    @FXML
    public TextField input_EditTaskName;
    @FXML
    public TextField input_EditTaskId;
    public ListView<Integer> listView_TaskId;
    private List<ToDoTask> myTasks;

    //Updates the listview
    @FXML
    public void updateListView() throws MalformedURLException {
        UpdateTaskList updateButtonPressed = new UpdateTaskList();
        this.myTasks = updateButtonPressed.updateTaskList(urlString, this.myTasks);

        listView_TaskName.setItems(updateButtonPressed.getTaskName());
        listView_TaskDescription.setItems(updateButtonPressed.getTaskDescription());
        listView_TaskId.setItems(updateButtonPressed.getTaskId());

    }

    //Reads the existing data from the server on boot
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.myTasks = new ArrayList<>();
            updateListView();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    //Adds a given task and task description to the list of existing tasks
    //Also calls the update listView method to update the list automatically
    @FXML
    public void addTaskButtonPressed() throws MalformedURLException {
        AddTaskButtonPressed addTaskButtonPressed = new AddTaskButtonPressed();
        addTaskButtonPressed.buttonPressed(urlString , this.myTasks, input_TaskName.getText(), input_TaskDescription.getText());
        input_TaskName.clear();
        input_TaskDescription.clear();
        updateListView();
    }

    @FXML
    public void editTaskButtonPressed(MouseEvent mouseEvent) throws MalformedURLException {
        UpdateTaskButtonPressed updateTaskButtonPressed= new UpdateTaskButtonPressed();
        updateTaskButtonPressed.buttonPressed(urlString, this.myTasks, input_EditTaskName.getText(), input_EditTaskDescription.getText(), input_EditTaskId.getText());
        input_EditTaskName.clear();
        input_EditTaskDescription.clear();
        input_EditTaskId.clear();
        updateListView();
    }

    @FXML
    public void removeButtonClicked() throws MalformedURLException {
        RemoveButtonPressed removeButtonPressed = new RemoveButtonPressed();
        removeButtonPressed.buttonPressed(urlString, this.myTasks, listView_TaskName.getSelectionModel().getSelectedItem());
        for(ToDoTask task : this.myTasks){
            System.out.println(task.getName());
        }
        updateListView();
    }
}