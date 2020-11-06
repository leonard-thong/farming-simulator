package gameobjects.npc;

public abstract class NPC {
    private int skill; // least: 1 -> most: 5

    public NPC(int skill) {
        this.skill = skill;
    }

    public NPC() {
        this(3);
    }
    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }
}
