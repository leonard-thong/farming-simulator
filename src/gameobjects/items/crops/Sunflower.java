package gameobjects.items.crops;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Sunflower extends Crop {
    public Sunflower() throws FileNotFoundException {
        super(3, "Sunflower", 1.4, new Image("/images/Sunflower_Stage_1.png"));
    }

    @Override
    public boolean harvest() {
        return this.getLifeStage() == 3;
    }
}
