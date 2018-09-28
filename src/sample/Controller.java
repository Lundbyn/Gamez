package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML AnchorPane mainPane;
    @FXML Pane pipePane;

    AnimationTimer timer;

    private int tick;
    Pipe pipe;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Game started");

        tick = 0;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                pipePane.setLayoutX(pipePane.getLayoutX() - 2);
                tick++;
                if(tick % 150 == 0) {
                    pipe = new Pipe(pipePane, tick, LISTE);
                }

            }
        };
        timer.start();


    }
}
