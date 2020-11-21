package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

public class Shovel extends Tool {
    public Shovel() {
        this(100);
    }

    public Shovel(int durability) {
        super("Shovel", 2.3, new Image("images/Shovel.png"), durability);
    }
    @Override
    public void action(Crop crop) {

    }
}
