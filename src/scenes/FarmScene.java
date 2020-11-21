package scenes;

import gameobjects.Farm;
import gameobjects.Player;
import gameobjects.Plot;
import gameobjects.items.Item;
import gameobjects.items.crops.Crop;
import gameobjects.items.tools.Fertilizer;
import gameobjects.items.tools.Pesticide;
import gameobjects.items.tools.Tool;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    public static ArrayList<Plot> getFarm() {
        return farm.getFarm();
    }

    public static Farm getFarmObj() {
        return farm;
    }

    public static Scene getScene() {
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
        oneHalf.setPercentHeight(100 / 6.0);
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

        Button btnSave = new Button("Save");
        btnSave.setId("saveButton");
        btnSave.setOnAction(e -> {
            try {
                save();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Button btnUnlock = new Button();
        btnUnlock.setId("unlockButton");
        btnUnlock.setText("Expand");
        btnUnlock.setOnAction(event -> {
            addPlot();
        });
        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnInventory, btnMarket, btnAdvanceDay, btnWater,
                btnPesticide, btnFertilizer, btnUnlock, btnSave);

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
                    pl.setWaterLevel(pl.getWaterLevel() + 5);
                }
                if (pl.getFertilizerLevel() > 0) {
                    pl.setFertilizerLevel(pl.getFertilizerLevel() - 5);
                }
            }
        }


        int diffMultiplier = 0;
        if ("Easy".equals(Main.getPlayer().getDiff())) {
            diffMultiplier = 5;
        } else if ("Normal".equals(Main.getPlayer().getDiff())) {
            diffMultiplier = 10;
        } else if ("Hard".equals(Main.getPlayer().getDiff())) {
            diffMultiplier = 15;
        }
        int lowestPrice = (int) (1.3 * diffMultiplier);

        boolean anyLeft = true;
        if (Main.getPlayer().getMoney() < lowestPrice && Main.getPlayer().getInventory().size() == 0) {
            anyLeft = false;
            for (Plot pl : farm.getFarm()) {
                if (pl.getCrop() != null && pl.getCrop().getLifeStage() != 4) {
                    anyLeft = true;
                    break;
                }
            }
        }
        if (!anyLeft) {
            Main.reset();
            farm = new Farm();
            Parent root = null;
            try {
                root = FXMLLoader.load(FarmScene.class.getResource("/scenes/EndScene.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getStage().setScene(new Scene(root));
        } else {
            Main.getPlayer().setDay(Main.getPlayer().getDay() + 1);
            Main.getPlayer().setHarvestingCount(0);
            Main.getPlayer().setWateringCount(0);
            Main.getStage().setScene(FarmScene.getScene());
        }
    }

    private static int addPesticide() {
        ObservableList<Integer> options =
                FXCollections.observableArrayList();
        for (int i = 0; i < FarmScene.getFarm().size(); i++) {
            if (FarmScene.getFarm().get(i).getCrop() != null) {
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
                FarmScene.getFarm().get(selectedPlot - 1).addPesticide();
            }
            return 0;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Plant some crops to add pesticide to!");
            alert.show();
            return -1;
        }
    }

    private static boolean addFertilizer() {
        ObservableList<Integer> options =
                FXCollections.observableArrayList();
        for (int i = 0; i < FarmScene.getFarm().size(); i++) {
            if (FarmScene.getFarm().get(i).getCrop() != null) {
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
                FarmScene.getFarm().get(selectedPlot - 1).addFertilizer(10);
                return true;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Plant some crops to add fertilizer to!");
            alert.show();
        }
        return false;
    }


    public static void addPlot() {
        int farmSize = FarmScene.getFarm().size();
        if (farmSize < 30) {
            int cost = (farmSize % 25) * 10 + 20;
            int money = Main.getPlayer().getMoney();

            if (money >= cost) {
                Main.getPlayer().setMoney(money - cost);
                FarmScene.getFarm().add(new Plot());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("You don't have enough money to buy a new plot");
                alert.show();
            }
            Main.getStage().setScene(FarmScene.getScene());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("You need to buy the premium version to unlock more plots");
            alert.show();
        }
    }


    public static void water() {
        int playerWaterMax = Main.getPlayer().getWateringMaximum();
        int playerWaterCount = Main.getPlayer().getWateringCount();

        if (playerWaterCount >= playerWaterMax) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("You have reached the watering maximum of the day.");
            alert.show();
            return;
        }

        ObservableList<Integer> options =
                FXCollections.observableArrayList();
        for (int i = 0; i < FarmScene.getFarm().size(); i++) {
            if (FarmScene.getFarm().get(i).getCrop() != null) {
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
                Main.getPlayer().setWateringCount(playerWaterCount + 1);
                FarmScene.getFarm().get(selectedPlot - 1).waterPlant(10);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Plant some crops to water!");
            alert.show();
        }
    }

    public static void harvest() {
        int playerHarvestMax = Main.getPlayer().getHarvestingMaximum();
        int playerHarvestCount = Main.getPlayer().getHarvestingCount();
        for (int i = 0; i < FarmScene.getFarm().size(); i++) {
            int finalI = i;
            if (farm.getFarm().get(i).getPlotImage() != null) {
                // INSERT  HERE
                farm.getFarm().get(i).getPlotImage().setOnMouseClicked(event -> {
                    if (farm.getFarm().get(finalI).getCrop().getLifeStage() == 3) {
                        if (Main.getPlayer().getInventory().size() == 25) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Not enough space in inventory!");
                            alert.show();
                            return;
                        } else if (playerHarvestCount >= playerHarvestMax) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("You have reached your maximum harvest count.");
                            alert.show();
                            return;
                        }
                        int fertilizer = farm.getFarm().get(finalI).getFertilizerLevel();
                        double chance = Math.random();
                        if (fertilizer > 0 && chance > 0.5) {
                            Main.getPlayer().getInventory().add(farm.getFarm().get(finalI).getCrop());
                        }
                        Main.getPlayer().getInventory().add(farm.getFarm().get(finalI).getCrop());
                        farm.getFarm().set(finalI, new Plot());

                        Main.getPlayer().setHarvestingCount(Main.getPlayer().getHarvestingCount() + 1);
                        Alert nameAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        nameAlert.setHeaderText("Congratulations! You just harvested a crop!");
                        nameAlert.setTitle("Successfully Harvested!");
                        nameAlert.show();
                        Main.getStage().setScene(FarmScene.getScene());
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

    private static void save() throws IOException {
        // show popup to overwrite or create new & save
        File dir = new File("src/saves");
        if (dir.listFiles().length == 0) {
            Alert noSaves = new Alert(Alert.AlertType.ERROR);
            noSaves.setHeaderText("No saves available");
            noSaves.show();
            return;
        }
        ObservableList<String> saves = FXCollections.observableArrayList();
        saves.add("Create new save");
        for (File f : dir.listFiles()) {
            saves.add(f.getName().substring(0, f.getName().length() - 4));
        }
        ChoiceDialog<String> chooseSave = new ChoiceDialog<>(saves.get(0), saves);
        chooseSave.setTitle("Select Save");
        chooseSave.setHeaderText("Please select a save ");
        Optional<String> save = chooseSave.showAndWait();
        if (save.isPresent()) {
            File savef = null;
            if (save.get().equals(saves.get(0))) {
                TextInputDialog newfNametd = new TextInputDialog("");
                newfNametd.setTitle("Create new save");
                newfNametd.setHeaderText("Enter name here ");
                Optional<String> newfname = newfNametd.showAndWait();
                if (newfname.isPresent()) {
                    savef = new File("src/saves/" + newfname.get() + ".txt");
                    savef.createNewFile();
                }
            } else {
                savef = new File("src/saves/" + save.get() + ".txt");
            }
            Player player = Main.getPlayer();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(savef))) {
                writer.write(player.getName());
                writer.append("\n" + player.getDiff());
                writer.append("\n" + player.getMoney());
                writer.append("\n" + player.getDay());
                writer.append("\n" + player.getSeason());
                writer.newLine();
                if (player.getInventory().size() != 0) {
                    Item item = player.getInventory().get(0);
                    if (item instanceof Crop) {
                        writer.append(item.getType());
                    } else {
                        writer.append(item.getType() + ":" + ((Tool) item).getDurability());
                    }
                }
                for (int i = 1; i < player.getInventory().size(); i++) {
                    Item item = player.getInventory().get(i);
                    if (item instanceof Crop) {
                        writer.append("," + item.getType());
                    } else {
                        writer.append("," + item.getType() + ":" + ((Tool) item).getDurability());
                    }
                }
                writer.newLine();
                Plot plot = farm.getFarm().get(0);
                if (plot.getCrop() == null) {
                    writer.append("null");
                } else {
                    writer.append("0:" + plot.getCrop().getType() + "/" + plot.getCrop().getLifeStage() + ":" + plot.getGrowth() + ":" + plot.getWaterLevel() + ":" + plot.getFertilizerLevel() + ":");
                    writer.append(plot.getWorker() != null ? "true/" + plot.getWorker().getSkill() : "false");
                    writer.append(":" + plot.getCrop().getHasPesticide());
                }
                for (int i = 1; i < farm.getFarm().size(); i++) {
                    plot = farm.getFarm().get(i);
                    if (plot.getCrop() == null) {
                        writer.append(",null");
                    } else {
                        writer.append(",0:" + plot.getCrop().getType() + "/" + plot.getCrop().getLifeStage() + ":" + plot.getGrowth() + ":" + plot.getWaterLevel() + ":" + plot.getFertilizerLevel() + ":");
                        writer.append(plot.getWorker() != null ? ("true/" + plot.getWorker().getSkill()) : "false");
                        writer.append(":" + plot.getCrop().getHasPesticide());
                    }
                }
            }
        }
    }
    public static void setBuilt(boolean built) {
        FarmScene.built = built;
    }

    public static void buildFarm(GridPane gridPane) {
        int tracker = -1;
        for (int i = 0; i < 5; i++) {
            gridPane.addRow(i,
                    (farm.getFarm().get(++tracker).getPlotImage() != null
                            ? farm.getFarm().get(tracker).getPlotImage() : new
                            Label("" + (tracker + 1))),
                    (farm.getFarm().get(++tracker).getPlotImage() != null
                            ? farm.getFarm().get(tracker).getPlotImage() : new
                            Label("" + (tracker + 1))),
                    (farm.getFarm().get(++tracker).getPlotImage() != null
                            ? farm.getFarm().get(tracker).getPlotImage() : new
                            Label("" + (tracker + 1))),
                    (farm.getFarm().get(++tracker).getPlotImage() != null
                            ? farm.getFarm().get(tracker).getPlotImage() : new
                            Label("" + (tracker + 1))),
                    (farm.getFarm().get(++tracker).getPlotImage() != null
                            ? farm.getFarm().get(tracker).getPlotImage() : new
                            Label("" + (tracker + 1)))
            );
        }
        boolean b1 = farm.getFarm().size() - 25 >= 1;
        boolean b2 = farm.getFarm().size() - 25 >= 2;
        boolean b3 = farm.getFarm().size() - 25 >= 3;
        boolean b4 = farm.getFarm().size() - 25 >= 4;
        boolean b5 = farm.getFarm().size() - 25 >= 5;
        ImageView lockedImage1 = new ImageView(new Image("/images/locked.png"));
        lockedImage1.setFitHeight(100);
        lockedImage1.setFitWidth(150);
        ImageView lockedImage2 = new ImageView(new Image("/images/locked.png"));
        lockedImage2.setFitHeight(100);
        lockedImage2.setFitWidth(150);
        ImageView lockedImage3 = new ImageView(new Image("/images/locked.png"));
        lockedImage3.setFitHeight(100);
        lockedImage3.setFitWidth(150);
        ImageView lockedImage4 = new ImageView(new Image("/images/locked.png"));
        lockedImage4.setFitHeight(100);
        lockedImage4.setFitWidth(150);
        ImageView lockedImage5 = new ImageView(new Image("/images/locked.png"));
        lockedImage5.setFitHeight(100);
        lockedImage5.setFitWidth(150);
        gridPane.addRow(5,
                !b1 ? lockedImage1 : (farm.getFarm().get(++tracker).getPlotImage() != null
                        ? farm.getFarm().get(tracker).getPlotImage() : new
                        Label("" + (tracker + 1))),
                !b2 ? lockedImage2 : (farm.getFarm().get(++tracker).getPlotImage() != null
                        ? farm.getFarm().get(tracker).getPlotImage() : new
                        Label("" + (tracker + 1))),
                !b3 ? lockedImage3 : (farm.getFarm().get(++tracker).getPlotImage() != null
                        ? farm.getFarm().get(tracker).getPlotImage() : new
                        Label("" + (tracker + 1))),
                !b4 ? lockedImage4 : (farm.getFarm().get(++tracker).getPlotImage() != null
                        ? farm.getFarm().get(tracker).getPlotImage() : new
                        Label("" + (tracker + 1))),
                !b5 ? lockedImage5 : (farm.getFarm().get(++tracker).getPlotImage() != null
                        ? farm.getFarm().get(tracker).getPlotImage() : new
                        Label("" + (tracker + 1))));


    }
}
