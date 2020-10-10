package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Axe extends Tool {
    public Axe() throws FileNotFoundException {
        super("Axe", 3, new Image("/images/Axe.png"));
    }

    @Override
    public void action(Crop crop) {
        this.setDurability(this.getDurability() - 1);
    }
}
