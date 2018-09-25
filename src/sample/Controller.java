package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    AnchorPane mainPane;
    @FXML
    Pane UIpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Game started");

    }
}
