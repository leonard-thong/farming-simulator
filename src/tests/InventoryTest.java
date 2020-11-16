package tests;

import gameobjects.items.crops.Cauliflower;
import gameobjects.items.crops.Corn;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
class InventoryTest {

    @Start
    void onStart(Stage stage) throws Exception {
        Main.getPlayer().getInventory().add(new Corn());
        Main.getPlayer().getInventory().add(new Cauliflower());
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/Inventory.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }
    /**
     * Implemented for M4
     */
    @Test
    void drop(FxRobot robot) {
        robot.clickOn("#inventoryImageView0");
        robot.clickOn("#dropButton");
        assertEquals(Main.getPlayer().getInventory().size(), 1);
    }


}