package Objects;

public class Tree extends Plant {

    static int treesHarvested = 0;


    public Tree(int x, int y, String name) {
        // 13 water, 15 heath, 15 temp health, 50-100 range temp, 30 days until
        // harvest

        super(13, 15, 15, 55, 90, 30, x, y, name);
        this.maxWaterHealth = 10;

    }

    // flower get waters, and water resets at 13 again
    public void water() {
        super.setWaterHealth(13);
        super.water();
    }

    // harvest to the tree and reset harvest day
    public void harvest() {
        super.setDayToHarvest(30);
        super.setLeafHealth(15);
        super.harvest();
        treesHarvested++;
    }

    public String toString() {
        return "T: " + super.toString();
    }
}