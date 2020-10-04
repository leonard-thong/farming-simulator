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

public class InventoryController extends Application {
    Item[] inventory = Main.getPlayer().getInventory();

    @Override
    public void start(Stage inventoryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/Inventory.fxml"));

        Scene scene = new Scene(root);
        Stage stage = inventoryStage;
        stage.setTitle("Inventory");
        stage.setScene(scene);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        stage.show();

        for (Item i : inventory) {

        }
    }
}
