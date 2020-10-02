package gameobjects.items;

import javafx.scene.image.Image;

public abstract class Item {
    private final String type;
    private final double basePrice;
    private Image image;

    protected Item(String type, double basePrice, Image image) {
        this.type = type;
        this.basePrice = basePrice;
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getSellPrice(Item item) { //Edit later
        return item.basePrice;
    }

    public double getBuyPrice(Item item) {
        return item.basePrice;
    }
}
