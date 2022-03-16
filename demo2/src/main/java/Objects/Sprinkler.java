package Objects;

import java.util.ArrayList;
import java.util.Arrays;
import Application.UserInterface;
public class Sprinkler {

    //----------Static Variables-------//
    public static ArrayList<Sprinkler> sprinkler_array = new ArrayList<>(); // Holds all of the initalized sprinklers

    //----------Instance Variables--------//
    private ArrayList<Plant> plants_in_promixity = new ArrayList<Plant>(); // All the plants that the sprinkler affects
    private int radius;
    private Integer[] position_array = new Integer[2]; // [x, y] [4,6] // ------------NEED TO ADD POSITION
    ArrayList<Integer[]> radius_array = new ArrayList<>(); // [[x,y] [x,y]] // List of all the coordinates for the array

    //Construct the sprinkler with a position and the plants that it affects
    public Sprinkler(int x, int y) {
        position_array[0] = x;
        position_array[1] = y;
        this.radius = 1;
        buildRadiusArray(x, y);
        this.checkForPlantsInProximity(UserInterface.Plantgrid);
        sprinkler_array.add(this);
    }

    //Check if the plant needs water, and if it does, water it
    public void checkToWaterPlants() {
        for (Plant P : plants_in_promixity) {
            if (P.needsWater() && P.isAlive()) {
                P.water();

            }
        }
    }

    //used to build the sprinklers affective radius
    public void buildRadiusArray(int locX, int locY) {
        //https://stackoverflow.com/questions/8275795/algorithm-for-operating-on-2d-array-with-radius/8275819

        for (int x = locX - radius; x <= locX + radius; x++) {
            if (x > 5 || x < 0) {
                continue;
            }
            for (int y = locY - radius; y <= locY + radius; y++) {
                if (y > 5 || y < 0) {
                    continue;
                }
                Integer[] coordinates = new Integer[2];
                coordinates[0] = x;
                coordinates[1] = y;
                radius_array.add(coordinates);
            }
        }


    }

    // This was a function made to test if the radius was being built properly
    /*
    public void addSprinklerRadiusToArray() {
        for (Integer[] integers : this.radius_array) {
            int x = integers[0];
            int y = integers[1];
            testArray[x][y] = 2;
        }
    }
    */

    // Checking and adding to the proximity array what plants are within the boundaries of the sprinklers radius
    public void checkForPlantsInProximity(Plant[][] Plantgrid) {
        // Iterate over plant array
        for (Integer[] C : radius_array) {
            if (Plantgrid[C[0]][C[1]] != null) {
                plants_in_promixity.add(Plantgrid[C[0]][C[1]]);
            }
        }
    }


}

/*   //--------------- Scoped out other sizes of sprinklers
class SmallSprinkler extends Sprinkler {
    SmallSprinkler(int x, int y) {
        super(x, y);
        this.radius = 1;
        buildRadiusArray(x, y);
    }
}
class LargeSprinkler extends Sprinkler {
    LargeSprinkler(int x, int y) {
        super(x, y);
        this.radius = 2;
        buildRadiusArray(x, y);
    }
}
  //----------Dont need a large sprinker, board is too small
class LargeSprinkler extends Sprinkler{
    LargeSprinkler(int x, int y){
        super(x,y);
        this.radius = 3;
        buildRadiusArray(x,y);
    } */
