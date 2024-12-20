# ToDo App

## Introduction

The goal of this project was to make a application that that utilazes spring boot for the backend and JavaFx for the frontend to create a ToDo app. The reason for doing this was partly becuase it was a school assingment but it was also to learn more of how frontend and backend interact. I feel like I have accomplished this goal and feel like I have learn a lot about how to think when making frontend as well as backend applications. I found this project really fun to do and also quiet challenging as I have never done anything like this before. 

## How does the app work?

#### Backend

The app makes use of spring boot for handling the backend and uses JavaFx for handling the frontend. In Spring Boot I make use of the 4 different request mappings, GET, POST, PUT and DELETE. The methods are first defined in an interface and then implemented in the Controller class. There are four different methods that each handel the four different actions.

The methods are as follows:
- GET: getAllTasks()
  - returns a List of all tasks
- POST: addNewTask(@RequestBody ObjectNode objectNode)
  - returns a ResponsEntity that tells the server if the request was vaild or not
- PUT: updateTask(@PathVariable int id, @RequestBody ToDoTask toDoTask)
  - returns same as above
- DELETE: deleteTask(@PathVariable int id)
  - also return same as above

#### Frontend

The frontend of the app uses JavaFx to create a user friendly user interface for adding, removing and editing a task. The scene was built using Scene Builder. There are three different tabs. One where you can view all current tasks and select one to remove, a second one where you can add new tasks, and a third one where you can edit a task by entering the id of the task you want to edit. The different buttons uses an absrtact class to help declutter the code a bit. For each of the buttons there is a method in the abstract class called "ButtonPressed" that is then implemented in four seperate classes for each of the buttons and then called when the corresponding button is pressed.

### How to run?

There is nothing to install, so all you need to do is 
1. Clone the reposity to a folders on your computer
2. Open the Frontend and Backend folders in two seperate instances of an ide
3. First run the backend and wait for it to fullt start
4. Then run the frontend
5. Then you are good to go!
