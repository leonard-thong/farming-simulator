package tests;

import javafx.scene.Node;
import javafx.scene.control.Label;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    /**
     * Checks to see if the money and day values are being displayed correctly on the screen
     *
     * @author Pranav Thomas
     */
    @Test
    void containsTexts() {
        verifyThat("#coinslabel", hasText(String.valueOf(Main.getPlayer().getMoney())));
        verifyThat("#coinslabel", isVisible());
        verifyThat("#daytext", hasText("Day " + Main.getPlayer().getDay()));
        verifyThat("#daytext", isVisible());
    }

    /**
     * Checks to see if the farm plot has been created with the appropriate dimensions
     *
     * @param robot interacts with the GUI
     * @author Pranav Thomas
     */
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
     * @param robot interacts with the GUI
     * @author Hemang Dash
     */
    @Test
    void checksBgColor(FxRobot robot) {
        VBox vbox = robot.lookup("#rootvbox").query();
        assertEquals(new BackgroundFill(Color.BLACK, null, null),
                vbox.getBackground().getFills().get(0));
    }
    /**
     * Implemented for M4
     */
    @Test
    void checksCrops(FxRobot robot) {
        GridPane grid = robot.lookup("#plotgrid").query();
        boolean chk = false;
        for (Node child : grid.getChildren()) {
            if (child instanceof ImageView) {
                chk = true;
                break;
            }
        }
        assertTrue(chk);

    }
    /**
     * Implemented for M4
     */
    @Test
    void advanceDay(FxRobot robot) {
        robot.clickOn("#advanceDayButton");
        assertEquals(Main.getPlayer().getDay(), 2);
    }
    /**
     * Implemented for M4
     */
    @Test
    void checksPlotInFarmIsEmptyAtStart(FxRobot robot) {
        GridPane grid = robot.lookup("#plotgrid").query();
        boolean chk = false;
        for (Node child : grid.getChildren()) {
            if (child instanceof Label) {
                chk = true;

            } else {
                chk = false;
                break;
            }
        }
        assertTrue(chk);
    }
}