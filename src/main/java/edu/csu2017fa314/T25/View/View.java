package edu.csu2017fa314.T25.View;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class View
{
   ArrayList<String> computedDistances = new ArrayList<String>(); /*[TODO] Replace with actual data structure*/

   private int totalDistance;

   public void setTotalDistance(int distance) 
   {
       totalDistance = distance; 
   }

   public int getTotalDistance() 
   {
      return totalDistance;
   }


   // Takes in a data structure consisting of a collection of data with three fields: id for one point, id for another point, and the distance between the two.
   // Uses the gson class to turn the data object into JSON form, and then writes that to a local JSON file "itinerary.json" that is used to show the end user the trip. [TODO] Give credit for GSON
   public void writeJSON(ArrayList<String> compDistances /*[TODO] Replace with actual data structure*/) throws IOException {
      Gson gsonObj = new Gson();

      FileWriter writer = new FileWriter("itinerary.json");
      gsonObj.toJson(computedDistances, writer);
      writer.flush();
      writer.close();

   }
}
