package scenes;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import main.Main;

import java.awt.event.ActionEvent;

public class WelcomeScene {

    @FXML public void startGame(ActionEvent e) {
        Main.getStage().setScene(ConfigScene.getScene());
    }
}
