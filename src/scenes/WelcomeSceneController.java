package scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import main.Main;

import java.io.IOException;

public class WelcomeSceneController {

    @FXML
    public void startGame(ActionEvent e) throws IOException {
        Main.getStage().setScene(new Scene(
                FXMLLoader.load(getClass().getResource("/scenes/ConfigScene.fxml"))));
    }

}
