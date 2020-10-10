package gameobjects.items.crops;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sunflower extends Crop {
    public Sunflower() throws FileNotFoundException {
        super("Sunflower", 1.4, new Image("/images/Sunflower_Stage_1.png"));
    }

    @Override
    public boolean harvest() {
        return this.getLifeStage() == 3;
    }
}
