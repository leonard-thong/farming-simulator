package scenes;

import gameobjects.Farm;
import gameobjects.Plot;
import gameobjects.items.tools.Fertilizer;
import gameobjects.items.tools.Pesticide;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import main.Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public class FarmScene {
    private static Farm farm;
    private static boolean built = false;

    // TAKE FROM HERE
    //  farm.getFarm()[i].getPlotImage().setOnMouseDragOver(event -> {
    //      Tooltip tooltip = new Tooltip();
    //      ImageView imageView = new ImageView("https://cdn.sstatic.net/Sites/stackoverflow/company/img/logos/so/so-logo.png?v=9c558ec15d8a");
    //      imageView.setPickOnBounds(true);
    //      Tooltip.install(imageView, tooltip);
    //  });
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
        FarmScene.harvest();
        gridpane.setMaxSize(750, 600);
        FarmScene.buildFarm(gridpane);
        gridpane.setAlignment(Pos.CENTER);
        for (Node cell : gridpane.getChildren()) {
            if (cell instanceof Control) {
                javafx.scene.control.Control control = (Control) cell;
                control.setPrefSize(150, 100);
                control.setStyle(
                        "-fx-background-color: saddlebrown;"
                                + " -fx-alignment: center; -fx-text-fill: white");
            }
        }
        gridpane.setStyle(
                "-fx-background-color: darkgreen; -fx-padding: 2; -fx-hgap: 5; -fx-vgap: 5;");
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
            Parent root = null;
            try {
                root = FXMLLoader.load(FarmScene.class.getResource("/scenes/Inventory.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getStage().setScene(new Scene(root));
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
        Button btnAdvanceDay = new Button();
        btnAdvanceDay.setId("advanceDayButton");
        btnAdvanceDay.setText("Advance Day");
        btnAdvanceDay.setOnAction(event -> advanceDay());
        Button btnWater = new Button();
        btnWater.setId("waterButton");
        btnWater.setText("Water");
        btnWater.setOnAction(event -> water());
        Button btnPesticide = new Button();
        btnPesticide.setId("addPesticideButton");
        btnPesticide.setText("Pesticide");
        btnPesticide.setOnAction(event -> {
            int index = -1;
            for (int i = 0; i < Main.getPlayer().getInventory().size(); i++) {
                if (Main.getPlayer().getInventory().get(i) instanceof Pesticide) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                int num = addPesticide();
                if (num != -1) {
                    Main.getPlayer().getInventory().remove(index);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("You do not have any pesticides! "
                        + "Buy pesticide from the market to use it.");
                alert.show();
            }
        });
        Button btnFertilizer = new Button();
        btnFertilizer.setId("fertilizerButton");
        btnFertilizer.setText("Fertilizer");
        btnFertilizer.setOnAction(event -> {
                int index = -1;
                for (int i = 0; i < Main.getPlayer().getInventory().size(); i++) {
                    if (Main.getPlayer().getInventory().get(i) instanceof Fertilizer) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    boolean var = addFertilizer();
                    if (var) {
                        Main.getPlayer().getInventory().remove(index);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("You do not have any fertilizers! "
                            + "Buy fertilizer from the market to use it.");
                    alert.show();
                }
            }
        );
        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnInventory, btnMarket, btnAdvanceDay, btnWater,
                btnPesticide, btnFertilizer);
        VBox root = new VBox();
        root.setId("rootvbox");
        root.getChildren().addAll(info, empty1, farm, empty2, buttons);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        return new Scene(root, 1000, 750, Color.BLACK);
    }

    private static void advanceDay() {
        String difficulty = Main.getPlayer().getDiff();
        double probability = 0.5;
        if ("Easy".equals(difficulty)) {
            probability = 0.7;
        } else if ("Hard".equals(difficulty)) {
            probability = 0.3;
        }
        // chance of random event
        if (Math.random() > probability) {
            int event = (int) (Math.random() * 3 + 1);

            if (event == 1) { // increase 5, 10, or 15 water level
                int rain = (int) (Math.random() * 3 + 1) * 5;

                for (Plot pl : farm.getFarm()) {
                    if (pl.getCrop() != null && pl.getCrop().getLifeStage() != 4) {
                        pl.setWaterLevel(pl.getWaterLevel() + rain);
                    }
                }

                Alert rainAlert = new Alert(Alert.AlertType.CONFIRMATION);
                rainAlert.setTitle("Rain Event");
                rainAlert.setHeaderText("Increase water level of each plots by " + rain + ".");
                rainAlert.show();
            } else if (event == 2) { // decrease 5, 10, or 15 water level
                int drought = (int) (Math.random() * 3 + 1) * 5;

                for (Plot pl : farm.getFarm()) {
                    if (pl.getCrop() != null && pl.getCrop().getLifeStage() != 4) {
                        pl.setWaterLevel(pl.getWaterLevel() - drought);
                    }
                }

                Alert droughtAlert = new Alert(Alert.AlertType.CONFIRMATION);
                droughtAlert.setTitle("Drought Event");
                droughtAlert.setHeaderText("Decrease water level of each plot by " + drought + ".");
                droughtAlert.show();
            } else if (event == 3) {
                int dead = 0;
                for (Plot pl : farm.getFarm()) {
                    if (pl.getCrop() != null && pl.getCrop().getLifeStage() != 4
                            && !pl.getCrop().getHasPesticide()) {
                        double locusts = Math.random();

                        if (locusts > probability) {
                            pl.getCrop().setLifeStage(4);
                            pl.setPlotImage(new Image("/images/Wilted.png"));
                            dead++;
                        }
                    }
                }

                Alert locustsAlert = new Alert(Alert.AlertType.CONFIRMATION);
                locustsAlert.setTitle("Locusts Event");
                locustsAlert.setHeaderText(dead + " of your plant had been killed");
                locustsAlert.show();
            }
        }
        for (Plot pl : farm.getFarm()) {
            if (pl.getCrop() != null) {
                try {
                    pl.getWorker().work(pl);
                } catch (Exception ignored) {
                }
                if (pl.getWaterLevel() < 10) {
                    pl.getCrop().setLifeStage(4);
                    pl.setPlotImage(new Image("/images/Wilted.png"));
                } else if (pl.getWaterLevel() > 75) {
                    pl.getCrop().setLifeStage(4);
                    pl.setPlotImage(new Image("/images/Wilted.png"));
                } else {
                    pl.cropGrowth();
                    pl.setWaterLevel(pl.getWaterLevel() - 5);
                }
                if (pl.getFertilizerLevel() > 0) {
                    pl.setFertilizerLevel(pl.getFertilizerLevel() - 5);
                }
            }
        }
        Main.getPlayer().setDay(Main.getPlayer().getDay() + 1);
        Main.getPlayer().setHarvestingCount(0);
        Main.getPlayer().setWateringCount(0);
        try {
            Main.getStage().setScene(FarmScene.getScene());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int addPesticide() {
        ObservableList<Integer> options =
                FXCollections.observableArrayList();
        for (int i = 0; i < 25; i++) {
            if (FarmScene.getFarm()[i].getCrop() != null) {
                options.add(i + 1);
            }
        }

        if (options.size() > 0) {
            ChoiceDialog dialog = new ChoiceDialog(options.get(0), options);
            dialog.setTitle("Plot Choice");
            dialog.setHeaderText("Select a plot ");

            Optional<Integer> result = dialog.showAndWait();
            int selectedPlot;

            if (result.isPresent()) {
                selectedPlot = result.get();
                FarmScene.getFarm()[selectedPlot - 1].addPesticide();
            }
            return 0;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Plant some crops to add pesticide to!");
            alert.show();
            return -1;
        }
    }

    public static boolean addFertilizer() {
        ObservableList<Integer> options =
                FXCollections.observableArrayList();
        for (int i = 0; i < 25; i++) {
            if (FarmScene.getFarm()[i].getCrop() != null) {
                options.add(i + 1);
            }
        }

        if (options.size() > 0) {
            ChoiceDialog dialog = new ChoiceDialog(options.get(0), options);
            dialog.setTitle("Plot Choice");
            dialog.setHeaderText("Select a plot ");

            Optional<Integer> result = dialog.showAndWait();
            int selectedPlot;

            if (result.isPresent()) {
                selectedPlot = result.get();
                FarmScene.getFarm()[selectedPlot - 1].addFertilizer(10);
                return true;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Plant some crops to add fertilizer to!");
            alert.show();
        }
        return false;
    }

    public static void water() {
        int playerWaterMax = Main.getPlayer().getWateringMaximum();
        int playerWaterCount = Main.getPlayer().getWateringCount();

        if (playerWaterCount >= playerWaterMax) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("You have reached the watering maximum of the day.");
            alert.show();
        }

        ObservableList<Integer> options =
                FXCollections.observableArrayList();
        for (int i = 0; i < 25; i++) {
            if (FarmScene.getFarm()[i].getCrop() != null) {
                options.add(i + 1);
            }
        }

        if (options.size() > 0) {
            ChoiceDialog dialog = new ChoiceDialog(options.get(0), options);
            dialog.setTitle("Plot Choice");
            dialog.setHeaderText("Select a plot ");

            Optional<Integer> result = dialog.showAndWait();
            int selectedPlot;

            if (result.isPresent()) {
                selectedPlot = result.get();
                FarmScene.getFarm()[selectedPlot - 1].waterPlant(10);
                Main.getPlayer().setWateringCount(playerWaterCount + 1);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Plant some crops to water!");
            alert.show();
        }
    }

    public static void harvest() {
        int playerHarvestMax = Main.getPlayer().getWateringMaximum();
        int playerHarvestCount = Main.getPlayer().getWateringCount();

        if (playerHarvestCount >= playerHarvestMax) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("You have reached the harvesting maximum of the day.");
            alert.show();
        }

        for (int i = 0; i < 25; i++) {
            int finalI = i;
            if (farm.getFarm()[i].getPlotImage() != null) {
                // INSERT  HERE
                farm.getFarm()[i].getPlotImage().setOnMouseClicked(event -> {
                    if (farm.getFarm()[finalI].getCrop().getLifeStage() == 3) {
                        if (Main.getPlayer().getInventory().size() == 25) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Not enough space in inventory!");
                            alert.show();
                            return;
                        }
                        int fertilizer = farm.getFarm()[finalI].getFertilizerLevel();
                        double chance = Math.random();
                        if (fertilizer > 0 && chance > 0.5) {
                            Main.getPlayer().getInventory().add(farm.getFarm()[finalI].getCrop());
                        }
                        Main.getPlayer().getInventory().add(farm.getFarm()[finalI].getCrop());
                        farm.getFarm()[finalI] = new Plot();
                        Alert nameAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        nameAlert.setHeaderText("Congratulations! You just harvested a crop!");
                        nameAlert.setTitle("Successfully Harvested!");
                        nameAlert.show();
                        Main.getPlayer().setHarvestingCount(playerHarvestCount + 1);
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
    }

    public static void buildFarm(GridPane gridPane) {
        int tracker = -1;
        for (int i = 0; i < 5; i++) {
            gridPane.addRow(i,
                    (farm.getFarm()[++tracker].getPlotImage() != null
                            ? farm.getFarm()[tracker].getPlotImage() : new
                            Label("" + (tracker + 1))),
                    (farm.getFarm()[++tracker].getPlotImage() != null
                            ? farm.getFarm()[tracker].getPlotImage() : new
                            Label("" + (tracker + 1))),
                    (farm.getFarm()[++tracker].getPlotImage() != null
                            ? farm.getFarm()[tracker].getPlotImage() : new
                            Label("" + (tracker + 1))),
                    (farm.getFarm()[++tracker].getPlotImage() != null
                            ? farm.getFarm()[tracker].getPlotImage() : new
                            Label("" + (tracker + 1))),
                    (farm.getFarm()[++tracker].getPlotImage() != null
                            ? farm.getFarm()[tracker].getPlotImage() : new
                            Label("" + (tracker + 1)))
            );
        }
    }
}
