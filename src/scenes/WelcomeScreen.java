package scenes;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;
import scenes.ConfigScene;

public class WelcomeScreen {


    public static Scene getScene() {
        //BorderPane root;
        Button button = new Button();
        button.setText("Start game");
        Text welcome = new Text("A game by 59minus1");


        //VBox vbox = new VBox(8);
        //vbox.getChildren().addAll(button, new Text("A game by 59minus1"));
        //BorderPane.setAlignment(welcome, Pos.TOP_CENTER);


        // Create a BorderPane with a Text node in each of the five regions

        BorderPane root = new BorderPane(button);
        root.setTop(welcome);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));

        button.setOnAction(event -> Main.getStage().setScene(ConfigScene.getScene()));
        return new Scene(root);
    }

}
