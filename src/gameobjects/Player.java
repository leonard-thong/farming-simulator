package gameobjects;

public class Player {
    private int money;
    private String season;
    private String diff;
    private String seed;
    private String name;
    private String[] inventory;
    private int day;

    public Player() {
        this(0, 50, "Normal", "Summer", "Corn", "Aibek", 1);
    }

    public Player(int inventorySize, int money, String diff, String startingSeason,
                  String startingSeed, String name, int day) {
        this.setDiff(diff);
        this.inventory = new String[inventorySize];
        this.money = money;
        this.season = startingSeason;
        this.seed = startingSeed;
        this.name = name;
        this.day = day;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
