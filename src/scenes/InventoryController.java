package scenes;

import gameobjects.items.Item;
import gameobjects.items.crops.Cauliflower;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.Main;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class InventoryController extends Application {
    public ImageView inventoryImageView0;
    public ImageView inventoryImageView1;
    public ImageView inventoryImageView2;
    public ImageView inventoryImageView3;
    public ImageView inventoryImageView4;
    public ImageView inventoryImageView5;
    public ImageView inventoryImageView6;
    public ImageView inventoryImageView7;
    public ImageView inventoryImageView8;
    public ImageView inventoryImageView9;
    public ImageView inventoryImageView10;
    public ImageView inventoryImageView11;
    public ImageView inventoryImageView12;
    public ImageView inventoryImageView13;
    public ImageView inventoryImageView14;
    public ImageView inventoryImageView15;
    public ImageView inventoryImageView16;
    public ImageView inventoryImageView17;
    public ImageView inventoryImageView18;
    public ImageView inventoryImageView19;
    public ImageView inventoryImageView20;
    public ImageView inventoryImageView21;
    public ImageView inventoryImageView22;
    public ImageView inventoryImageView23;
    public ImageView inventoryImageView24;
    List<ImageView> images = new ArrayList<>();


    public Item[] inventory = Main.getPlayer().getInventory();

    @Override
    public void start(Stage inventoryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/Inventory.fxml"));

        Scene scene = new Scene(root);
        Stage stage = inventoryStage;
        stage.setTitle("Inventory");
        stage.setScene(scene);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        stage.show();

        images.add(inventoryImageView0);
        images.add(inventoryImageView1);
        images.add(inventoryImageView2);
        images.add(inventoryImageView3);
        images.add(inventoryImageView4);
        images.add(inventoryImageView5);
        images.add(inventoryImageView6);
        images.add(inventoryImageView7);
        images.add(inventoryImageView8);
        images.add(inventoryImageView9);
        images.add(inventoryImageView10);
        images.add(inventoryImageView11);
        images.add(inventoryImageView12);
        images.add(inventoryImageView13);
        images.add(inventoryImageView14);
        images.add(inventoryImageView15);
        images.add(inventoryImageView16);
        images.add(inventoryImageView17);
        images.add(inventoryImageView18);
        images.add(inventoryImageView19);
        images.add(inventoryImageView20);
        images.add(inventoryImageView21);
        images.add(inventoryImageView22);
        images.add(inventoryImageView23);
        images.add(inventoryImageView24);

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                continue;
            }
            System.out.println(inventory[i].getBasePrice());
            ImageView image = new ImageView(inventory[i].getImage());
            images.set(i, image);
        }
    }
}
