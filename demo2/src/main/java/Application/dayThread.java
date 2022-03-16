package Application;
import MyLog.Log;
import Objects.Bug;
import Objects.Plant;
import Objects.Sprinkler;

import Application.TemperatureSystem;
public class dayThread extends Thread{
    static int thread_day = 0; // Used to know what day it is
    static int length_of_simulation = 50; // used to set the length of the simulation
    static int day_in_seconds = 1/2; // used to simplify how long days are
    static int day = 1000*day_in_seconds; // used to simplified the caluclation of the wait for a day

    public void run() {

        while (thread_day<length_of_simulation) {
            //Start of Day
            thread_day +=1;
            System.out.println(thread_day);
            Log.addToDailyLog("                   --- DAY "+thread_day+" ---");

            //Generate temperature for the temperature quadrants
            TemperatureSystem.generateRandomTemp();

            //Bug Attack
            Bug.attackPlant(UserInterface.Plantgrid);

            try {
                Thread.sleep(day);
            }catch(InterruptedException e){System.out.println(e);}




            //-----------------------------------AT THE END OF THE DAY WE NEED TO------------------//
            // Increase harvest,harvest if possible
            // See if the plant is going to die or not
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (UserInterface.Plantgrid[i][j] != null){
                        //CHECK TEMP
                        if(!UserInterface.Plantgrid[i][j].isGoodtemperature()){
                            UserInterface.Plantgrid[i][j].decreaseTempHealth();
                            Log.addToDailyLog(" -"+UserInterface.Plantgrid[i][j].getName()+
                                    " was out of its temperature range " +
                                    "Temp: "+UserInterface.Plantgrid[i][j].getCurrentTemp());
                        } else {
                            UserInterface.Plantgrid[i][j].increaseTempHealth();
                        }

                        //NEED TO DECRESE WATER AND HARVEST, NEED A CHECK FOR IF SOMETHING IS HARVESTABLE, IF IT IS, NEED TO RESET THE NUMBER
                        UserInterface.Plantgrid[i][j].decreaseWaterHealth();
                        if (UserInterface.Plantgrid[i][j].canHarvest()){
                            UserInterface.Plantgrid[i][j].harvest();
                        } else {
                            UserInterface.Plantgrid[i][j].decreaseHarvest();
                        }

                        // check if the plant dies -> remove it
                        if (!UserInterface.Plantgrid[i][j].isAlive()) {
                            Log.addToDailyLog(" ---"+UserInterface.Plantgrid[i][j].getName()+" is dead :(");
                            UserInterface.Plantgrid[i][j] = null;

                        }

                    }
                }
            }

            //End of day
            Log.addToDailyLog("                   --- DAY "+thread_day+" IS OVER ---\n");




        }
    }

    public static void main(String args[]) {




        //----------PLANTS----------//
        UserInterface.growFlower(0, 1, "Blue Flower");
        UserInterface.growTree(0, 2, "Blue Tree");
        UserInterface.growBush(1, 3, "Green Bush");

        // UserInterface.growBush(5, 1, "green bush");
        // UserInterface.growBush(5, 5, "green bush");



        //------SPRINKLERS-----//
        Sprinkler Sprinkler1 = new Sprinkler(1,2);
        SprinklerThread Sprink1 = new SprinklerThread();
        Sprink1.start();



        //------TEMPERATURE SYSTEM-----//
        TemperatureSystem.generateRandomTemp();
        TemperatureSystem Quad1 = new TemperatureSystem(1);
        TemperatureSystem Quad2 = new TemperatureSystem(2);
        TemperatureSystem Quad3 = new TemperatureSystem(3);
        TemperatureSystem Quad4 = new TemperatureSystem(4);
        tempThread tempSystem =new tempThread();
        tempSystem.start();


        //-------Logging System-----//

        //plantLog
        String[] PlantColumns = {"plantInstance", "time_stamp", "water_health", "left_health", "tempHealth", "days_to_harvest", "Comment"};
        Log.createCSVLog("plantLog.csv", PlantColumns);

        //sprinklerLog
        String[] SprinklerColumns = {"plantInstance", "time_stamp", "water_health", "left_health", "tempHealth", "days_to_harvest", "Comment"};
        Log.createCSVLog("sprinklerLog.csv", SprinklerColumns);

        //temperatureLog
        String[] TemperatureColumns = {"plantInstance", "time_stamp", "water_health", "left_health", "tempHealth", "days_to_harvest", "Comment"};
        Log.createCSVLog("temperatureLog.csv", TemperatureColumns);


        //dailyLog
        Log.createDailyLog("DailyLog.txt");


        dayThread dayThread = new dayThread();
        dayThread.start();



    }
}


class tempThread extends Thread {
    public void run() {

        while (dayThread.thread_day < dayThread.length_of_simulation) {
            for (TemperatureSystem T : TemperatureSystem.ActiveSystems) {
                T.changeTemp();
            }
            try {
                Thread.sleep(dayThread.day/5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }


}

class SprinklerThread extends Thread {
    public void run() {

        while (dayThread.thread_day < dayThread.length_of_simulation) {
            for (Sprinkler S : Sprinkler.sprinkler_array) {
                S.checkToWaterPlants();

            }

        }
    }

}
