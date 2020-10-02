package gameobjects.items.tools;

import gameobjects.items.Item;

public abstract class Tool extends Item {
    private boolean broken;
    private int durability;

    public boolean getBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}
