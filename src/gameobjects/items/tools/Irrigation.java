package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

public class Irrigation extends Tool {
    public Irrigation() {
        super("Irrigation", 3, new Image("images/Irrigation.png"));
    }

    @Override
    public void action(Crop crop) {

    }
}