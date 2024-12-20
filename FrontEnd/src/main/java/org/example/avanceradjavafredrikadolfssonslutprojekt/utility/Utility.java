package org.example.avanceradjavafredrikadolfssonslutprojekt.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.avanceradjavafredrikadolfssonslutprojekt.dialogBoxes.DialogBox;
import org.example.avanceradjavafredrikadolfssonslutprojekt.models.ToDoTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;

public class Utility {
    private final DialogBox dialogBox;

    public Utility(){
        dialogBox = new DialogBox();
    }

    //Reads the respone from the server
    //Code between 200 and 300 is good
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
    public List<ToDoTask> parseTask(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<ToDoTask>>() {});
    }

    public DialogBox getDialogBox() {
        return dialogBox;
    }
}
