package scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import main.Main;

import java.io.IOException;

public class EndSceneController {
    @FXML
    private Button restartButton;

    @FXML
    public void restart() throws IOException {
        Main.getStage().setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/scenes/ConfigScene.fxml"))));
    }
}
