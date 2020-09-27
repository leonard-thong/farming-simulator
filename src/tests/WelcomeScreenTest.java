package tests;

import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import scenes.WelcomeScene;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
class WelcomeScreenTest {

    @Start
    void onStart(Stage stage) throws Exception {
        stage.setScene(WelcomeScene.getScene());
        stage.show();
        stage.toFront();
    }

    /**
     * Checks if Start Game button exists to see if the game starts
     *
     * @author Aravind Vengarai S
     * @param robot it interacts with the GUI
     */
    @Test
    void containsButton(FxRobot robot) {
        verifyThat(".button", hasText("Start game"));
        verifyThat(".button", isVisible());
    }
    /**
     * Checks if Background color is black.
     *
     * @author Aravind Vengarai S
     * @param robot it interacts with the GUI
     */
    @Test
    void checksBgColor(FxRobot robot) {
        BorderPane borderpane = robot.lookup("#rootborderpane").query();
        assertEquals(new BackgroundFill(Color.BLACK, null, null),
                borderpane.getBackground().getFills().get(0));
    }
}