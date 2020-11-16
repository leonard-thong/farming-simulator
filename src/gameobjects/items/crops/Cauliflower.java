package gameobjects.items.crops;

import javafx.scene.image.Image;

public class Cauliflower extends Crop {
    public Cauliflower() {
        super(0, "Cauliflower", 1.5,
                new Image("/images/Cauliflower_Stage_1.png"));
        if (super.getLifeStage() == 1) {
            super.setImage(new Image("images/Cauliflower_Stage_2.png"));
        } else if (super.getLifeStage() == 2) {
            super.setImage(new Image("images/Cauliflower_Stage_3.png"));
        } else if (super.getLifeStage() == 3) {
            super.setImage(new Image("images/Cauliflower_Harvest.png"));
        }
    }

    @Override
    public boolean harvest() {
        return this.getLifeStage() == 3;
    }
}
