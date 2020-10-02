package gameobjects.items.crops;

import gameobjects.items.Item;

public abstract class Crop extends Item {
    private int lifeStage;

    public int getLifeStage() {
        return lifeStage;
    }

    public void setLifeStage(int lifeStage) {
        this.lifeStage = lifeStage;
    }
}
