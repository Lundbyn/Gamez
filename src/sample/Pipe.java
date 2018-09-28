package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Pipe {

    private final int width = 40;
    private int height;
    private final Color color = Color.GREEN;
    private Rectangle upper;
    private Rectangle lower;

    public Pipe(Pane pipePane, int tick) {
        Random r = new Random();

        height = r.nextInt(700) + 50;

        upper = new Rectangle(width,height,color);
        upper.setTranslateY(0);
        upper.setTranslateX(1200 + (tick * 2));

        lower = new Rectangle(width, 800, color);
        lower.setTranslateY(height + 100);
        lower.setTranslateX(1200 + (tick * 2));

        pipePane.getChildren().addAll(upper, lower);
    }
}
