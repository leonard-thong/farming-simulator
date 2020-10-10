package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

public class Rake extends Tool {
    public Rake() {
        super("Rake", 2.9, new Image("images/Rake.png"));
    }

    @Override
    public void action(Crop crop) {

    }
}
