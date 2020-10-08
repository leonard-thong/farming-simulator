package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WateringCan extends Tool {
    public WateringCan() throws FileNotFoundException {
        super("WateringCan", 3, new Image("/images/Watering_Can.png"));
    }

    @Override
    public void action(Crop crop) {
        crop.incrLifeStage();
        this.setDurability(this.getDurability() - 1);
    }
}
