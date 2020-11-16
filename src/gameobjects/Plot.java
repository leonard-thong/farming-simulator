package gameobjects;

import gameobjects.items.crops.Crop;
import gameobjects.npc.FarmWorker;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Plot {
    private ImageView plotImage;
    private int waterLevel;
    private Crop crop;
    private FarmWorker worker;
    private int fertilizerLevel;
    private int growth;

    public Plot() {
        plotImage = null;
        waterLevel = 0;
        crop = null;
        fertilizerLevel = 0;
        growth = 0;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;

    }

    public void cropGrowth() {
        if (waterLevel > 0) {
            growth++;
        }
        if (fertilizerLevel > 0) {
            growth++;
        }
        if (growth >= 3) {
            growth = 0;
            this.crop.grow();
            this.setPlotImage(this.crop.getImage());
        }
    }

    public void addPesticide() {
        this.crop.setHasPesticide(true);
        Tooltip tooltip = new Tooltip("Water Level: " + this.getWaterLevel() + "\nWorker: "
                + (this.worker != null) + "\nPesticide: " + this.crop.getHasPesticide()
                + "\nFertilizer Level: " + this.getFertilizerLevel());
        tooltip.setShowDelay(Duration.seconds(.1));
        Tooltip.install(plotImage, tooltip);
    }

    public void waterPlant(int val) {
        if (waterLevel < 70) {
            waterLevel += val;
            Tooltip tooltip = new Tooltip("Water Level: " + this.getWaterLevel() + "\nWorker: "
                    + (this.worker != null) + "\nPesticide: " + this.crop.getHasPesticide()
                    + "\nFertilizer Level: " + this.getFertilizerLevel());
            tooltip.setShowDelay(Duration.seconds(.1));
            Tooltip.install(plotImage, tooltip);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Water level reached max (70)");
            alert.show();
        }
    }

    public void addFertilizer(int val) {
        if (fertilizerLevel < 50) {
            fertilizerLevel += val;
            Tooltip tooltip = new Tooltip("Water Level: " + this.getWaterLevel() + "\nWorker: "
                    + (this.worker != null) + "\nPesticide: " + this.crop.getHasPesticide()
                    + "\nFertilizer Level: " + this.getFertilizerLevel());
            tooltip.setShowDelay(Duration.seconds(.1));
            Tooltip.install(plotImage, tooltip);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Water level reached max (50)");
            alert.show();
        }
    }

    public ImageView getPlotImage() {
        return plotImage;
    }

    public void setPlotImage(Image image) {
        plotImage = new ImageView(image);
        plotImage.setFitHeight(100);
        plotImage.setFitWidth(100);
        plotImage.setPickOnBounds(true);
        Tooltip tooltip = new Tooltip("Water Level: " + this.getWaterLevel() + "\nWorker: "
                + (this.worker != null) + "\nPesticide: " + this.crop.getHasPesticide()
                + "\nFertilizer Level: " + this.getFertilizerLevel());
        tooltip.setShowDelay(Duration.seconds(.1));
        Tooltip.install(plotImage, tooltip);
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
        Tooltip tooltip = new Tooltip("Water Level: " + this.getWaterLevel() + "\nWorker: "
                + (this.worker != null) + "\nPesticide: " + this.crop.getHasPesticide()
                + "\nFertilizer Level: " + this.getFertilizerLevel());
        tooltip.setShowDelay(Duration.seconds(.1));
        Tooltip.install(plotImage, tooltip);
    }

    public FarmWorker getWorker() {
        return worker;
    }

    public void setWorker(FarmWorker worker) {
        this.worker = worker;
        Tooltip tooltip = new Tooltip("Water Level: " + this.getWaterLevel() + "\nWorker: "
                + (this.worker != null) + "\nPesticide: " + this.crop.getHasPesticide()
                + "\nFertilizer Level: " + this.getFertilizerLevel());
        tooltip.setShowDelay(Duration.seconds(.1));
        Tooltip.install(plotImage, tooltip);
    }

    public int getFertilizerLevel() {
        return fertilizerLevel;
    }

    public void setFertilizerLevel(int fertilizerLevel) {
        this.fertilizerLevel = fertilizerLevel;
        Tooltip tooltip = new Tooltip("Water Level: " + this.getWaterLevel() + "\nWorker: "
                + (this.worker != null) + "\nPesticide: " + this.crop.getHasPesticide()
                + "\nFertilizer Level: " + this.getFertilizerLevel());
        tooltip.setShowDelay(Duration.seconds(.1));
        Tooltip.install(plotImage, tooltip);
    }

}
