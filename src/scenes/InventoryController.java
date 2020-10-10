package scenes;

import gameobjects.items.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;


public class InventoryController implements Initializable {
    @FXML
    private ImageView inventoryImageView0;
    @FXML
    private ImageView inventoryImageView1;
    @FXML
    private ImageView inventoryImageView2;
    @FXML
    private ImageView inventoryImageView3;
    @FXML
    private ImageView inventoryImageView4;
    @FXML
    private ImageView inventoryImageView5;
    @FXML
    private ImageView inventoryImageView6;
    @FXML
    private ImageView inventoryImageView7;
    @FXML
    private ImageView inventoryImageView8;
    @FXML
    private ImageView inventoryImageView9;
    @FXML
    private ImageView inventoryImageView10;
    @FXML
    private ImageView inventoryImageView11;
    @FXML
    private ImageView inventoryImageView12;
    @FXML
    private ImageView inventoryImageView13;
    @FXML
    private ImageView inventoryImageView14;
    @FXML
    private ImageView inventoryImageView15;
    @FXML
    private ImageView inventoryImageView16;
    @FXML
    private ImageView inventoryImageView17;
    @FXML
    private ImageView inventoryImageView18;
    @FXML
    private ImageView inventoryImageView19;
    @FXML
    private ImageView inventoryImageView20;
    @FXML
    private ImageView inventoryImageView21;
    @FXML
    private ImageView inventoryImageView22;
    @FXML
    private ImageView inventoryImageView23;
    @FXML
    private ImageView inventoryImageView24;

    @FXML
    private Item[] inventory = Main.getPlayer().getInventory();
    private String selectedID = "";
    private int selectedItem = -1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setImages();
    }

    @FXML
    private void item(MouseEvent e) {
        this.clearEffect();
        ImageView img = (ImageView) e.getSource();
        img.setEffect(new DropShadow(20, Color.BLACK));
        selectedID = img.getId();
    }

    @FXML
    public void drop(ActionEvent e) {
        if (selectedID == "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No item selected!");
            alert.show();
        } else {
            selectedItem = Integer.parseInt(selectedID.substring(18));
            Item[] inventory = Main.getPlayer().getInventory();
            inventory[selectedItem] = null;

            Main.getPlayer().setInventory(inventory);

            selectedID = "";
            selectedItem = -1;

            this.setImages();
        }
    }

    public void setImages() {
        inventoryImageView0.setImage(inventory[0].getImage());
        inventoryImageView1.setImage(inventory[1].getImage());
        inventoryImageView2.setImage(inventory[2].getImage());
        inventoryImageView3.setImage(inventory[3].getImage());
        inventoryImageView4.setImage(inventory[4].getImage());
        inventoryImageView5.setImage(inventory[5].getImage());
        inventoryImageView6.setImage(inventory[6].getImage());
        inventoryImageView7.setImage(inventory[7].getImage());
        inventoryImageView8.setImage(inventory[8].getImage());
        inventoryImageView9.setImage(inventory[9].getImage());
        inventoryImageView10.setImage(inventory[10].getImage());
        inventoryImageView11.setImage(inventory[11].getImage());
        inventoryImageView12.setImage(inventory[12].getImage());
        inventoryImageView13.setImage(inventory[13].getImage());
        inventoryImageView14.setImage(inventory[14].getImage());
        inventoryImageView15.setImage(inventory[15].getImage());
        inventoryImageView16.setImage(inventory[16].getImage());
        inventoryImageView17.setImage(inventory[17].getImage());
        inventoryImageView18.setImage(inventory[18].getImage());
        inventoryImageView19.setImage(inventory[19].getImage());
        inventoryImageView20.setImage(inventory[20].getImage());
        inventoryImageView21.setImage(inventory[21].getImage());
        inventoryImageView22.setImage(inventory[22].getImage());
        inventoryImageView23.setImage(inventory[23].getImage());
        inventoryImageView24.setImage(inventory[24].getImage());
    }

    private void clearEffect() {
        inventoryImageView0.setEffect(null);
        inventoryImageView1.setEffect(null);
        inventoryImageView2.setEffect(null);
        inventoryImageView3.setEffect(null);
        inventoryImageView4.setEffect(null);
        inventoryImageView5.setEffect(null);
        inventoryImageView6.setEffect(null);
        inventoryImageView7.setEffect(null);
        inventoryImageView8.setEffect(null);
        inventoryImageView9.setEffect(null);
        inventoryImageView10.setEffect(null);
        inventoryImageView11.setEffect(null);
        inventoryImageView12.setEffect(null);
        inventoryImageView13.setEffect(null);
        inventoryImageView14.setEffect(null);
        inventoryImageView15.setEffect(null);
        inventoryImageView16.setEffect(null);
        inventoryImageView17.setEffect(null);
        inventoryImageView18.setEffect(null);
        inventoryImageView19.setEffect(null);
        inventoryImageView20.setEffect(null);
        inventoryImageView21.setEffect(null);
        inventoryImageView22.setEffect(null);
        inventoryImageView23.setEffect(null);
        inventoryImageView24.setEffect(null);
    }
}
