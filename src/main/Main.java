package main;

// import JavaFX

import javafx.application.Application;
import javafx.stage.Stage;
import gameobjects.Player;
import scenes.ConfigScene;
import scenes.WelcomeScreen;

// import package

public class Main extends Application {
    private static Stage stage = new Stage();
    private static Player player = new Player();

    public static Stage getStage() {
        return stage;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FarmSim");
        primaryStage.setScene(WelcomeScreen.getScene());
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}