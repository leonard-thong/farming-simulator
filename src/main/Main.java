package main;

import gameobjects.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


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
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/scenes/WelcomeScene.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/scenes/Inventory.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/ConfigScene.fxml"));

        Scene scene = new Scene(root);
        stage = primaryStage;
        stage.setTitle("FarmSim");
        stage.setScene(scene);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setWidth(1080);
        stage.setHeight(800);

        stage.show();
    }

}