package main;

import javafx.application.Application;
import javafx.stage.Stage;
import gameobjects.Player;
import scenes.ConfigScene;

public class Main extends Application {
    private static Stage stage = new Stage();
    private static Player player = new Player();

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("FarmSim");
        stage.setScene(ConfigScene.getScene());
        stage.setMaximized(true);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void main(String[] args) {
        launch(args);
    }
}