package gameobjects.items.crops;

import javafx.scene.image.Image;

public class Cauliflower extends Crop {
    public Cauliflower() {
        super("Cauliflower", 1.5, new Image("images/Cauliflower_Stage_1.png"));
    }

    @Override
    public boolean harvest() {
        return this.getLifeStage() == 3;
    }
}
