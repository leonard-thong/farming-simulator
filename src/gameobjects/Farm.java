package gameobjects;

import gameobjects.items.crops.Crop;

import java.util.ArrayList;

public class Farm {
    private ArrayList<Plot> farm;

    public Farm() {
        farm = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            farm.add(new Plot());
        }
    }

    public ArrayList<Plot> getFarm() {
        return this.farm;
    }

    public void plantPlot(Crop crop, int index) {
        if (index == -1) {
            for (int i = 0; i < farm.size(); i++) {
                if (farm.get(i).getCrop() == null) {
                    farm.get(i).setCrop(crop);
                    farm.get(i).setPlotImage(crop.getImage());
                    break;
                }
            }
        } else {
            farm.get(index - 1).setCrop(crop);
            farm.get(index - 1).setPlotImage(crop.getImage());
        }
    }
}