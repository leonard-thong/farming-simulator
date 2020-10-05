package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Hoe extends Tool {
    public Hoe() throws FileNotFoundException {
        super("Hoe", 3, new Image(new FileInputStream("images/Hoe.png")));
    }

    @Override
    public void action(Crop crop) {
        this.setDurability(this.getDurability() - 1);
    }
}
