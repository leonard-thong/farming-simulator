package tests;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Main;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.ComboBoxMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;
import scenes.ConfigScene;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
class ConfigSceneTest {

    @Start
    void onStart(Stage stage) throws Exception {
        stage.setScene(ConfigScene.getScene());
        stage.show();
        stage.toFront();
    }

    @Test
    void testDiffBox(FxRobot robot) {
        verifyThat("#diffComboBox", ComboBoxMatchers.containsItems("Easy", "Normal", "Hard"));
        robot.clickOn("#diffComboBox").press(KeyCode.DOWN);
        verifyThat("#diffComboBox", ComboBoxMatchers.hasSelectedItem("Easy"));
    }

    @Test
    void testSeedBox(FxRobot robot) {
        verifyThat("#seedComboBox",
                ComboBoxMatchers.containsItems("Corn", "Sunflower", "Cauliflower"));
        robot.clickOn("#seedComboBox").press(KeyCode.DOWN);
        verifyThat("#seedComboBox", ComboBoxMatchers.hasSelectedItem("Corn"));
    }

    @Test
    void testSeasonBox(FxRobot robot) {
        verifyThat("#seasonComboBox",
                ComboBoxMatchers.containsItems("Spring", "Summer", "Fall", "Winter"));
        robot.clickOn("#seasonComboBox").press(KeyCode.DOWN);
        verifyThat("#seasonComboBox", ComboBoxMatchers.hasSelectedItem("Spring"));
    }

    @Test
    void testTextField(FxRobot robot) {
        robot.clickOn(".text-field");
        robot.write("Jayant");
        verifyThat(".text-field", TextInputControlMatchers.hasText("Jayant"));
    }

    @Test
    void containsLabels() {
        verifyThat("#nameLabel", hasText("USERNAME:"));
        verifyThat("#nameLabel", isVisible());
        verifyThat("#diffLabel", hasText("SELECT A DIFFICULTY:"));
        verifyThat("#diffLabel", isVisible());
        verifyThat("#seedLabel", hasText("SELECT A STARTING SEED:"));
        verifyThat("#seedLabel", isVisible());
        verifyThat("#seasonLabel", hasText("SELECT A STARTING SEASON:"));
        verifyThat("#seasonLabel", isVisible());
    }

    @Test
    void testButton(FxRobot robot) {
        robot.clickOn(".text-field");
        robot.write("Jayant");
        robot.clickOn("#diffComboBox");
        robot.push(KeyCode.DOWN, KeyCode.ENTER);
        robot.clickOn("#seedComboBox");
        robot.push(KeyCode.DOWN, KeyCode.ENTER);
        robot.clickOn("#seasonComboBox");
        robot.push(KeyCode.DOWN, KeyCode.ENTER);


        verifyThat(".button", hasText("CONTINUE"));
        robot.clickOn(".button");
        assertEquals("Jayant", Main.getPlayer().getName());
        assertEquals("Easy", Main.getPlayer().getDiff());
        assertEquals(100, Main.getPlayer().getMoney());
        assertEquals("Corn", Main.getPlayer().getStartingSeed());
        assertEquals("Spring", Main.getPlayer().getSeason());
    }

    @Test
    void checksBgColor(FxRobot robot) {
        VBox vbox = robot.lookup("#rootvbox").query();
        Assert.assertEquals(new BackgroundFill(Color.BLACK, null, null),
                vbox.getBackground().getFills().get(0));
    }
}