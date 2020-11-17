package scenes;

import gameobjects.items.Item;
import gameobjects.items.crops.Crop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.Main;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
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

    private ArrayList<ImageView> images = new ArrayList<>();
    private String selectedID = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        images.addAll(Arrays.asList(inventoryImageView0,
                inventoryImageView1, inventoryImageView2, inventoryImageView3, inventoryImageView4,
                inventoryImageView5, inventoryImageView6, inventoryImageView7,
                inventoryImageView8, inventoryImageView9, inventoryImageView10,
                inventoryImageView11, inventoryImageView12, inventoryImageView13,
                inventoryImageView14, inventoryImageView15, inventoryImageView16,
                inventoryImageView17, inventoryImageView18, inventoryImageView19,
                inventoryImageView20, inventoryImageView21, inventoryImageView22,
                inventoryImageView23, inventoryImageView24));
        this.setImages();
    }

    @FXML
    private void item(MouseEvent e) {
        this.clearEffect();
        if (((ImageView) e.getSource()).getImage().getUrl().contains("empty_slot.jpg")) {
            selectedID = "";
            return;
        }
        ImageView img = (ImageView) e.getSource();
        img.setEffect(new DropShadow(20, Color.BLACK));
        selectedID = img.getId();
    }

    @FXML
    private void goBack() throws FileNotFoundException {
        Main.getStage().setScene(FarmScene.getScene());
    }

    @FXML
    public void drop(ActionEvent e) {
        if (selectedID.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No item selected!");
            alert.show();
        } else {
            int selectedItem = Integer.parseInt(selectedID.substring(18));
            Main.getPlayer().getInventory().remove(selectedItem);
            images.get(selectedItem).setImage(new Image("/images/empty_slot.jpg"));
            selectedID = "";
        }
    }

    public void plant(ActionEvent e) {
        if (selectedID.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No seed selected!");
            alert.show();
        } else {
            int selectedItem = Integer.parseInt(selectedID.substring(18));
            if (Main.getPlayer().getInventory().get(selectedItem) instanceof Crop) {
                Crop crop = (Crop) Main.getPlayer().getInventory().get(selectedItem);
                if (crop.getLifeStage() != 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("You can only plant a seed");
                    System.out.println(crop.getLifeStage());
                    alert.show();
                } else {
                    ObservableList<Integer> options =
                            FXCollections.observableArrayList();
                    for (int i = 0; i < FarmScene.getFarm().size(); i++) {
                        if (FarmScene.getFarm().get(i).getCrop() == null) {
                            options.add(i + 1);
                        }
                    }

                    ChoiceDialog<Integer> dialog = new ChoiceDialog<>(options.get(0), options);
                    dialog.setTitle("Plot Choice");
                    dialog.setHeaderText("Select a plot ");

                    Optional<Integer> result = dialog.showAndWait();
                    int selectedPlot;

                    if (result.isPresent()) {
                        selectedPlot = result.get();
                        FarmScene.getFarm().get(selectedPlot - 1).setCrop(crop);
                        FarmScene.getFarm().get(selectedPlot - 1).setPlotImage(crop.getImage());
                    }
                    Main.getPlayer().getInventory().remove(selectedItem);
                    images.get(selectedItem).setImage(new Image("/images/empty_slot.jpg"));
                    selectedID = "";
                    try {
                        Main.getStage().setScene(FarmScene.getScene());
                    } catch (FileNotFoundException d) {
                        d.printStackTrace();
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("You can only plant a seed!");
                alert.show();
            }
        }
    }

    public void setImages() {
        int i = 0;
        for (Item item : Main.getPlayer().getInventory()) {
            images.get(i++).setImage(item.getImage());
        }
    }

    private void clearEffect() {
        for (ImageView iv : images) {
            iv.setEffect(null);
        }
    }
}