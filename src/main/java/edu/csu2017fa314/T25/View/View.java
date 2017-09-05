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

   public void writeJSON(ArrayList<String> compDistances /*[TODO] Replace with actual data structure*/) throws IOException {
      Gson gsonObj = new Gson();

      FileWriter writer = new FileWriter("itinerary.json");
      gsonObj.toJson(computedDistances, writer);
   }
}
