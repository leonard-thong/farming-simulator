package scenes;

import gameobjects.items.crops.Cauliflower;
import gameobjects.items.crops.Corn;
import gameobjects.items.crops.Crop;
import gameobjects.items.crops.Sunflower;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import main.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.io.FileNotFoundException;

public class FarmScene {
    static Crop[] crops = new Crop[25];
    static Image[] cropPictures = new Image[25];
    static ImageView[] cropImages = new ImageView[25];
    static List<Boolean> arr = new ArrayList<>();
    static Random random = new Random();
    static boolean built = false;

    public static void farmSceneInit() throws FileNotFoundException {
        for (int i = 0; i < 25; i++) {
            int r = random.nextInt(3);
            crops[i] = (r == 0) ? new Corn() : (r == 1) ? new Cauliflower() : new Sunflower();
            cropPictures[i] = crops[i].getImage();
            cropImages[i] = new ImageView(cropPictures[i]);
            cropImages[i].setId("cropimage" + "i");
            if (i == 2) {
                arr.add(true);
            } else {
                arr.add(random.nextInt() % 2 == 1);
            }
        }
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

        GridPane plot = new GridPane();
        plot.setId("plotgrid");
        for (int i = 0; i < 25; i++) {
            int finalI = i;
            if (cropImages[i] != null) {
                cropImages[i].setOnMouseClicked(event -> {
                    if (crops[finalI].getLifeStage() == 3) {
                        System.out.println(crops[finalI].getLifeStage());
                        cropImages[finalI] = null;
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
                    }
                    System.out.println("Clicked!"); // change functionality
                });
            }
        }
        plot.setMaxSize(750, 600);
        int tracker = -1;
        for (int i = 0; i < 5; i++) {
            plot.addRow(i,
                    (cropImages[++tracker] != null && arr.get(i * 5 + 0)) ? cropImages[tracker] : new Label(),
                    (cropImages[++tracker] != null && arr.get(i * 5 + 1)) ? cropImages[tracker] : new Label(),
                    (cropImages[++tracker] != null && arr.get(i * 5 + 2)) ? cropImages[tracker] : new Label(),
                    (cropImages[++tracker] != null && arr.get(i * 5 + 3)) ? cropImages[tracker] : new Label(),
                    (cropImages[++tracker] != null && arr.get(i * 5 + 4)) ? cropImages[tracker] : new Label()
                    );
        }
        plot.setAlignment(Pos.CENTER);
        for (Node cell : plot.getChildren()) {
            if (cell instanceof Control) {
                javafx.scene.control.Control control = (Control) cell;
                control.setPrefSize(150, 100);
                control.setStyle("-fx-background-color: saddlebrown; -fx-alignment: center;");
            }
        }
        plot.setStyle("-fx-background-color: darkgreen; -fx-padding: 2; -fx-hgap: 5; -fx-vgap: 5;");
        plot.setSnapToPixel(false);

        ColumnConstraints oneThird = new ColumnConstraints();
        oneThird.setPercentWidth(100 / 5.0);
        oneThird.setHalignment(HPos.CENTER);
        plot.getColumnConstraints().addAll(oneThird, oneThird, oneThird, oneThird, oneThird);
        RowConstraints oneHalf = new RowConstraints();
        oneHalf.setPercentHeight(100 / 5.0);
        oneHalf.setValignment(VPos.CENTER);
        plot.getRowConstraints().addAll(oneHalf, oneHalf, oneHalf, oneHalf, oneHalf);

        StackPane farm = new StackPane();
        farm.getChildren().addAll(plot);
        StackPane.setAlignment(plot, Pos.CENTER);

        Label empty = new Label();
        empty.setPrefSize(1000, 70);

        VBox root = new VBox();
        root.setId("rootvbox");
        root.getChildren().addAll(info, empty, farm);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // market stuff



        return new Scene(root, 1000, 750, Color.BLACK);
    }
//    public void openMarket() throws Exception{
//        Stage stage = new Stage();
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/scenes/Market.fxml"))));
//        MarketController.initialize(Main.getPlayer().getDiff());
//        stage.show();
//    }
}