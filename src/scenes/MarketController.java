package scenes;

import gameobjects.items.Item;
import gameobjects.items.crops.Cauliflower;
import gameobjects.items.crops.Corn;
import gameobjects.items.crops.Sunflower;
import gameobjects.items.tools.Axe;
import gameobjects.items.tools.Irrigation;
import gameobjects.items.tools.Shovel;
import gameobjects.items.tools.Sickle;
import gameobjects.items.tools.Tractor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import main.Main;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;


public class MarketController implements Initializable {

    private static Map<Integer, LinkedList<Item>> market = new HashMap<>();
    @FXML
    private Button backButton;

    @FXML
    private Label moneyLabel;
    @FXML
    private ImageView marketImageView0;
    @FXML
    private ImageView marketImageView1;
    @FXML
    private ImageView marketImageView2;
    @FXML
    private ImageView marketImageView3;
    @FXML
    private ImageView marketImageView4;
    @FXML
    private ImageView marketImageView5;
    @FXML
    private ImageView marketImageView6;
    @FXML
    private ImageView marketImageView7;
    @FXML
    private ImageView marketImageView8;
    @FXML
    private ImageView marketImageView9;
    @FXML
    private ImageView marketImageView10;
    @FXML
    private ImageView marketImageView11;
    @FXML
    private ImageView marketImageView12;
    @FXML
    private ImageView marketImageView13;
    @FXML
    private ImageView marketImageView14;
    @FXML
    private ImageView marketImageView15;
    @FXML
    private ImageView marketImageView16;
    @FXML
    private ImageView marketImageView17;
    @FXML
    private ImageView marketImageView18;
    @FXML
    private ImageView marketImageView19;
    @FXML
    private ImageView marketImageView20;
    @FXML
    private ImageView marketImageView21;
    @FXML
    private ImageView marketImageView22;
    @FXML
    private ImageView marketImageView23;
    @FXML
    private ImageView marketImageView24;
    @FXML
    private Button buyButton;
    @FXML
    private Button sellButton;
    @FXML
    private ListView<String> inventoryList;
    private Pair<ImageView, Integer> selected = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Main.getPlayer().getInventory().add(new Cauliflower());
        ObservableList<String> inventoryItems = FXCollections.observableArrayList();
        for (Item i : Main.getPlayer().getInventory()
        ) {
            if (i != null) {
                inventoryItems.add(i.getType());
            }
        }
        inventoryList.setItems(inventoryItems);
        market.put(0, new LinkedList<>());
        market.get(0).addAll(Arrays.asList(new Cauliflower()));
        market.get(0);
        market.put(1, new LinkedList<>());
        market.get(1).addAll(Arrays.asList(new Corn()));
        market.put(2, new LinkedList<>());
        market.get(2).addAll(Arrays.asList(new Sunflower()));
        market.put(3, new LinkedList<>());
        market.get(3).addAll(Arrays.asList(new Shovel()));
        market.put(4, new LinkedList<>());
        market.get(4).addAll(Arrays.asList(new Axe()));
        market.put(5, new LinkedList<>());
        market.get(5).addAll(Arrays.asList(new Sickle()));
        market.put(8, new LinkedList<>());
        market.get(8).addAll(Arrays.asList(new Tractor()));
        market.put(9, new LinkedList<>());
        market.get(9).addAll(Arrays.asList(new Irrigation()));
        moneyLabel.setText("" + Main.getPlayer().getMoney());
    }

    @FXML
    private void goBack() throws FileNotFoundException {
        Main.getStage().setScene(FarmScene.getScene());
    }

    @FXML
    private void select(MouseEvent e) {
        this.clearEffect();
        if (((ImageView) e.getSource()).getImage().getUrl().contains("empty_slot.jpg")) {
            selected = null;
            return;
        }
        this.selected = new Pair<>((ImageView) e.getSource(),
                Integer.parseInt(((ImageView) e.getSource()).getId().substring(15)));

        this.selected.getKey().setEffect(new DropShadow(20, Color.BLACK));
    }

    @FXML
    void buy(ActionEvent e) {
        if (selected == null || market.get(selected.getValue()) == null) {
            Alert emptySlot = new Alert(Alert.AlertType.ERROR);
            emptySlot.setHeaderText("No item selected!");
            emptySlot.show();
            return;
        }
        int diffMultiplier = 0;
        if ("Easy".equals(Main.getPlayer().getDiff())) {
            diffMultiplier = 5;
        } else if ("Normal".equals(Main.getPlayer().getDiff())) {
            diffMultiplier = 10;
        } else if ("Hard".equals(Main.getPlayer().getDiff())) {
            diffMultiplier = 15;
        }
        int price = (int) (market.get(selected.getValue()).get(0).getBasePrice() * diffMultiplier);
        int money = Main.getPlayer().getMoney();
        if (money < price) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Not enough money!");
            alert.show();
        } else {
            if (Main.getPlayer().getInventory().size() == 25) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Not enough space in inventory!");
                alert.show();
            } else {
                Main.getPlayer().setMoney(money - price);
                Item bought = market.get(selected.getValue()).get(0);
                Main.getPlayer().getInventory().add(bought);
                ObservableList<String> temp = inventoryList.getItems();
                temp.add(bought.getType());
                inventoryList.setItems(temp);
                moneyLabel.setText("" + Main.getPlayer().getMoney());
                // market.get(selected.getValue()).get(0);
            }
        }
    }

    @FXML
    void sell(ActionEvent e) {
        if (Main.getPlayer().getInventory().size() == 0) {
            Alert noItems = new Alert(Alert.AlertType.ERROR);
            noItems.setHeaderText("Inventory empty!");
            noItems.show();
        } else if (inventoryList.getSelectionModel().getSelectedIndices().size() == 0) {
            Alert noItems = new Alert(Alert.AlertType.ERROR);
            noItems.setHeaderText("No item selected!");
            noItems.show();
        }

        for (int i : inventoryList.getSelectionModel().getSelectedIndices()
        ) {
            Item temp = Main.getPlayer().getInventory().remove(i);
            int price = (int) (temp.getBasePrice() * 5);
            Main.getPlayer().setMoney(Main.getPlayer().getMoney() + price);
        }
        ObservableList<String> inventoryItems = FXCollections.observableArrayList();
        for (Item i : Main.getPlayer().getInventory()
        ) {
            if (i != null) {
                inventoryItems.add(i.getType());
            }
        }
        inventoryList.setItems(inventoryItems);
        moneyLabel.setText(Main.getPlayer().getMoney() + "");
    }

    private void clearEffect() {
        marketImageView0.setEffect(null);
        marketImageView1.setEffect(null);
        marketImageView2.setEffect(null);
        marketImageView3.setEffect(null);
        marketImageView4.setEffect(null);
        marketImageView5.setEffect(null);
        marketImageView6.setEffect(null);
        marketImageView7.setEffect(null);
        marketImageView8.setEffect(null);
        marketImageView9.setEffect(null);
        marketImageView10.setEffect(null);
        marketImageView11.setEffect(null);
        marketImageView12.setEffect(null);
        marketImageView13.setEffect(null);
        marketImageView14.setEffect(null);
        marketImageView15.setEffect(null);
        marketImageView16.setEffect(null);
        marketImageView17.setEffect(null);
        marketImageView18.setEffect(null);
        marketImageView19.setEffect(null);
        marketImageView20.setEffect(null);
        marketImageView21.setEffect(null);
        marketImageView22.setEffect(null);
        marketImageView23.setEffect(null);
        marketImageView24.setEffect(null);
    }
}
