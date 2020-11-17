package gameobjects.items.crops;

import gameobjects.items.Item;
import javafx.scene.image.Image;

public abstract class Crop extends Item {
    private int lifeStage; // 0 -> Stage1, 1 -> Stage2, 2 -> Stage3, 3 -> Harvest, 4 -> Withered
    private boolean hasPesticide;



    public Crop(int lifeStage, String type, double basePrice, Image image) {
        super(type, basePrice, image);
        this.lifeStage = lifeStage;
        this.hasPesticide = false;
    }

    public abstract boolean harvest();

    public int getLifeStage() {
        return lifeStage;
    }

    public void setHasPesticide(boolean hasPesticide) {
        this.hasPesticide = hasPesticide;
    }

    public boolean getHasPesticide() {
        return this.hasPesticide;
    }

    public void setLifeStage(int lifeStage) {
        if (lifeStage < 3) {
            this.lifeStage = lifeStage;
            this.setImage(new Image("/images/" + this.getType()
                    + "_Stage_" + (lifeStage + 1) + ".png"));
        } else if (lifeStage == 3) {
            this.lifeStage = 3;
            this.setImage(new Image("/images/" + this.getType() + "_Harvest.png"));
        } else if (lifeStage == 4) {
            this.lifeStage = 4;
            this.setImage(new Image("/images/Wilted.png"));
        }
    }


    public void grow() {
        if (this.lifeStage < 3) {
            this.setLifeStage(this.lifeStage + 1);
        }
    }
}
