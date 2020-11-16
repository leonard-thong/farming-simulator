package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

public class Fertilizer extends Tool {
    public Fertilizer() {
        super("Fertilizer", 2, new Image("/images/Fertilizer.png"));
    }

    @Override
    public void action(Crop crop) {
        crop.setLifeStage(0);
    }
}
