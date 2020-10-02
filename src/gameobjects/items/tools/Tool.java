package gameobjects.items.tools;

import gameobjects.items.Item;
import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

public abstract class Tool extends Item {
    private int durability;

    public Tool(String type, double basePrice, Image image) {
        super(type, basePrice, image);
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public abstract void action(Crop crop);
}
