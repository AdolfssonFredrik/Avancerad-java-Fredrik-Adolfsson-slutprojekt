package org.example.avanceradjavafredrikadolfssonslutprojekt;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.avanceradjavafredrikadolfssonslutprojekt.buttonControllers.AddTaskButtonPressed;
import org.example.avanceradjavafredrikadolfssonslutprojekt.buttonControllers.RemoveButtonPressed;
import org.example.avanceradjavafredrikadolfssonslutprojekt.buttonControllers.UpdateButtonPressed;
import org.example.avanceradjavafredrikadolfssonslutprojekt.buttonControllers.UpdateTaskButtonPressed;
import org.example.avanceradjavafredrikadolfssonslutprojekt.models.ToDoTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private final String urlString = "http://localhost:8080/api/todo";

    public ListView<String> listView_TaskName;
    public ListView<String> listView_TaskDescription;
    public TextArea input_TaskDescription;
    public TextField input_TaskName;
    public TextArea input_EditTaskDescription;
    public TextField input_EditTaskName;
    public TextField input_EditTaskId;
    public ListView<Integer> listView_TaskId;
    List<ToDoTask> myTasks;

    //Updates the listview
    @FXML
    public void updateListView() throws MalformedURLException {
        UpdateButtonPressed updateButtonPressed = new UpdateButtonPressed();
        myTasks = updateButtonPressed.buttonPressed(urlString ,myTasks);
        listView_TaskName.getItems().clear();
        listView_TaskName.setItems(updateButtonPressed.getTaskName());

        listView_TaskDescription.getItems().clear();
        listView_TaskDescription.setItems(updateButtonPressed.getTaskDescription());

        listView_TaskId.getItems().clear();
        listView_TaskId.setItems(updateButtonPressed.getTaskId());

    }

    //Reads the existing data from the server on boot
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            myTasks = new ArrayList<>();
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
        addTaskButtonPressed.buttonPressed(urlString , myTasks, input_TaskName.getText(), input_TaskDescription.getText());
        input_TaskName.clear();
        input_TaskDescription.clear();
        updateListView();
    }

    @FXML
    public void editTaskButtonPressed(MouseEvent mouseEvent) throws MalformedURLException {
        UpdateTaskButtonPressed updateTaskButtonPressed= new UpdateTaskButtonPressed();
        updateTaskButtonPressed.buttonPressed(urlString, myTasks, input_EditTaskName.getText(), input_EditTaskDescription.getText(), input_EditTaskId.getText());
        input_EditTaskName.clear();
        input_EditTaskDescription.clear();
        input_EditTaskId.clear();
        updateListView();
    }

    @FXML
    public void removeButtonClicked() throws MalformedURLException {
        RemoveButtonPressed removeButtonPressed = new RemoveButtonPressed();
        removeButtonPressed.buttonPressed(urlString, myTasks, listView_TaskName.getSelectionModel().getSelectedItem());
        for(ToDoTask task : myTasks){
            System.out.println(task.getName());
        }
        updateListView();
    }
}