package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.collections.Shaker;
import sample.model.Task;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField loginusername;

    @FXML
    private JFXPasswordField loginpassword;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton loginSignupButton;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();

        loginButton.setOnAction(event -> {


            String loginText = loginusername.getText().trim();
            String loginPWD = loginpassword.getText().trim();

            User user = new User();
            user.setUserName(loginText);
            user.setPassword(loginPWD);

            ResultSet resultSet = databaseHandler.getUsers(user);


            int counter = 0;
            int userid = 0;
            int counter1 = 0;


            try {
                while(resultSet.next()){
                    counter++;
                    userid = resultSet.getInt("usersid");

                }
                if (counter == 1){
                    if(userid >=1){

                        ResultSet resultSet1 = databaseHandler.getTask(userid);
                        while(resultSet1.next()){
                            counter1++;
                        }
                        if (counter1 == 0) {
                            showAddItemScreen(userid);
                        }
                        else{
                            showAllItem(userid);
                        }
                    }


                }else {
                    Shaker shaker = new Shaker(loginusername);
                    Shaker shaker1 = new Shaker(loginpassword);
                    shaker.shake();
                    shaker1.shake();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }

        });

        loginSignupButton.setOnAction(event -> {
            //users to signup screen
            loginSignupButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource( "/sample/view/signup.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });


    }

    private void showAddItemScreen(int usersid){

        //Take users to AddItem Screen
        FXMLLoader loader = new FXMLLoader();
        loginSignupButton.getScene().getWindow().hide();

        loader.setLocation(getClass().getResource( "/sample/view/addItem.fxml"));
        addItemFormController controller = new addItemFormController();
        controller.setUserid(usersid);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public  void showAllItem(int userid){

        //Take users to AddItem Screen
        loginSignupButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource( "/sample/view/showitem.fxml"));
        ResultSet resultSet1 = databaseHandler.getTask(userid);

        try{
            ObservableList<Task> dis = getObjet(resultSet1);
            allTasks controller = new allTasks();
            controller.setdata(dis);


        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }

    private static ObservableList<Task> getObjet(ResultSet resu) throws ClassNotFoundException ,SQLException{

        try{
            ObservableList<Task> listt  = FXCollections.observableArrayList();
            while(resu.next()){
                listt.add(new Task(resu.getString("description"),resu.getString("task")));
            }
            return listt;
        }catch(SQLException e){
            e.printStackTrace();
            throw e;
        }

    }

}
