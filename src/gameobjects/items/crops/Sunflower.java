package gameobjects.items.crops;

import javafx.scene.image.Image;

public class Sunflower extends Crop {
    public Sunflower() {
        this(0);
    }

    public Sunflower(int lifestage) {
        super(lifestage, "Sunflower", 1.5,
                new Image("/images/Sunflower_Stage_1.png"));
        if (super.getLifeStage() == 1) {
            super.setImage(new Image("images/Sunflower_Stage_2.png"));
        } else if (super.getLifeStage() == 2) {
            super.setImage(new Image("images/Sunflower_Stage_3.png"));
        } else if (super.getLifeStage() == 3) {
            super.setImage(new Image("images/Sunflower_Harvest.png"));
        }
    }

    @Override
    public boolean harvest() {
        return this.getLifeStage() == 3;
    }
}
