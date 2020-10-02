package gameobjects.items.crops;

import gameobjects.items.Item;
import javafx.scene.image.Image;

public abstract class Crop extends Item {
    private int lifeStage; // 0 -> Stage1, 1 -> Stage2, 2 -> Stage3, 3 -> Harvest

    protected Crop(String type, double basePrice) {
        super(type, basePrice);
    }

    public abstract boolean harvest();

    public int getLifeStage() {
        return lifeStage;
    }

    public void setLifeStage(int lifeStage) {
        this.lifeStage = lifeStage;
    }
}
