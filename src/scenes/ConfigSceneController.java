package scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.Main;


import javafx.event.ActionEvent;
import java.io.FileNotFoundException;


public class ConfigSceneController {
    @FXML private TextField nameTextField;
    @FXML private ComboBox<String> seasonComboBox;
    @FXML private ComboBox<String> seedComboBox;
    @FXML private ComboBox<String> diffComboBox;
    @FXML private Label seedLabel;
    @FXML private Label nameLabel;
    @FXML private Label diffLabel;
    @FXML Button buttonContinue;

    @FXML public void nextScene(ActionEvent e) {
        if (nameTextField.getText() != null) {
            Main.getPlayer().setName(nameTextField.getText());
        }
        if (seedComboBox.getValue() != null) {
            Main.getPlayer().setSeed(seedComboBox.getValue());
        }
        if (seedComboBox.getValue() != null) {
            Main.getPlayer().setSeason(seasonComboBox.getValue());
        }

        if (diffComboBox.getValue() != null) {
            Main.getPlayer().setDiff(diffComboBox.getValue());
            if ("Easy".equals(Main.getPlayer().getDiff())) {
                Main.getPlayer().setMoney(100);
            } else if ("Hard".equals(Main.getPlayer().getDiff())) {
                Main.getPlayer().setMoney(25);
            }
        }

        // Show next scene
        try {
            if (!nameTextField.getText().equals("")) {
                Main.getStage().setScene(FarmScene.getScene());
            } else {
                Alert nameAlert = new Alert(Alert.AlertType.ERROR);
                nameAlert.setHeaderText("Please enter a username to continue");
                nameAlert.setTitle("Invalid Username");
                nameAlert.show();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
