package tests;

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
class FarmSceneTest {

    @Start
    void onStart(Stage stage) throws Exception {
        stage.setScene(FarmScene.getScene());
        stage.show();
        stage.toFront();
    }

    @Test
    void containsTexts() {
        verifyThat("#coinslabel", hasText(String.valueOf(Main.getPlayer().getMoney())));
        verifyThat("#coinslabel", isVisible());
        verifyThat("#daytext", hasText("Day " + Main.getPlayer().getDay()));
        verifyThat("#daytext", isVisible());
    }

    @Test
    void checksPlot(FxRobot robot) {
        GridPane grid = robot.lookup("#plotgrid").query();
        assertEquals(5, grid.getRowCount());
        assertEquals(5, grid.getColumnCount());
    }

    /**
     * Checks if the image of coins is displayed next to the Player's money
     *
     * @author Hemang Dash
     */
    @Test
    void checksCoinImage() {
        verifyThat("#coinsimage", isVisible());
    }

    /**
     * Checks if the background color of the Farm Scene UI is black
     *
     * @author Hemang Dash
     * @param robot it interacts with the GUI
     */
    @Test
    void checksBgColor(FxRobot robot) {
        VBox vbox = robot.lookup("#rootvbox").query();
        assertEquals(new BackgroundFill(Color.BLACK, null, null),
                vbox.getBackground().getFills().get(0));
    }
}