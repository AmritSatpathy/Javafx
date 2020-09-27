package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import sample.Database.DatabaseHandler;
import sample.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField singUpFirstName;

    @FXML
    private JFXTextField singUpLastName;

    @FXML
    private JFXTextField singUpUserName;

    @FXML
    private JFXCheckBox singUpMale;

    @FXML
    private JFXCheckBox singUpFemale;

    @FXML
    private JFXPasswordField singUpPassword;

    @FXML
    private JFXButton singUpButton;

    @FXML
    void initialize() {

        singUpButton.setOnAction(event -> {
            createUser();
        });


    }

    private void createUser(){

        DatabaseHandler databaseHandler = new DatabaseHandler();

        String name = singUpFirstName.getText();
        String last = singUpLastName.getText();
        String username = singUpUserName.getText();
        String password = singUpPassword.getText();
        String gender;
        if (singUpMale.isSelected()){
            gender = "Male";
        }else{
            gender = "Female";
        }

        User user = new User(name,last,username,password,gender);

        databaseHandler.signUpUser(user);
    }


}
