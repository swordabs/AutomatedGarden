package Objects;

public class Flower extends Plant {
    public Flower(int x, int y, String name) {
        // 7 water, 5 leaf, 15 tempHealth, 65-90 temp range, 15 days until harvest
        super(7, 5, 15, 65, 90, 15, x, y, name);
        this.maxWaterHealth = 7;


    }

    // flower get waters, and water resets at 7 again
    public void water() {
        super.setWaterHealth(7);
        super.water();
    }

    // harvest to the flower and reset harvest day
    public void harvest() {
        super.setDayToHarvest(15);
        super.setLeafHealth(5);
        super.harvest();
    }

    public String toString() {
        return "F: " + super.toString();
    }
}