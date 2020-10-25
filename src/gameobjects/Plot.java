package gameobjects;

import gameobjects.items.crops.Crop;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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
        plotImage.setPickOnBounds(true);
        Tooltip tooltip = new Tooltip("Water Level: " + String.valueOf(this.getWaterLevel()));
        tooltip.setShowDelay(Duration.seconds(.1));
        Tooltip.install(plotImage, tooltip);
    }

    public void waterPlant(int val) {
        waterLevel += val;
        Tooltip tooltip = new Tooltip("Water Level: " + String.valueOf(this.getWaterLevel()));
        tooltip.setShowDelay(Duration.seconds(.1));
        Tooltip.install(plotImage, tooltip);
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
