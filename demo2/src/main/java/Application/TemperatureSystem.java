package Application;

import java.util.ArrayList;
import java.util.Random;
import Objects.Plant;

public class TemperatureSystem {

    //------------Static Variables---------------//
    static TemperatureSystem[][] TempArray = new TemperatureSystem[6][6]; //Used for testing
    static Integer[] quadrantTemps = new Integer[5]; // Used for generating an setting random temperatures for each quadrant each day
    static ArrayList<TemperatureSystem> ActiveSystems = new ArrayList<TemperatureSystem>(); // Holding the active temperature systems,
    // needs a plant to be active
    //------------Instance Variables---------------//
    private int quadrant; // Which quad is the plant in? 1,2,3,4
    private Integer[] position = new Integer[2]; // x,y
    private ArrayList<Plant> plants_in_prox = new ArrayList<Plant>();  // what plants are within the quadrant and will be affected?
    private int quadOptimalTemp; // Based on the plants within the system what temp should the quad optimize for
    private int current_temp; // what is the current temp of the quad

    // Initialize the temp system by putting it on the grid and findings plants in its range based on its quadrant
    // and where there are plants in side of it
    TemperatureSystem(int quadrant) {
        this.quadrant = quadrant;
        this.setSystemOnGrid();
        this.findPlantsInQuadrant(UserInterface.Plantgrid);
        if (!this.plants_in_prox.isEmpty()){
            this.calculateOptimalTemperature();
            ActiveSystems.add(this);
        }

    }

    //------------GETTERS--------------//
    public ArrayList<Plant> getPlantsInProx(){
        return plants_in_prox;
    }

    public int getQuadOptimalTemp(){return quadOptimalTemp;}

    public int getCurrent_temp() {
        return current_temp;
    }

    public Integer[] getPosition(){
        return position;
    }

    //-----------INTERNAL FUNCTIONS-----------//

    // Putting the temp system on the corners of the grid based on which quad it is in
    private void setSystemOnGrid() {
        switch (quadrant) {
            case 1:
                this.position[0] = 0;
                this.position[1] = 0;
                break;
            case 2:
                this.position[0] = 5;
                this.position[1] = 0;
                break;
            case 3:
                this.position[0] = 0;
                this.position[1] = 5;
                break;
            case 4:
                this.position[0] = 5;
                this.position[1] = 5;
                break;
        }
        TempArray[this.position[0]][this.position[1]] = this;
    }

    //Generates a unique temperature for each quadrant to react to
    static void generateRandomTemp() {

        Random rand = new Random(); //instance of random class
        for (int i = 0; i < 5; i++) {
            int int_random = rand.nextInt(45, 100);
            quadrantTemps[i] = int_random;

        }

        for (TemperatureSystem TS: ActiveSystems) {
            //  System.out.println(TS.quadrant + " New temp is "+ quadrantTemps[TS.quadrant]);
            TS.setPlantTemps();
        }

    }

    //Using the plants within the quadrant, this function finds the optimal temperature to optimize for
    private void calculateOptimalTemperature() {
        int count = 0;
        int total = 0;
        for (Plant p : plants_in_prox) {
            total += (p.getMaxTemperature() + p.getMinTemperature()) / 2;
            count += 1;

        }
        quadOptimalTemp = total / count;

    }

    //Finds the plants that will be affected by the instance of the temperature system(each quadrant)
    private void findPlantsInQuadrant(Plant[][] Plantgrid) {

       /* for (int i = 0; i < Plantgrid.length; i++) {
            System.out.println(Arrays.toString(Plantgrid[i]));
        } */

        // Iterate over plant array
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                // If plant isnt null
                if (Plantgrid[i][j] != null) {

                    // if plant is within quadrant 1 and the plant is quadrant 1
                    if (i < 3 && j < 3 && quadrant == 1) {
                        plants_in_prox.add(Plantgrid[i][j]);

                    } else if (i < 3 && j >= 3 && quadrant == 2) {
                        plants_in_prox.add(Plantgrid[i][j]);

                    } else if (i >= 3 && j >= 3 && quadrant == 4) {
                        plants_in_prox.add(Plantgrid[i][j]);

                    } else if (i >= 3 && j < 3 && quadrant == 3) {
                        plants_in_prox.add(Plantgrid[i][j]);

                    }
                }
            }
        }

    }

    //Prints the temperature array, used for testing
    private static void printTempArray() {
        //print board
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (TempArray[i][j] == null) {
                    System.out.print("= ");
                } else {
                    System.out.print(TempArray[i][j] + " ");
                }
            }
            System.out.println();
        }

    }

    // Using the current temperature within the array, it sets the plants temperatures,
    //used right after generating a random temp
    public void setPlantTemps() {
        current_temp = quadrantTemps[quadrant];
        for (Plant P: plants_in_prox) {
            P.setCurrentTemp(current_temp);
        }
    }

    //Adjusts the temperature based on what the optimal temperature of the quad is
    public void changeTemp() {
        if(current_temp==0){
        } else if (current_temp>quadOptimalTemp){
            current_temp -=2;
            //System.out.println(quadrant+" Lowering Temp to "+current_temp);
        } else if (current_temp<quadOptimalTemp) {
            current_temp += 2;
            // System.out.println(quadrant + " Raising Temp to " + current_temp);
        }

        for (Plant P: plants_in_prox) {
            P.setCurrentTemp(current_temp);
        }

    }


}