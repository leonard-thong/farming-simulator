package scenes;

import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FarmScene {
    public static Scene getScene() throws FileNotFoundException {
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
        plot.setMaxSize(750, 600);
        plot.addRow(0, new Label(), new Label(), new Label(), new Label(), new Label());
        plot.addRow(1, new Label(), new Label(), new Label(), new Label(), new Label());
        plot.addRow(2, new Label(), new Label(), new Label(), new Label(), new Label());
        plot.addRow(3, new Label(), new Label(), new Label(), new Label(), new Label());
        plot.addRow(4, new Label(), new Label(), new Label(), new Label(), new Label());
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
    public void openMarket() throws Exception{
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/scenes/Market.fxml"))));
        MarketController.initialize(Main.getPlayer().getDiff());
        stage.show();
    }
}
