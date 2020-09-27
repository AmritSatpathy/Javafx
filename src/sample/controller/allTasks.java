package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.model.Task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class allTasks {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Task> view;

    @FXML
    private TableColumn<Task,String> task;

    @FXML
    private TableColumn<Task,String> desc;


    private static ObservableList<Task> usersid ;

    public void setdata(ObservableList<Task> q) {
        this.usersid = q;
    }

    public ObservableList<Task> getRes() {
        return usersid;
    }

    @FXML
    void initialize() {

        ObservableList<Task> dis = getRes();
        task.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        desc.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
        view.setItems(dis);


    }
}

