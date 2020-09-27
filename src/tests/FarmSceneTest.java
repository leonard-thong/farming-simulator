package tests;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import scenes.FarmScene;

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
    void containsButton(FxRobot robot) {

    }

}