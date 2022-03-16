package Objects;

import java.util.ArrayList;
import MyLog.Log;
public class Bug {

    // each plant has 10% to be attacked by a bug
    public static void attackPlant(Plant[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                // null means the spot is empty
                // not null means it is flower, tree, or bush
                Plant plant = grid[r][c];
                if (plant != null) {
                    // Math.random() gives number from 0 - 1
                    if (Math.random() < 0.1) { // 10% to be attacked
                        plant.attack();
                        Log.addToDailyLog(" -"+plant.getName()+" was attacked by a bug");
                    }
                }
            }
        }

    }

}