package scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import main.Main;

public class WelcomeScene {


    public static Scene getScene() {
        Button button = new Button();
        button.setText("Start game");

        BorderPane root = new BorderPane(button);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));

        button.setOnAction(event -> Main.getStage().setScene(ConfigScene.getScene()));
        return new Scene(root);
    }

}
