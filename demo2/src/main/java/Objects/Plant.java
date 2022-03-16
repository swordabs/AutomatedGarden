package Objects;
import MyLog.Log;
public class Plant {

    int maxWaterHealth; // Helpful for calculating the percentages of water health
    private int waterHealth;
    private int leafHealth;
    private int tempHealth;
    private int[] tempRange = new int[2]; // High and low for temperature range that the plant can live in
    private int dayToHarvest; // days left in harvest, will count down
    private int[] tracker = new int[2]; // position on graph
    private String name; // name of plant, fun for tracking
    private int current_temp; // current temperature that will be given by temp system

    public Plant(int w, int l, int t, int minT, int maxT, int d, int x, int y,
                 String n) {
        waterHealth = w;
        leafHealth = l;
        tempHealth = t;
        tempRange[0] = minT;
        tempRange[1] = maxT;
        dayToHarvest = d;
        tracker[0] = x;
        tracker[1] = y;
        name = n;
    }

    //check if the plant needs water
    public boolean needsWater(){
        return ((waterHealth < 3));
    };

    //------GETTERS-----//
    public int getWaterHealth() {
        return waterHealth;
    }

    public int getLeafHealth() {
        return leafHealth;
    }

    public int getTempHealth() {
        return tempHealth;
    }

    public int getDayToHarvest() {
        return dayToHarvest;
    }

    public String getName() {
        return name;
    }

    public int[] getTracker() {
        return tracker;
    }

    public int getMinTemperature() {
        return tempRange[0];
    }

    public int getMaxTemperature() {
        return tempRange[1];
    }

    public int getCurrentTemp() {return current_temp;}

    //------SETTERS-----//
    public void setCurrentTemp(int value) {current_temp = value;}

    public void setWaterHealth(int value) {
        waterHealth = value;
    }

    public void setLeafHealth(int value) {
        leafHealth = value;
    }

    public void setTempHealth(int value) {
        tempHealth = value;
    }

    public void setDayToHarvest(int value) {
        dayToHarvest = value;
    }

    public void setName(String value) {
        name = value;
    }


    //------END OF DAY FUNCTIONS--------//
    public void decreaseWaterHealth() {
        --waterHealth;
    }
    public void decreaseTempHealth() {
        --tempHealth;
    }
    public void increaseTempHealth(){++tempHealth;}
    public boolean isGoodtemperature() {
        return tempRange[0] <= current_temp && current_temp <= tempRange[1];
    }
    public void harvest(){
        Log.addToDailyLog(" -"+this.getName()+ " is getting harvested");
    }
    public void decreaseHarvest() {
        --dayToHarvest;
    }
    public boolean canHarvest() {
        return dayToHarvest <= 0;
    }
    public boolean isAlive() {
        return leafHealth > 0 && waterHealth > 0 && tempHealth > 0;
    }

    //Interacrtions with other systems
    public void attack() {
        leafHealth--;
    }
    public void water(){
        Log.addToDailyLog(" -"+this.getName()+
                " is getting watered ");
    }

    public String toString() {
        return name + ", waterHealth: " + waterHealth + ", leafHealth: "
                + leafHealth + ", tempHealth:" + tempHealth
                + ", dayToHarvest: " + dayToHarvest;
    }
}