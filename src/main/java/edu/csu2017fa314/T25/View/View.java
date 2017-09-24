package edu.csu2017fa314.T25.View;

import com.google.gson.Gson;
import edu.csu2017fa314.T25.Model.Model;
import edu.csu2017fa314.T25.Model.TripLeg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class View {

   private int totalDistance;
   public Model modeldata;

   public void setTotalDistance(int distance) 
   {
       totalDistance = distance; 
   }

   public int getTotalDistance() 
   {
      return totalDistance;
   }


   // INPUT: ArrayList<TripLeg> object
   // OUTPUT: JSON file "itinerary.json"
   // POSSIBLE ERRORS: I/O and incorrect ArrayList<TripLeg>
   public void writeJSON(ArrayList<TripLeg> computedDistances) throws IOException {
      Gson gsonObj = new Gson();

      FileWriter writer = new FileWriter("itinerary.json");
      gsonObj.toJson(computedDistances, writer);
      writer.flush();
      writer.close();

   }

   public void convertCoordinates(ArrayList<Model> breweriesList, String SVGPath) throws FileNotFoundException{
      double svgWidth = 0.0;
      double svgHeight = 0.0;
      double padX = 0.0;
      double padY = 0.0;
      Scanner scanner = new Scanner(new File(SVGPath));

      //grab the SVG file height and width
      String pathPolylineY = "";
      String path49X = "";
      Boolean foundWidth = false;
      Boolean foundHeight = false;
      Boolean foundpolyY = false;
      Boolean foundPathX = false;
      while(scanner.hasNextLine()){
         String line = scanner.nextLine();
         if(line.contains("path49") && foundPathX == false){
            foundPathX = true;
         }
         if(line.contains("polyline45") && foundpolyY ==  false){
            foundpolyY = true;
         }
         if(line.contains("points=") && foundpolyY ==  false){
            pathPolylineY = line;
         }
         if(line.contains("d=") && foundPathX == false){
            path49X = line;
         }
         if(line.contains("width") && foundWidth == false){// create escape sequence for width and height
            svgWidth = Double.parseDouble(line.substring(10,line.length()-1));
            foundWidth = true;
         }
         if(line.contains("height") && foundHeight == false){
            svgHeight = Double.parseDouble(line.substring(11,line.length()-1));
            foundWidth = true;
         }
         if(foundHeight == true && foundWidth == true && foundPathX == true && foundpolyY == true){
            //exit while loop after pad has been found
            break;
         }
      }
      scanner.close();
      padX = Double.parseDouble(path49X.substring(path49X.indexOf("M ") + 2,path49X.indexOf(".") + 6));
      padY = Double.parseDouble(pathPolylineY.substring(pathPolylineY.lastIndexOf(",") + 1,pathPolylineY.lastIndexOf(".") + 6));


//      System.out.println("SVG size");
//      System.out.println("Width: " +svgWidth);
//      System.out.println("Height " +svgHeight);
//      System.out.println();
//      System.out.println("Pad of X and Y");
//      System.out.println("X " +padX);
//      System.out.println("Y " +padY);
//      System.out.println();
//      System.out.println("Breweries lat and long");
//
//      System.out.println(breweriesList.get(4).latitude);
//      System.out.println(breweriesList.get(4).longitude);

      double svgXcoordinate = (-109 - Double.parseDouble(breweriesList.get(4).longitude)) / -7;

      svgXcoordinate = (svgXcoordinate * svgWidth) + padX;

      double svgYcoordinate = ((41 - Double.parseDouble(breweriesList.get(4).latitude)) / 4);

      svgYcoordinate = (svgYcoordinate * svgHeight) + padY;
//      System.out.println();
//      System.out.println();
      String coordinate = svgXcoordinate + " " + svgYcoordinate;

//      System.out.println(coordinate);


      //x1 is at path49
      //y1 is at polyline45
   }

   public void insertSVG(){

   }



}
