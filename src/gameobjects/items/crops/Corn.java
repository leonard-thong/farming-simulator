package gameobjects.items.crops;

import javafx.scene.image.Image;

public class Corn extends Crop {

    protected Corn() {
        super("Corn", 1.3);
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
