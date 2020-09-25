package Screen;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class InitialConfigScreen extends Application {
    public static Player player = new Player();

    @Override
    public void start(Stage stage) throws Exception {
        // Text box for name
        Label nameLabel = new Label("Username:");
        TextField nameTextField = new TextField ();
        HBox nameHBox = new HBox();
        nameHBox.getChildren().addAll(nameLabel, nameTextField);
        nameHBox.setSpacing(10);
        nameTextField.setOnAction(e -> {
            player.setName(nameTextField.getText());
        });

        // Drop down for diff
        Label diffLabel = new Label("Select a difficulty:");
        ObservableList<String> diffs =
                FXCollections.observableArrayList(
                         "Easy",
                                "Normal",
                                "Hard"
                );
        ComboBox<String> diffComboBox = new ComboBox<>(diffs);
        HBox diffHBox = new HBox();
        diffHBox.getChildren().addAll(diffLabel, diffComboBox);
        diffHBox.setSpacing(10);
        diffComboBox.getOnAction();
        diffComboBox.setOnAction(e -> {
            player.setDiff(diffComboBox.getValue());
            switch (diffComboBox.getValue()) {
                case "Easy":
                    player.setMoney(100);
                    break;
                case "Normal":
                    player.setMoney(50);
                    break;
                case "Hard":
                    player.setMoney(25);
                    break;
            }
        });

        // Drop down for starting seed
        Label seedLabel = new Label("Select a starting seed:");
        ObservableList<String> seeds =
                FXCollections.observableArrayList(
                        "Corn",
                        "Sunflower",
                        "Cauliflower"
                );
        ComboBox<String> seedComboBox = new ComboBox<>(seeds);
        HBox seedHBox = new HBox();
        seedHBox.getChildren().addAll(seedLabel, seedComboBox);
        seedHBox.setSpacing(10);
        seedComboBox.setOnAction(e -> player.setStartingSeed(seedComboBox.getValue()));

        // Drop down for starting season
        Label seasonLabel = new Label("Select a starting season:");
        ObservableList<String> seasons =
                FXCollections.observableArrayList(
                        "Spring",
                        "Summer",
                        "Fall",
                        "Winter"
                );
        ComboBox<String> seasonComboBox = new ComboBox<>(seasons);
        HBox seasonHBox = new HBox();
        seasonHBox.getChildren().addAll(seasonLabel, seasonComboBox);
        seasonHBox.setSpacing(10);
        seasonComboBox.setOnAction(e -> player.setSeason(seasonComboBox.getValue()));

        // Button for next screen
        Button nextScreen = new Button();
        nextScreen.setText("Continue");
        nextScreen.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Label secondLabel = new Label("welcome to farm game, the best "
                        + "agriculture-simulation game ever made!!! ");

                StackPane secondaryLayout = new StackPane();
                secondaryLayout.setBackground(new Background(new BackgroundFill(Color.BLACK,
                        CornerRadii.EMPTY, Insets.EMPTY)));
                secondaryLayout.getChildren().add(secondLabel);

                Scene secondScene = new Scene(secondaryLayout, 700, 300);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("FarmSim");
                newWindow.setScene(secondScene);

                // Set position of second window, related to primary window.
                newWindow.setX(stage.getX() + 100);
                newWindow.setY(stage.getY() + 100);

                newWindow.show();
            }
        });
        
        
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        VBox root = new VBox();
        root.getChildren().addAll(nameHBox, diffHBox, seedHBox, seasonHBox, nextScreen);

        Scene scene = new Scene(root, 300, 250);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setTitle("FarmSim");
        stage.setScene(scene);
        stage.show();
    }
}
