package main;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

import gameobjects.Player;
import scenes.WelcomeScene;


public class Main extends Application {
    private static Stage stage = new Stage();
    private static Player player = new Player();

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("FarmSim");
        stage.setScene(WelcomeScene.getScene());

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

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