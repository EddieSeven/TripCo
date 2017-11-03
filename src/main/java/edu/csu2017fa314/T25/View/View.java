package edu.csu2017fa314.T25.View;

import com.google.gson.Gson;
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
   private double svgWidth = 1024.0;
   private double svgHeight = 512.0;
   private double padX = 0.0;// no pad x on world map.
   private double padY = 14.0;//

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

   public void getCoordinates() throws IOException{
      Scanner scanner = new Scanner(new File("web/world.svg"));
	  outputSVG = "";

      //grab the SVG file height and width
      String pathPolylineY = "";
      String path49X = "";


      while(scanner.hasNextLine()) {
         String line = scanner.nextLine();
         outputSVG += line + "\n";
         //else add to string for file output
      }
      scanner.close();
      padX = Double.parseDouble(path49X.substring(path49X.indexOf("M ") + 2,path49X.indexOf(".") + 6));
      padY = Double.parseDouble(pathPolylineY.substring(pathPolylineY.lastIndexOf(",") + 1,pathPolylineY.lastIndexOf(".") + 6));

      //System.out.println(outputSVG);
      //insertSVG(svgWidth,svgHeight,padX,padY, path);
      //x1 is at path49
      //y1 is at polyline45
   }

   public String insertSVG(ArrayList<TripLeg> path) throws IOException{
      getCoordinates();
      String coordinates = "";
      String startcoordinate = "";
      int latIndex = 0;
      int longIndex = 0;
      for(int i = 0; i < path.size(); i++){
		 TripLeg leg = path.get(i);
         double svgXcoordinate = ((svgWidth - (padX * 2)) * (-109 - leg.start.longitude) / (-109 + 102));
         svgXcoordinate += padX;
         double svgYcoordinate = ((svgHeight - (padY * 2)) * (41 - leg.start.latitude) / (41 - 37));
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

      return outputSVG;
   }

	public String getSVG() {
		return outputSVG;
	}
}
