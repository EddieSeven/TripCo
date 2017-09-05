package edu.csu2017fa314.T25.Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Model
{
    public static ArrayList<Model> breweriesList = new ArrayList<>();

    public String studentID = "";
    public String name = "";
    public String city = "";
    public String latitude = "";
    public String longitude = "";
    public int elevation = 0;

    public Model(){}

    public Model(String stuID,String brewName,String brewCi,String brewLat,String brewLong,int brewElev){
        studentID = stuID;
        name = brewName;
        city = brewCi;
        latitude = brewLat;
        longitude = brewLong;
        elevation = brewElev;
    }


    public static void readCSV(String csvLocation) throws FileNotFoundException {
         Scanner scanner = new Scanner(new File(csvLocation));
         scanner.nextLine();
         scanner.useDelimiter("\n");
         while (scanner.hasNext()) {
             Model brewery = new Model();
             String[] values = scanner.next().split(",");
             brewery.studentID = values[0];
             brewery.name = values[1];
             brewery.city = values[2];
             brewery.latitude = values[3];
             brewery.longitude = values[4];
             brewery.elevation = Integer.parseInt(values[5]);
             breweriesList.add(brewery);
         }
         scanner.close();
    }
}
