package scenes;

import gameobjects.items.Item;
import gameobjects.items.crops.Cauliflower;
import gameobjects.items.crops.Corn;
import gameobjects.items.crops.Sunflower;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import main.Main;

import java.util.HashMap;
import java.util.Map;


public class MarketController {

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
    private Button dropButton;

    private static Map<Integer, Item[]> market = new HashMap<>();

    public static void initialize(String diff) {
        int originalNum = 0;
        if (diff.equals("easy")) {
            originalNum = 6;
        } else if (diff.equals("normal")) {
            originalNum = 4;
        } else {
            originalNum = 2;
        }
        market.put(0, new Cauliflower[originalNum]);
        market.put(1, new Cauliflower[originalNum]);
        market.put(2, new Corn[originalNum]);
        market.put(3, new Corn[originalNum]);
        market.put(4, new Sunflower[originalNum]);
        market.put(5, new Sunflower[originalNum]);


    }

    @FXML
    void buy() {
    }

    @FXML
    void sell(){

    }

    public void setImages() {
        marketImageView0.setImage(market.get(0).getImage());
        marketImageView1.setImage(market[1].getImage());
        marketImageView2.setImage(market[2].getImage());
        marketImageView3.setImage(market[3].getImage());
        marketImageView4.setImage(market[4].getImage());
        marketImageView5.setImage(market[5].getImage());
        marketImageView6.setImage(market[6].getImage());
        marketImageView7.setImage(market[7].getImage());
        marketImageView8.setImage(market[8].getImage());
        marketImageView9.setImage(market[9].getImage());
        marketImageView10.setImage(market[10].getImage());
        marketImageView11.setImage(market[11].getImage());
        marketImageView12.setImage(market[12].getImage());
        marketImageView13.setImage(market[13].getImage());
        marketImageView14.setImage(market[14].getImage());
        marketImageView15.setImage(market[15].getImage());
        marketImageView16.setImage(market[16].getImage());
        marketImageView17.setImage(market[17].getImage());
        marketImageView18.setImage(market[18].getImage());
        marketImageView19.setImage(market[19].getImage());
        marketImageView20.setImage(market[20].getImage());
        marketImageView21.setImage(market[21].getImage());
        marketImageView22.setImage(market[22].getImage());
        marketImageView23.setImage(market[23].getImage());
        marketImageView24.setImage(market[24].getImage());
    }
}
