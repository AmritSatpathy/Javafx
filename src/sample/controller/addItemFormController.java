package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import sample.Database.DatabaseHandler;
import sample.model.Task;
import sample.model.User;

import java.net.URL;
import java.util.ResourceBundle;


public class addItemFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField taskField;

    @FXML
    private JFXTextField descriptionField;

    @FXML
    private JFXButton saveTaskButton;

    private static int usersid ;

    @FXML
    void initialize() {

        saveTaskButton.setOnAction(event -> {
            createTask();
        });
    }

    public void setUserid(int usersid) {
        this.usersid = usersid;
    }

    public int getUser() {
        return usersid;
    }

    private void createTask(){

        DatabaseHandler databaseHandler = new DatabaseHandler();

        String addtask = taskField.getText();
        String desc = descriptionField.getText();

        Task task = new Task(desc,addtask);
        databaseHandler.storetasks(task,getUser());
    }
}
