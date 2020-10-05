package gameobjects.items.crops;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Cauliflower extends Crop {
    public Cauliflower() throws FileNotFoundException {
        super("Cauliflower", 1.5, new Image(new FileInputStream("images/Cauliflower_Stage_1.png")));
    }

    @Override
    public boolean harvest() {
        return this.getLifeStage() == 3;
    }
}
