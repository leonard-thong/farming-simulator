package gameobjects.items.crops;

public class Sunflower extends Crop {
    protected Sunflower() {
        super("Sunflower", 1.4);
    }

    @Override
    public boolean harvest() {
        return false;
    }
}
