package edu.csu2017fa314.T25.View;

import com.google.gson.Gson;
import edu.csu2017fa314.T25.Model.Model;
import edu.csu2017fa314.T25.Model.TripLeg;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
   private int totalDistance;
   public String outputSVG = "";
   public Model model;

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

   public void getCoordinates(String SVGPath) throws IOException{
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
      while(scanner.hasNextLine()) {
         String line = scanner.nextLine();
         if (line.contains("path49") && !foundPathX) {
            foundPathX = true;
         }
         if (line.contains("polyline45") && !foundpolyY) {
            foundpolyY = true;
         }
         if (line.contains("points=") && !foundpolyY) {
            pathPolylineY = line;
         }
         if (line.contains("d=") && !foundPathX) {
            path49X = line;
         }
         if (line.contains("width") && !foundWidth) {// create escape sequence for width and height
            svgWidth = Double.parseDouble(line.substring(10, line.length() - 1));
            foundWidth = true;
         }
         if (line.contains("height") && !foundHeight) {
            svgHeight = Double.parseDouble(line.substring(11, line.length() - 1));
            foundHeight = true;
         }

         if (line.contains("path181")) {
            outputSVG += "   id=\"path181\" />\n";
            break;

         }
         outputSVG += line + "\n";
         //else add to string for file output
      }
      scanner.close();
      padX = Double.parseDouble(path49X.substring(path49X.indexOf("M ") + 2,path49X.indexOf(".") + 6));
      padY = Double.parseDouble(pathPolylineY.substring(pathPolylineY.lastIndexOf(",") + 1,pathPolylineY.lastIndexOf(".") + 6));
      insertSVG(svgWidth,svgHeight,padX,padY);
      //x1 is at path49
      //y1 is at polyline45
   }

   public void insertSVG(double svgWidth, double svgHeight, double padX, double padY) throws IOException{
      String coordinates = "";
      String startcoordinate = "";
      int latIndex = 0;
      int longIndex = 0;
      for(int i = 0; i < model.latcoordinates.size(); i++){
         double svgXcoordinate = ((svgWidth - (padX * 2)) * (-109 + Math.abs(model.longcoordinates.get(i))) / (-109 + 102));
         svgXcoordinate += padX;
         double svgYcoordinate = ((svgHeight - (padY * 2)) * (41 - model.latcoordinates.get(i)) / (41 - 37));
         svgYcoordinate += padY;
         if(i == 0) {
            coordinates += "\t<path d=\"M" + String.format("%.5f", svgXcoordinate) + " " + String.format("%.5f", svgYcoordinate) + " ";
            startcoordinate = "L" +String.format("%.5f", svgXcoordinate) + " " + String.format("%.5f", svgYcoordinate) + " ";

         }
         else {
            coordinates += "L" +String.format("%.5f", svgXcoordinate) + " " + String.format("%.5f", svgYcoordinate) + " ";
         }

      }
      coordinates += startcoordinate;
      coordinates += " \" stroke=\"red\" stroke-width=\"3\" fill=\"none\"/>  ";
      outputSVG += coordinates;
      outputSVG += "\n" + "\t\t</g>\n" + "\n" + "  </g>\n" + "\n" + "</svg>\n";

      File file = new File("web/output.svg");
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(outputSVG);
      bw.close();
      //System.out.println(outputSVG);
      //System.out.println(coordinates);
   }
}
