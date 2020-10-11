package tests;

import gameobjects.items.crops.Cauliflower;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import scenes.FarmScene;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
class MarketTest {

    @Start
    void onStart(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/Market.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }

    @Test
    void buy(FxRobot robot) {
        robot.clickOn("#marketImageView0");
        robot.clickOn("#buyButton");
        assertEquals(Main.getPlayer().getInventory().size(), 1);
        assertEquals(Main.getPlayer().getInventory().get(0), new Cauliflower());
    }

    @Test
    void labelsExist(){
        verifyThat("#moneyLabel", hasText("50"));
        verifyThat("#moneyLabel", isVisible());
    }

//    @Test
//    void sell(FxRobot robot){
//        Main.getPlayer().getInventory().add(new Cauliflower());
//        robot.clickOn("#");
//    }


}