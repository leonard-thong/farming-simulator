package scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
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


public class ConfigScene {
    public static Scene getScene() {
        // Getting User Inputs
        // Label and TextField for Name
        Label nameLabel = new Label("Username:");
        TextField nameTextField = new TextField();

        // Label Drop Down for Difficulty
        Label diffLabel = new Label("Select a difficulty:");
        ObservableList<String> diffs = FXCollections.observableArrayList("Easy", "Normal", "Hard");
        ComboBox<String> diffComboBox = new ComboBox<>(diffs);

        // Label and Drop Down for Starting Seed
        Label seedLabel = new Label("Select a starting seed:");
        ObservableList<String> seeds = FXCollections.observableArrayList("Corn", "Sunflower", "Cauliflower");
        ComboBox<String> seedComboBox = new ComboBox<>(seeds);

        // Label and Drop Down for Starting Season
        Label seasonLabel = new Label("Select a starting season:");
        ObservableList<String> seasons = FXCollections.observableArrayList("Spring", "Summer", "Fall", "Winter");
        ComboBox<String> seasonComboBox = new ComboBox<>(seasons);

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
                // Main.getStage().setScene(//FarmUI);
            }
        });

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
        BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(background_fill));
        root.getChildren().addAll(grid, buttonContinue);

        // Set Scene
        return new Scene(root);
    }
}
