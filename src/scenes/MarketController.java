package scenes;

import gameobjects.items.Item;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.Main;

public class MarketController extends Application {
    Item[] market = new Item[25];

    @Override
    public void start(Stage marketStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/Market.fxml"));

        Scene scene = new Scene(root);
        Stage stage = marketStage;
        stage.setTitle("Market");
        stage.setScene(scene);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        stage.show();
    }
}
