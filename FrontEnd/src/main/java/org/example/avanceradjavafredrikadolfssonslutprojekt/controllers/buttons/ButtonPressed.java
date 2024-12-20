package org.example.avanceradjavafredrikadolfssonslutprojekt.controllers.buttons;

import org.example.avanceradjavafredrikadolfssonslutprojekt.models.ToDoTask;
import org.example.avanceradjavafredrikadolfssonslutprojekt.utility.Utility;

import java.util.List;

public abstract class ButtonPressed extends Utility {
    public void buttonPressed(String urlString, List<ToDoTask> tasks, String taskName, String taskDescripton){}
    public void buttonPressed(String urlString, List<ToDoTask> tasks, String taskName, String taskDescripton, String taskId){}
    public void buttonPressed(String urlString, List<ToDoTask> tasks, String taskName){}

    public ToDoTask newToDoTask(String taskName, String taskDescription){
        return new ToDoTask(taskName, taskDescription);
    }

}
