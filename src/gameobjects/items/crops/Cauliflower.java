package gameobjects.items.crops;

public class Cauliflower extends Crop {
    protected Cauliflower() {
        super("Cauliflower", 1.5);
    }

    @Override
    public boolean harvest() {
        return false;
    }
}
