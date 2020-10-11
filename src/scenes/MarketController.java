package scenes;

import gameobjects.items.Item;
import gameobjects.items.crops.Cauliflower;
import gameobjects.items.crops.Corn;
import gameobjects.items.crops.Sunflower;
import gameobjects.items.tools.Axe;
import gameobjects.items.tools.Shovel;
import gameobjects.items.tools.Sickle;
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
import javafx.scene.image.Image;
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
//        for (int i = 0; i < 25; i++) {
            Main.getPlayer().getInventory().add(new Cauliflower());
//        }
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
        moneyLabel.setText("" + Main.getPlayer().getMoney());
    }

    @FXML
    private void goBack() throws FileNotFoundException {
        Main.getStage().setScene(FarmScene.getScene());
    }

    @FXML
    private void select(MouseEvent e) {
//        ((ImageView) e.getSource()).setImage(new Image("/images/empty_slot.jpg"));
        this.clearEffect();
        if (((ImageView) e.getSource()).getImage().getUrl().contains("empty_slot.jpg")) {
            selected = null;
            return;
        }
        this.selected = new Pair<>((ImageView) e.getSource(),
                Integer.parseInt(((ImageView) e.getSource()).getId().substring(15)));

        this.selected.getKey().setEffect(new DropShadow(20, Color.BLACK));
    }

//    public void initialize(String diff) throws FileNotFoundException {
//        inventoryItems.add("i.getType()");
//        inventoryList.setItems(inventoryItems);
//
//        int originalNum;
//        if (diff.equals("Easy")) {
//            originalNum = 6;
//        } else if (diff.equals("Normal")) {
//            originalNum = 4;
//        } else {
//            originalNum = 2;
//        }
//
//    }

    @FXML
    void buy(ActionEvent e) {
        if (selected == null || market.get(selected.getValue()) == null) {
            Alert emptySlot = new Alert(Alert.AlertType.ERROR);
            emptySlot.setHeaderText("No item selected!");
            emptySlot.show();
            return;
        }
        int price = (int) (Objects.requireNonNull(market.get(selected.getValue()).get(0)).getBasePrice() * 10);
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
        if (inventoryList.getSelectionModel().getSelectedIndices().size() == 0) {
            Alert noItems = new Alert(Alert.AlertType.ERROR);
            noItems.setHeaderText("Inventory empty!");
            noItems.show();
        }
        for (int i: inventoryList.getSelectionModel().getSelectedIndices()
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

//    public void setImages() {
//        marketImageView0.setImage(market.get(0).peek().getImage()); //add getOrEmpty(item) method to item
//        marketImageView1.setImage(market.get(1).peek().getImage());
//        marketImageView2.setImage(market.get(2).peek().getImage());
//        marketImageView3.setImage(market.get(3).peek().getImage());
//        marketImageView4.setImage(market.get(4).peek().getImage());
//        marketImageView5.setImage(market.get(5).peek().getImage());
//        marketImageView6.setImage(market.get(6).peek().getImage());
//        marketImageView7.setImage(market.get(7).peek().getImage());
//        marketImageView8.setImage(market.get(8).peek().getImage());
//        marketImageView9.setImage(market.get(9).peek().getImage());
//        marketImageView10.setImage(market.get(10).peek().getImage());
//        marketImageView11.setImage(market.get(11).peek().getImage());
//        marketImageView12.setImage(market.get(12).peek().getImage());
//        marketImageView13.setImage(market.get(13).peek().getImage());
//        marketImageView14.setImage(market.get(14).peek().getImage());
//        marketImageView15.setImage(market.get(15).peek().getImage());
//        marketImageView16.setImage(market.get(16).peek().getImage());
//        marketImageView17.setImage(market.get(17).peek().getImage());
//        marketImageView18.setImage(market.get(18).peek().getImage());
//        marketImageView19.setImage(market.get(19).peek().getImage());
//        marketImageView20.setImage(market.get(20).peek().getImage());
//        marketImageView21.setImage(market.get(21).peek().getImage());
//        marketImageView22.setImage(market.get(22).peek().getImage());
//        marketImageView23.setImage(market.get(23).peek().getImage());
//        marketImageView24.setImage(market.get(24).peek().getImage());
//    }

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
