package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

public class Shovel extends Tool {

    public Shovel() {
        super("Shovel", 2.3, new Image("images/Shovel.png"));
    }

    @Override
    public void action(Crop crop) {

    }
}
