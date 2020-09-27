package sample.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController {

    @FXML
    private AnchorPane rootanchorpane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addbutton;

    @FXML
    private Label notasks;

    @FXML
    void initialize() {

        addbutton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000),addbutton);

            addbutton.relocate(0, 10);
            notasks.relocate(0, 45);

            addbutton.setOpacity(0);
            notasks.setOpacity(0);

            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(2);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();

            try {

                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));
                rootanchorpane.getChildren().setAll(formPane);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }


}
