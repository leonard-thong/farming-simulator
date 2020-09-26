package main;

import gameobjects.Player;
import javafx.application.Application;
import javafx.stage.Stage;
import scenes.WelcomeScreen;

public class Main extends Application {
    private static Stage stage = new Stage();
    private static Player player = new Player();

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("FarmSim");
        stage.setScene(WelcomeScreen.getScene());
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