package gameobjects;

public class Player {
    private int day;
    private int money;
    private String season;
    private String diff;
    private String seed;
    private String name;
    private String[] inventory = new String[20];

    public Player() {
        this(1, 50, "Normal", "Spring", "Corn", "Aibek");
    }

    public Player(int day, int money, String diff, String startingSeason,
                  String startingSeed, String name) {
        this.day = day;
        this.money = money;
        this.setDiff(diff);
        this.season = startingSeason;
        this.seed = startingSeed;
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
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

    public String[] getInventory() {
        return inventory;
    }

    public void setInventory(String[] inventory) {
        this.inventory = inventory;
    }
}
