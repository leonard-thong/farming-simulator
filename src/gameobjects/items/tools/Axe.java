package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

public class Axe extends Tool {
    public Axe() {
        super("Axe", 3, new Image("images/Axe.png"));
    }

    @Override
    public void action(Crop crop) {
        this.setDurability(this.getDurability() - 1);
    }
}
