package gameobjects.npc;

import gameobjects.Player;
import gameobjects.Plot;
import main.Main;

public class FarmWorker extends NPC {
    private int wage;

    public FarmWorker(int baseWage, int skill) {
        super(skill);
        String diff = Main.getPlayer().getDiff();
        if (diff.equals("Easy")) {
            this.wage = (baseWage / 2) * skill;
        } else if (diff.equals("Normal")) {
            this.wage = baseWage * skill;
        } else {
            this.wage = baseWage * 2 * skill;
        }
    }

    public FarmWorker() {
        this(5, true);
    }

    public FarmWorker(int baseWage, boolean what) {
        this(baseWage, 3);
    }

    public FarmWorker(int skill) {
        this(5, skill);
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public void setSkill(int skill) {
        int baseWage = 5;
        String diff = Main.getPlayer().getDiff();
        if (diff.equals("Easy")) {
            this.wage = (baseWage / 2) * skill;
        } else if (diff.equals("Normal")) {
            this.wage = baseWage * skill;
        } else {
            this.wage = baseWage * 2 * skill;
        }
        super.setSkill(skill);
    }

    public void work(Plot plot) {
        Player player = Main.getPlayer();
        if (player.getDay() % 7 == 0) {
            if (player.getMoney() < this.wage) {
                plot.setWorker(null);
                return;
            }
            player.setMoney(player.getMoney() - this.wage);
        }
        if (plot.getCrop().getLifeStage() == 3) {
            for (int i = 0; i < this.getSkill(); i++) {
                player.setMoney((int) (player.getMoney() + (plot.getCrop().getBasePrice() * 5)));
            }
            plot.getCrop().setLifeStage(1);
            plot.setPlotImage(plot.getCrop().getImage());
        }
        if (plot.getWaterLevel() <= 10) {
            plot.setWaterLevel(plot.getWaterLevel() + (this.getSkill() * 5));
        }
    }

}
