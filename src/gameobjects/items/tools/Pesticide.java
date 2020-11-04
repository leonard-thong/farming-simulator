package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

public class Pesticide extends Tool {
    public Pesticide() {
        super("Pesticide", 2, new Image("/images/Pesticide.png"));
    }

    @Override
    public void action(Crop crop) {
        //After implementation of locusts
    }
}
