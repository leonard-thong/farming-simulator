package gameobjects.items.crops;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Corn extends Crop {

    public Corn() throws FileNotFoundException {
        super(new Random().nextInt(4), "Corn", 1.3, new Image("/images/Corn_Stage_1.png"));
    }

    public Corn(Image image) {
        super(new Random().nextInt(4), "Corn", 1.3, image);
    }

    @Override
    public boolean harvest() {
        if (this.getLifeStage() == 3) {
            this.setLifeStage(2);
            this.setImage(new Image(""));
            return true;
        }
        return false;
    }
}
