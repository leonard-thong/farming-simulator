package gameobjects;

import gameobjects.items.crops.Crop;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Plot {
    private ImageView plotImage;
    private int waterLevel;
    private Crop crop;

    public Plot() {
        plotImage = null;
        waterLevel = 0;
        crop = null;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setPlotImage(Image image) {
        plotImage = new ImageView(image);
        plotImage.setFitHeight(100);
        plotImage.setFitWidth(100);
        waterLevel += 10;
    }

    public void waterPlant(int val) {
        waterLevel += val;
    }

    public ImageView getPlotImage() {
        return plotImage;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }
}
