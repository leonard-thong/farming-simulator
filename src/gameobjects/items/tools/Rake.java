package gameobjects.items.tools;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Rake extends Tool {
    public Rake() throws FileNotFoundException {
        super("Rake", 2.9, new Image("images/Rake.png"));
    }

    @Override
    public void action(Crop crop) {

    }
}
