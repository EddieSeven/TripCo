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

   public void setTotalDistance(int distance) 
   {
       totalDistance = distance; 
   }

   public int getTotalDistance() 
   {
      return totalDistance;
   }


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
         if(line.contains("Antarctique")){ //end of SVG, insert path at this point
            outputSVG += "id=\"Antarctique\" /></g>\n";
            break;
         }
         outputSVG += line + "\n";
      }
      scanner.close();
   }

   public double[] hemisphereValue(Double latitude, Double longitude){
      double[] returnedValue = {0.0,0.0};

      if(latitude > 0 && longitude < 0 || latitude > 0 && longitude > 0) {//north west
         returnedValue[0] = ((180 + longitude) / 360) * svgWidth;
         returnedValue[1] = ((90 - latitude) / 180) * svgHeight;
      }
      if(latitude < 0 && longitude < 0 || latitude < 0 && longitude > 0){//south west
         returnedValue[0] = ((180 + longitude) / 360) * svgWidth;
         returnedValue[1] = ((90 + Math.abs(latitude)) / 180) * svgHeight;

      }
      if(longitude == 0){
         returnedValue[0] = 512;
      }
      if(latitude == 0){
         returnedValue[1] = 256;
      }
      if(latitude == -90){
         returnedValue[1] = 512;
      }

      return returnedValue;
   }



   public String internationalDL(double currentX, double currentY, double nextX, double nextY){
       String coordinates = "";
       System.out.println(currentX);
       System.out.println(currentY);
       System.out.println(nextX);
       System.out.println(nextY);

       if (currentX < nextX){ //x1<x2

           double slope = Math.abs(nextY - currentY)/2;
           double intercept;
           if(currentY > 256){//this is the SE
               intercept = slope + currentY;
           }
           else{
               intercept = slope + nextY;
           }
           coordinates += String.format("L%.5f %.5f L%.5f %.5f ", currentX, currentY , 0.0, intercept);
           coordinates += String.format("M%.5f %.5f L%.5f %.5f ", 1024.0 , intercept , nextX, nextY);

       }

       else{

           double slope = Math.abs(nextY - currentY)/2;
           double intercept;
           if(currentY > 256){//this is the SE
               intercept = slope + currentY;
           }
           else{
               intercept = slope + nextY;
           }
           // modify depending on what hemisphere (NE = newY, SE = oldY)
           coordinates += String.format("L%.5f %.5f L%.5f %.5f ", currentX, currentY , 1024.0 , intercept);
           coordinates += String.format("M%.5f %.5f L%.5f %.5f ", 0.0 , intercept, nextX, nextY);


       }
       System.out.println(coordinates);
       return coordinates;
   }



    public String insertSVG(ArrayList<TripLeg> path) throws IOException{
        readSVG();
        String coordinates = "";
        String startcoordinate = "";
        for(int i = 0; i < path.size(); i++){
            TripLeg leg = path.get(i);
            double[] returnedXY = hemisphereValue(leg.start.latitude, leg.start.longitude); //array of X,Y coordinates
            double svgXcoordinate = returnedXY[0];
            double svgYcoordinate = returnedXY[1];

            double nextSvgXcoordinate = 0.0;
            double nextSvgYcoordinate = 0.0;

            if(i < path.size() - 1){ //set the next coordinates
                nextSvgXcoordinate = hemisphereValue(path.get(i+1).start.latitude, path.get(i+1).start.longitude)[0];
                nextSvgYcoordinate = hemisphereValue(path.get(i+1).start.latitude, path.get(i+1).start.longitude)[1];
            }
            else{//break out or out of bounds will occur
                break;
            }

            if(i == 0) {
                coordinates += "\t<path d=\"M" + String.format("%.5f", svgXcoordinate) + " " + String.format("%.5f", svgYcoordinate) + " "; // create a startpoint
                startcoordinate = "L" +String.format("%.5f", svgXcoordinate) + " " + String.format("%.5f", svgYcoordinate) + " "; //create the first line
            }

            double differenceX = Math.abs(nextSvgXcoordinate - svgXcoordinate);

            if(differenceX > 512){ // if they are over halfway across the world from each other cross the IDL

                coordinates += internationalDL(svgXcoordinate, svgYcoordinate, nextSvgXcoordinate, nextSvgYcoordinate);

            }

            else {// add the line

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
