package scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.Main;

public class WelcomeScreen {


    public static Scene getScene() {
        //BorderPane root;
        Button button = new Button();
        button.setText("Start game");
        //Text welcome = new Text("A game by 59minus1");


        //VBox vbox = new VBox(8);
        //vbox.getChildren().addAll(button, new Text("A game by 59minus1"));
        //BorderPane.setAlignment(welcome, Pos.TOP_CENTER);


        // Create a BorderPane with a Text node in each of the five regions

        BorderPane root = new BorderPane(button);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));

        button.setOnAction(event -> Main.getStage().setScene(ConfigScene.getScene()));
        return new Scene(root);
    }

}
