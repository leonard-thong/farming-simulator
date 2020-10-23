package gameobjects;

import gameobjects.items.crops.Crop;

public class Farm {
    private Plot[] farm;

    public Farm() {
        farm = new Plot[25];
        for (int i = 0; i < 25; i++) {
            farm[i] = new Plot();
        }
    }

    public Plot[] getFarm() {
        return this.farm;
    }

    public void plantPlot(Crop crop, int index) {
        if (index == -1) {
            for (int i = 0; i < 25; i++) {
                if (farm[i].getCrop() == null) {
                    farm[i].setCrop(crop);
                    farm[i].setPlotImage(crop.getImage());
                    break;
                }
            }
        } else {
            farm[index - 1].setCrop(crop);
            farm[index - 1].setPlotImage(crop.getImage());
        }
    }
}
