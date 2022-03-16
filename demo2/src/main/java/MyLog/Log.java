package MyLog;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Log {

    static Dictionary<String, Log> log_list; // List of all of the logs

    // Used to convert text to csv format
    public static String convertToCSV(String[] data) {
        //------------------------https://www.baeldung.com/java-csv------------------------//

        return Arrays.stream(data).collect(Collectors.joining(","));
    }

    // used to add a row of data into a csv log
    public static void addToCSVLog(String log_name, String[] row_of_data) {
        try (FileWriter fw = new FileWriter(log_name, true)) {
            //pw.println(convertToCSV(columns));
            fw.append(convertToCSV(row_of_data));
            fw.append("\n");
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // creating a csv using the name and columns as input
    public static void createCSVLog(String log_name, String[] columns) {
        File csvOutputFile = new File(log_name);
        try (FileWriter pw = new FileWriter(csvOutputFile)) {
            //pw.println(convertToCSV(columns));

            pw.append("column_values->   " + convertToCSV(columns));
            pw.append("\n");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // adding values to a daily log
    public static void addToDailyLog(String row_of_data) {
        try (FileWriter fw = new FileWriter("DailyLog.txt", true)) {
            //pw.println(convertToCSV(columns));
            fw.append(row_of_data);
            fw.append("\n");
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // creating the daily log
    public static void createDailyLog(String log_name) {
        File csvOutputFile = new File(log_name);
        try (FileWriter pw = new FileWriter(csvOutputFile)) {
            //pw.println(convertToCSV(columns));

            pw.append("                   //------------Daily Log---------//");
            pw.append("\n \n \n");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Testing stuff
/* testing stuff
    public static void main(String[] args) {
        String[] PlantColumns = {"plantInstance", "time_stamp", "water_health", "left_health", "tempHealth", "days_to_harvest", "Comment"};
        createCSVLog("plantLog.csv", PlantColumns);
        createDailyLog("DailyLog.txt");
        addToDailyLog("--------Day 1--------");
        addToDailyLog("Wow it was such a great day");
     Example on how to use
        createLog("plantLog.csv",columns);
        String[] row =  {"1", "6", "0","01"};
        String[] row2 =  {"1", "5", "0","02"};
        String[] row3 =  {"1", "3","1",  "03"};
        String[] row4 =  {"1", "4","0", "04"};
        String[] row5 =  {"1", "3","1", "05"};
        String[] row6 =  {"1", "5","0", "06"}; // Testing githu
        addToLog("plantLog.csv",row);
        addToLog("plantLog.csv",row2);
        addToLog("plantLog.csv",row3);
        addToLog("plantLog.csv",row4);
        addToLog("plantLog.csv",row5);
        addToLog("plantLog.csv",row6);
        }
     */



}