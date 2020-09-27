package scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.Main;

import java.io.FileNotFoundException;


public class ConfigScene {
    private static Button buttonContinue = new Button();

    public static Button getButtonContinue() {
        return buttonContinue;
    }

    public static Scene getScene() {
        // Getting User Inputs
        // Label and TextField for Name
        Label nameLabel = new Label("USERNAME:");
        TextField nameTextField = new TextField();

        nameLabel.setStyle("-fx-text-fill: White");
        nameLabel.setId("nameLabel");

        // Label Drop Down for Difficulty
        Label diffLabel = new Label("SELECT A DIFFICULTY:");
        ObservableList<String> diffs = FXCollections.observableArrayList(
                "Easy",
                "Normal",
                "Hard");
        ComboBox<String> diffComboBox = new ComboBox<>(diffs);

        diffLabel.setStyle("-fx-text-fill: White");
        diffLabel.setId("diffLabel");
        diffComboBox.setStyle("-fx-pref-width: 250;");
        diffComboBox.setId("diffComboBox");

        // Label and Drop Down for Starting Seed
        Label seedLabel = new Label("SELECT A STARTING SEED:");
        ObservableList<String> seeds = FXCollections.observableArrayList(
                "Corn",
                "Sunflower",
                "Cauliflower");
        ComboBox<String> seedComboBox = new ComboBox<>(seeds);

        seedLabel.setStyle("-fx-text-fill: White");
        seedLabel.setId("seedLabel");
        seedComboBox.setStyle("-fx-pref-width: 250;");
        seedComboBox.setId("seedComboBox");


        // Label and Drop Down for Starting Season
        Label seasonLabel = new Label("SELECT A STARTING SEASON:");
        ObservableList<String> seasons = FXCollections.observableArrayList(
                "Spring",
                "Summer",
                "Fall",
                "Winter");
        ComboBox<String> seasonComboBox = new ComboBox<>(seasons);

        seasonLabel.setStyle("-fx-text-fill: White");
        seasonLabel.setId("seasonLabel");
        seasonComboBox.setStyle("-fx-pref-width: 250;");
        seasonComboBox.setId("seasonComboBox");

        buttonContinue.setText("CONTINUE");

        buttonContinue.setOnAction(event -> {
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
            try {
                Main.getStage().setScene(FarmScene.getScene());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        buttonContinue.setStyle("-fx-pref-width: 200;");

        // Set Grid
        GridPane grid = new GridPane();
        grid.add(nameLabel, 0, 0);
        grid.add(nameTextField, 1, 0);
        grid.add(diffLabel, 0, 1);
        grid.add(diffComboBox, 1, 1);
        grid.add(seedLabel, 0, 2);
        grid.add(seedComboBox, 1, 2);
        grid.add(seasonLabel, 0, 3);
        grid.add(seasonComboBox, 1, 3);

        grid.setAlignment(Pos.CENTER);

        // Scene and Root
        // Set Root
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,
                Insets.EMPTY);
        VBox root = new VBox();
        root.setId("rootvbox");
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(backgroundFill));
        root.getChildren().addAll(grid, buttonContinue);

        // Set Scene
        return new Scene(root);
    }
}
