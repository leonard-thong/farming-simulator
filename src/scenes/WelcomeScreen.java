import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WelcomeScreen extends Application {

    @Override
    public void start(final Stage primaryStage) {
        //BorderPane root;
        Button button = new Button();
        button.setText("start game");
        Text welcome = new Text("A game by 59minus1");



        VBox vbox = new VBox(8);
        vbox.getChildren().addAll(button, new Text("A game by 59minus1"));
        BorderPane.setAlignment(welcome, Pos.TOP_CENTER);



        // Create a BorderPane with a Text node in each of the five regions
        BorderPane root = new BorderPane(button);
        root.setTop(welcome);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 900, 500);

        primaryStage.setTitle("2340 farm game");
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction(new EventHandler<ActionEvent>() {

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
                newWindow.setTitle("farm game");
                newWindow.setScene(secondScene);

                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX() + 100);
                newWindow.setY(primaryStage.getY() + 100);

                newWindow.show();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}