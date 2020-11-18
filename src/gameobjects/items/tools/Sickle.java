package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

public class Sickle extends Tool {
    public Sickle() {
        this(100);
    }

    public Sickle(int durability) {
        super("Sickle", 3, new Image("images/Sickle.png"), durability);
    }

    @Override
    public void action(Crop crop) {
        crop.setLifeStage(0);
        this.setDurability(this.getDurability() - 1);
    }
}
