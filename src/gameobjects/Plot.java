package gameobjects;

import gameobjects.items.crops.Crop;
import javafx.scene.control.Alert;
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
        plotImage.setPickOnBounds(true);
        Tooltip tooltip = new Tooltip("Water Level: " + this.getWaterLevel());
        tooltip.setShowDelay(Duration.seconds(.1));
        Tooltip.install(plotImage, tooltip);
    }

    public void cropGrowth() {
        this.crop.grow();
        this.setPlotImage(this.crop.getImage());
    }

    public void waterPlant(int val) {
        if (waterLevel < 70) {
            waterLevel += val;
            Tooltip tooltip = new Tooltip("Water Level: " + String.valueOf(this.getWaterLevel()));
            tooltip.setShowDelay(Duration.seconds(.1));
            Tooltip.install(plotImage, tooltip);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Water level reached max (70)");
            alert.show();
        }
    }

    public ImageView getPlotImage() {
        return plotImage;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
        Tooltip tooltip = new Tooltip("Water Level: " + this.getWaterLevel());
        tooltip.setShowDelay(Duration.seconds(.1));
        Tooltip.install(plotImage, tooltip);
    }
}
