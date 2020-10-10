package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Fertilizer extends Tool {
    public Fertilizer() throws FileNotFoundException {
        super("Fertilizer", 3, new Image("/images/Hoe.png"));
    }

    @Override
    public void action(Crop crop) {
        crop.setLifeStage(0);
    }
}
