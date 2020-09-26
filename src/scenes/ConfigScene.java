package scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;


public class ConfigScene {

    public static Scene getScene() {
        // Getting User Inputs
        // Label and TextField for Name
        Label nameLabel = new Label("Username:");
        TextField nameTextField = new TextField();

        HBox nameHBox = new HBox();
        nameHBox.getChildren().addAll(nameLabel, nameTextField);
        nameHBox.setSpacing(10);

        // Label Drop Down for Difficulty
        Label diffLabel = new Label("Select a difficulty:");
        ObservableList<String> diffs = FXCollections.observableArrayList(
                "Easy",
                "Normal",
                "Hard");
        ComboBox<String> diffComboBox = new ComboBox<>(diffs);

        HBox diffHBox = new HBox();
        diffHBox.getChildren().addAll(diffLabel, diffComboBox);
        diffHBox.setSpacing(10);

        // Label and Drop Down for Starting Seed
        Label seedLabel = new Label("Select a starting seed:");
        ObservableList<String> seeds = FXCollections.observableArrayList(
                "Corn",
                "Sunflower",
                "Cauliflower");
        ComboBox<String> seedComboBox = new ComboBox<>(seeds);

        HBox seedHBox = new HBox();
        seedHBox.getChildren().addAll(seedLabel, seedComboBox);
        seedHBox.setSpacing(10);

        // Label and Drop Down for Starting Season
        Label seasonLabel = new Label("Select a starting season:");
        ObservableList<String> seasons = FXCollections.observableArrayList(
                "Spring",
                "Summer",
                "Fall",
                "Winter");
        ComboBox<String> seasonComboBox = new ComboBox<>(seasons);

        HBox seasonHBox = new HBox();
        seasonHBox.getChildren().addAll(seasonLabel, seasonComboBox);
        seasonHBox.setSpacing(10);

        // Button to continue
        Button buttonContinue = new Button();
        buttonContinue.setText("Continue");

        buttonContinue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.getPlayer().setName(nameTextField.getText());
                Main.getPlayer().setDiff(diffComboBox.getValue());
                Main.getPlayer().setSeed(seedComboBox.getValue());
                Main.getPlayer().setSeason(seasonComboBox.getValue());
                if ("Easy".equals(Main.getPlayer().getDiff())) {
                    Main.getPlayer().setMoney(100);
                } else if ("Normal".equals(Main.getPlayer().getDiff())) {
                    Main.getPlayer().setMoney(50);
                } else if ("Hard".equals(Main.getPlayer().getDiff())) {
                    Main.getPlayer().setMoney(25);
                }
                // Show next scene
                // Main.getStage().setScene(//farmui);
            }
        });


        // Stage, Scene, and Root
        // Set Root
        VBox root = new VBox();
        root.getChildren().addAll(nameHBox, diffHBox, seedHBox, seasonHBox, buttonContinue);

        // Set Scene
        return new Scene(root);
    }
}
