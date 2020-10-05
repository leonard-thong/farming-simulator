package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sickle extends Tool {
    public Sickle() throws FileNotFoundException {
        super("Sickle", 3, new Image(new FileInputStream("images/Sickle.png")));
    }

    @Override
    public void action(Crop crop) {
        crop.setLifeStage(0);
        this.setDurability(this.getDurability() - 1);
    }
}
