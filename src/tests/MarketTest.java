package tests;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
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
import org.testfx.matcher.control.ListViewMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
class MarketTest {

    @Start
    void random(Stage stage) throws Exception {
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
        assertEquals(Main.getPlayer().getInventory().get(0).getType(), "Cauliflower");
    }

    @Test
    void labelsExist() {
        verifyThat("#moneyLabel", hasText("123122748"));
        verifyThat("#moneyLabel", isVisible());
    }

    @Test
    void sell(FxRobot robot) {
        verifyThat("#inventoryList", ListViewMatchers.hasItems(1));
        robot.clickOn("#inventoryList");
        robot.push(KeyCode.ENTER);
        robot.clickOn("#sellButton");
        verifyThat("#inventoryList", ListViewMatchers.hasItems(0));
    }

    @Test
    void buyV2(FxRobot robot) {
        Main.getPlayer().setMoney(123123123);
        for (int i = 0; i < 25; i++) {
            robot.clickOn("#marketImageView0");
            robot.clickOn("#buyButton");
        }
        verifyThat("#inventoryList", ListViewMatchers.hasItems(25));
        robot.clickOn("#marketImageView0");
        robot.clickOn("#buyButton");
    }

    @Test
    void marketBGColor(FxRobot robot) {
        BorderPane pane = robot.lookup("#marketBGColor").query();
        assertEquals(new BackgroundFill(Color.BLACK, null, null),
                pane.getBackground().getFills().get(0));
    }
    @Test
    void checksItemsInMarket(FxRobot robot) {
        GridPane grid = robot.lookup("#marketPane").query();
        boolean chk = false;
        for (Node child : grid.getChildren()) {
            if (child instanceof ImageView) {
                chk = true;
                break;
            }
        }
        assertTrue(chk);
    }

}