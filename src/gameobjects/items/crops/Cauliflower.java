package gameobjects.items.crops;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Cauliflower extends Crop {
    public Cauliflower() throws FileNotFoundException {
        super(new Random().nextInt(4), "Cauliflower", 1.5, new Image("/images/Cauliflower_Stage_1.png"));
    }

    @Override
    public boolean harvest() {
        return this.getLifeStage() == 3;
    }
}
