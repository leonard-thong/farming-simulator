import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class InitialConfigScreen extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Text box for name
        Label nameLabel = new Label("Username:");
        TextField nameTextField = new TextField ();
        HBox nameHBox = new HBox();
        nameHBox.getChildren().addAll(nameLabel, nameTextField);
        nameHBox.setSpacing(10);

        // Drop down for diff
        Label diffLabel = new Label("Select a difficulty:");
        ObservableList<String> diffs =
                FXCollections.observableArrayList(
                         "Easy",
                                "Normal",
                                "Hard"
                );
        ComboBox diffComboBox = new ComboBox(diffs);
        HBox diffHBox = new HBox();
        diffHBox.getChildren().addAll(diffLabel, diffComboBox);
        diffHBox.setSpacing(10);

        // Drop down for starting seed
        Label seedLabel = new Label("Select a difficulty:");
        ObservableList<String> seeds =
                FXCollections.observableArrayList(
                        "Corn",
                        "Sunflower",
                        "Cauliflower"
                );
        ComboBox seedComboBox = new ComboBox(diffs);
        HBox seedHBox = new HBox();
        seedHBox.getChildren().addAll(diffLabel, diffComboBox);
        seedHBox.setSpacing(10);

        // Drop down for starting season
        Label seasonLabel = new Label("Select a difficulty:");
        ObservableList<String> seasons =
                FXCollections.observableArrayList(
                        "Spring",
                        "Summer",
                        "Fall",
                        "Winter"
                );
        ComboBox seasonComboBox = new ComboBox(diffs);
        HBox seasonHBox = new HBox();
        seasonHBox.getChildren().addAll(diffLabel, diffComboBox);
        seasonHBox.setSpacing(10);

        // Button for next screen
        Button nextScreen = new Button();
        nextScreen.setText("Continue");
        
        
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        VBox root = new VBox();
        root.getChildren().addAll(nameHBox, diffHBox, seedHBox, seasonHBox);

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
