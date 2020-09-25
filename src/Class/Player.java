public class Player {
    private int money, diff;
    private String name, startingSeed, season;
    private String[] inventory = new String[20];

    public Player() {
        this(null, null, null, 0, 0);
    }
    public Player(String name, String startingSeed, String startingSeason, int diff, int money) {
        this.name = name;
        this.diff = diff;
        this.startingSeed = startingSeed;
        this.season = startingSeason;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartingSeed() {
        return startingSeed;
    }

    public void setStartingSeed(String startingSeed) {
        this.startingSeed = startingSeed;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

}
