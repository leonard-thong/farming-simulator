package scenes;

import gameobjects.Farm;
import gameobjects.Plot;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.Main;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FarmScene {
    private static Farm farm;
//    private static Plot[] farm.getFarm() = new Plot[25];
    private static boolean built = false;

    public static void farmSceneInit() {
        farm = new Farm();
    }

    public static Plot[] getFarm() {
        return farm.getFarm();
    }


    public static Scene getScene() throws FileNotFoundException {
        if (!built) {
            farmSceneInit();
            built = true;
        }
        
        Label coins = new Label("" + Main.getPlayer().getMoney());
        coins.setFont(new Font("Ubuntu", 25));
        coins.setTextFill(Color.GOLD);
        coins.setId("coinslabel");

        Image coinsPic = new Image("/images/coins.png");
        ImageView coinsPicture = new ImageView(coinsPic);
        coinsPicture.setFitHeight(35);
        coinsPicture.setFitWidth(35);
        coinsPicture.setId("coinsimage");

        HBox money = new HBox();
        money.setPadding(new Insets(10));
        money.getChildren().addAll(coins, coinsPicture);

        Label date = new Label("Day " + Main.getPlayer().getDay());
        date.setFont(Font.font("Ubuntu", FontWeight.BOLD, FontPosture.REGULAR, 40));
        date.setTextFill(Color.GREEN);
        date.setTextAlignment(TextAlignment.CENTER);
        date.setId("daytext");

        StackPane info = new StackPane();
        info.getChildren().addAll(money, date);
        StackPane.setAlignment(date, Pos.TOP_CENTER);

        GridPane gridpane = new GridPane();
        gridpane.setId("plotgrid");
        for (int i = 0; i < 25; i++) {
            int finalI = i;
            if (farm.getFarm()[i].getPlotImage() != null) {
                farm.getFarm()[i].getPlotImage().setOnMouseClicked(event -> {
                    if (farm.getFarm()[finalI].getCrop().getLifeStage() == 3) {
                        if (Main.getPlayer().getInventory().size() == 25) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Not enough space in inventory!");
                            alert.show();
                            return;
                        }

                        farm.getFarm()[finalI].setCrop(null);
                        farm.getFarm()[finalI].setPlotImage(null);
                        farm.getFarm()[finalI].setWaterLevel(0);

                        Main.getPlayer().getInventory().add(farm.getFarm()[finalI].getCrop());
                        Alert nameAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        nameAlert.setHeaderText("Congratulations! You just harvested a crop!");
                        nameAlert.setTitle("Successfully Harvested!");
                        nameAlert.show();
                        try {
                            Main.getStage().setScene(FarmScene.getScene());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Alert nameAlert = new Alert(Alert.AlertType.ERROR);
                        nameAlert.setHeaderText("The crop still needs to grow");
                        nameAlert.setTitle("Cannot Harvest!");
                        nameAlert.show();
                    } // change functionality
                });
            }
        }
        gridpane.setMaxSize(750, 600);
        int tracker = -1;
        for (int i = 0; i < 5; i++) {
            gridpane.addRow(i,
                    (farm.getFarm()[++tracker].getPlotImage() != null
                            ? farm.getFarm()[tracker].getPlotImage() : new Label("" + (tracker + 1))),
                    (farm.getFarm()[++tracker].getPlotImage() != null
                            ? farm.getFarm()[tracker].getPlotImage() : new Label("" + (tracker + 1))),
                    (farm.getFarm()[++tracker].getPlotImage() != null
                            ? farm.getFarm()[tracker].getPlotImage() : new Label("" + (tracker + 1))),
                    (farm.getFarm()[++tracker].getPlotImage() != null
                            ? farm.getFarm()[tracker].getPlotImage() : new Label("" + (tracker + 1))),
                    (farm.getFarm()[++tracker].getPlotImage() != null
                            ? farm.getFarm()[tracker].getPlotImage() : new Label("" + (tracker + 1)))
            );
        }
        gridpane.setAlignment(Pos.CENTER);
        for (Node cell : gridpane.getChildren()) {
            if (cell instanceof Control) {
                javafx.scene.control.Control control = (Control) cell;
                control.setPrefSize(150, 100);
                control.setStyle("-fx-background-color: saddlebrown; -fx-alignment: center; -fx-text-fill: white");
            }
        }
        gridpane.setStyle("-fx-background-color: darkgreen; -fx-padding: 2; -fx-hgap: 5; -fx-vgap: 5;");
        gridpane.setSnapToPixel(false);

        ColumnConstraints oneThird = new ColumnConstraints();
        oneThird.setPercentWidth(100 / 5.0);
        oneThird.setHalignment(HPos.CENTER);
        gridpane.getColumnConstraints().addAll(oneThird, oneThird, oneThird, oneThird, oneThird);
        RowConstraints oneHalf = new RowConstraints();
        oneHalf.setPercentHeight(100 / 5.0);
        oneHalf.setValignment(VPos.CENTER);
        gridpane.getRowConstraints().addAll(oneHalf, oneHalf, oneHalf, oneHalf, oneHalf);

        StackPane farm = new StackPane();
        farm.getChildren().addAll(gridpane);
        StackPane.setAlignment(gridpane, Pos.CENTER);

        Label empty1 = new Label();
        empty1.setPrefSize(1000, 70);

        Label empty2 = new Label();
        empty2.setPrefSize(1000, 345);

        Button btnInventory = new Button();
        btnInventory.setText("Inventory");
        btnInventory.setOnAction(event -> {
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(FarmScene.class.getResource("/scenes/Inventory.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(root));
            stage.show();
        });

        Button btnMarket = new Button();
        btnMarket.setText("Market");
        btnMarket.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(FarmScene.class.getResource("/scenes/Market.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getStage().setScene(new Scene(root));
        });

        Button advanceDay = new Button();
        advanceDay.setText("Advance Day");
        advanceDay.setOnAction(event -> {
            Main.getPlayer().setDay(Main.getPlayer().getDay() + 1);
            try {
                Main.getStage().setScene(FarmScene.getScene());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        StackPane buttons = new StackPane();
        buttons.getChildren().addAll(btnInventory, advanceDay, btnMarket);
        StackPane.setAlignment(btnInventory, Pos.TOP_LEFT);
        StackPane.setAlignment(advanceDay, Pos.TOP_CENTER);
        StackPane.setAlignment(btnMarket, Pos.TOP_RIGHT);

        VBox root = new VBox();
        root.setId("rootvbox");
        root.getChildren().addAll(info, empty1, farm, empty2, buttons);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        return new Scene(root, 1000, 750, Color.BLACK);
    }
}
