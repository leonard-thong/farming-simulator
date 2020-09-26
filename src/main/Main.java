package main;

// import JavaFX
import javafx.application.Application;
import javafx.stage.Stage;

// import package
import objects.Player;
import scenes.ConfigScene;

public class Main extends Application {
    private static Stage stage = new Stage();
    private static Player player = new Player();

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

    public static void main(String[] args) {
        launch(args);
    }
}