package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML AnchorPane mainPane;
    @FXML Label txtPreGame;
    @FXML Label txtPoints;

    AnimationTimer timer;

    private int tick, pts;
    private Pipe pipe;
    private ArrayList<Rectangle> pipeList = new ArrayList<>();
    private ArrayList<Pipe> pipes = new ArrayList<>();
    private Bird bird;
    private boolean started,alive;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Game started");

        bird = new Bird(mainPane);
        started = false;
        alive = true;
        pts = 0;


        tick = 0;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mainPane.requestFocus();
                tick++;
                if(tick % 150 == 0 && started && alive) {
                    pipe = new Pipe(mainPane, pipeList);
                    pipes.add(pipe);
                    bird.getCircle().toFront();
                }
                testCollision();
                buttonClicked();
                updatePts();
            }
        };
        timer.start();
    }

    private void testCollision() {
        for(Rectangle pipe : pipeList) {
            if(pipe.getBoundsInParent().intersects(bird.getCircle().getBoundsInParent())) {
                killBird();
            }
        }
        if(bird.getCircle().getTranslateY() > 800 || bird.getCircle().getTranslateY() < 0) {
            killBird();
        }
    }

    private void updatePts() {
        for(Pipe pipe : pipes) {
            if(bird.getCircle().getTranslateX() > pipe.getUpper().getTranslateX() && pipe.isActive()) {
                pts++;
                pipe.setActive(false);
                txtPoints.setText("Points: " + pts);
            }
            txtPoints.toFront();
        }
    }

    private void killBird() {
        bird.die();
        stopPipes();
        alive = false;
        txtPreGame.setText("Press 'R' to try again");
        txtPreGame.toFront();
        txtPreGame.setVisible(true);
    }

    private void stopPipes() {
        for(Pipe pipe : pipes) {
            pipe.getTimer().stop();
        }
    }

    private void buttonClicked() {
        mainPane.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.SPACE) {
                bird.jump();
                started = true;
                if(alive) {
                    txtPreGame.setVisible(false);
                }
            }

            if(e.getCode() == KeyCode.R) {
                restartGame();
            }
        });
    }

    private void restartGame() {
        mainPane.getChildren().removeAll(pipeList);
        pipeList.clear();

        mainPane.getChildren().remove(bird.getCircle());
        bird = null;
        bird = new Bird(mainPane);
        pts = 0;
        txtPoints.setText("Points: " + pts);

        alive = true;
        bird.setCanJump(true);
        txtPreGame.setVisible(false);
    }
}
