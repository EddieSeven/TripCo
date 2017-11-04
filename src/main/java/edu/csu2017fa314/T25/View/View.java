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

   public void readSVG() throws IOException{
      Scanner scanner = new Scanner(new File("web/world.svg"));
	  outputSVG = "";
      while(scanner.hasNextLine()) {
         String line = scanner.nextLine();
         outputSVG += line + "\n";
      }
      scanner.close();
   }

   public Double hemisphereValue(Double latitude, Double longitude){// write tests for me
      Double returnedValue = 0.0;



      if(latitude > 0 && longitude < 0){//north west

      }
      if(latitude < 0 && longitude < 0){//south west

      }
      if(latitude > 0 && longitude > 0){//north east

      }
      if(latitude < 0 && longitude > 0){//south east

      }


      return returnedValue;
   }


   public String insertSVG(ArrayList<TripLeg> path) throws IOException{
      readSVG();
      String coordinates = "";
      String startcoordinate = "";
      for(int i = 0; i < path.size(); i++){
		 TripLeg leg = path.get(i);
         double svgXcoordinate = ((svgWidth) * (leg.start.longitude) / (-180 + 102));
         svgXcoordinate += padX;
         double svgYcoordinate = (svgHeight - padY) * (leg.start.latitude) / (41 + 37);
         svgYcoordinate += padY;
         System.out.println();
         System.out.println("svgX: " +svgXcoordinate);
         System.out.println("longitude: "+leg.start.longitude);
         System.out.println("svgY: "+svgYcoordinate);
         System.out.println("Latitude: "+leg.start.latitude);
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
