package Objects;

public class Bush extends Plant {
    static int bushesHarvested = 0;

    public Bush(int x, int y, String name) {
        // 9 water, 15 heath, 15 temp health, 60-80 temp range, 10 days until
        // harvest
        super(9, 15, 15, 60, 80, 10, x, y, name);
        this.maxWaterHealth = 8;

    }

    //flower get waters, and water resets at 9 again
    public void water() {
        super.setWaterHealth(9);
        super.water();
    }
    // harvest to the bush and reset harvest day
    public void harvest() {
        super.setDayToHarvest(10);
        super.setLeafHealth(15);
        super.harvest();
        bushesHarvested++;
    }

    public String toString() {
        return "B: " + super.toString();
    }
}
