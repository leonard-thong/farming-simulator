package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

public class Hoe extends Tool {
    public Hoe() {
        super("Hoe", 3, new Image("/images/Hoe.png"));
    }

    @Override
    public void action(Crop crop) {
        this.setDurability(this.getDurability() - 1);
    }
}
