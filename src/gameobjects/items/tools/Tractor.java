package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

public class Tractor extends Tool {
    public Tractor() {
        super("Tractor", 3, new Image("images/Tractor.png"));
    }

    @Override
    public void action(Crop crop) {

    }
}
