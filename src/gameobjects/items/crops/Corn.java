package gameobjects.items.crops;

import javafx.scene.image.Image;

import java.util.Random;

public class Corn extends Crop {

    public Corn() {
        this(0);
    }

    public Corn(Image image) {
        super(new Random().nextInt(4), "Corn", 1.3, image);
    }

    public Corn(int lifestage) {
        super(lifestage, "Corn", 1.3, new Image("/images/Corn_Stage_1.png"));
        if (super.getLifeStage() == 1) {
            super.setImage(new Image("images/Corn_Stage_2.png"));
        } else if (super.getLifeStage() == 2) {
            super.setImage(new Image("images/Corn_Stage_3.png"));
        } else if (super.getLifeStage() == 3) {
            super.setImage(new Image("images/Corn_Harvest.png"));
        }
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
