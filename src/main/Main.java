package main;

import gameobjects.Player;
import javafx.application.Application;
import javafx.stage.Stage;
import scenes.ConfigScene;

public class Main extends Application {
    private static Stage stage = new Stage();
    private static Player player = new Player();

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static Player getPlayer() {
        return player;
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("FarmSim");
        primaryStage.setScene(ConfigScene.getScene());
        primaryStage.show();
    }
}