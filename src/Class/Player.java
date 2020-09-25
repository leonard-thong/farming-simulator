package Class;

import org.jetbrains.annotations.NotNull;

public class Player {
    private int diff, money;
    private String season, seed, name;
    private String[] inventory;

    public Player() {
        this(0, 20, 0, null, null, null);
    }

    public Player( int diff, int inventorySize, int money, String startingSeason, String startingSeed, String name) {
        this.diff = diff;
        this.inventory = new String[inventorySize];
        this.money = money;
        this.season = startingSeason;
        this.seed = startingSeed;
        this.name = name;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(@NotNull String diff) {
        switch (diff) {
            case "Easy":
                this.diff = 0;
                break;
            case "Normal":
                this.diff = 1;
                break;
            case "Hard":
                this.diff = 2;
                break;
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getStartingSeed() {
        return seed;
    }

    public void setSeed(String startingSeed) {
        this.seed = startingSeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
