package org.example.avanceradjavafredrikadolfssonslutprojekt.controllers.buttons;

import org.example.avanceradjavafredrikadolfssonslutprojekt.dialogBoxes.DialogBox;
import org.example.avanceradjavafredrikadolfssonslutprojekt.models.ToDoTask;
import org.example.avanceradjavafredrikadolfssonslutprojekt.utility.Utility;

import java.net.URL;
import java.util.List;

public abstract class ButtonPressed extends Utility {
    private URL url;
    private List<ToDoTask> tasks;



    public List<ToDoTask> buttonPressed(String urlString, List<ToDoTask> tasks){
        return tasks;
    }
    public void buttonPressed(String urlString, List<ToDoTask> tasks, String taskName, String taskDescripton){}
    public void buttonPressed(String urlString, List<ToDoTask> tasks, String taskName, String taskDescripton, String taskId){}
    public void buttonPressed(String urlString, List<ToDoTask> tasks, String taskName){}

}
