package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public class Pickaxe extends Tool {
    public Pickaxe() throws FileNotFoundException {
        super("Pickaxe", 3, new Image("/images/Pickaxe.png"));
    }

    @Override
    public void action(Crop crop) {
        crop.setLifeStage(0);
        this.setDurability(this.getDurability() - 1);
    }
}
