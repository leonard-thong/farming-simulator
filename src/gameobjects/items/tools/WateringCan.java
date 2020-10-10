package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

public class WateringCan extends Tool {
    public WateringCan() {
        super("WateringCan", 3, new Image("/images/Watering_Can.png"));
    }

    @Override
    public void action(Crop crop) {
        crop.incrLifeStage();
        this.setDurability(this.getDurability() - 1);
    }
}
