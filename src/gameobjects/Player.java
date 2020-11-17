package gameobjects;

import gameobjects.items.Item;

import java.util.ArrayList;


public class Player {
    private int day;
    private int money;
    private String season;
    private String diff;
    private String name;
    private ArrayList<Item> inventory = new ArrayList<>(25);

    private int harvestingMaximum;
    private int wateringMaximum;
    private int harvestingCount;
    private int wateringCount;

    public Player() {
        this(1, 100, "Normal", "Spring", "Aibek", 7, 7, 0, 0);
    }

    public Player(int day, int money, String diff, String startingSeason, String name,
                  int harvestingMaximum, int wateringMaximum, int wateringCount, int harvestingCount) {
        this.day = day;
        this.money = money;
        this.setDiff(diff);
        this.season = startingSeason;
        this.name = name;
        this.harvestingMaximum = harvestingMaximum;
        this.wateringMaximum = wateringMaximum;
        this.harvestingCount = harvestingCount;
        this.wateringCount = wateringCount;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public int getHarvestingMaximum() {
        return harvestingMaximum;
    }

    public int getWateringMaximum() {
        return wateringMaximum;
    }

    public void setHarvestingMaximum(int n) {
        harvestingMaximum = n;
    }

    public void setWateringMaximum(int n) {
        wateringMaximum = n;
    }

    public int getHarvestingCount() {
        return harvestingCount;
    }

    public int getWateringCount() {
        return wateringCount;
    }

    public void setHarvestingCount(int n) {
        harvestingCount = n;
    }

    public void setWateringCount(int n) {
        wateringCount = n;
    }
}
