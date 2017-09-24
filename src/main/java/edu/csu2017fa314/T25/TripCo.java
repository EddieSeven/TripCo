package edu.csu2017fa314.T25;

import edu.csu2017fa314.T25.Model.Model;
import edu.csu2017fa314.T25.Model.TripLeg;
import edu.csu2017fa314.T25.View.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TripCo
{

   private String name = "";

   public String getName()
   {
      return name;
   }

   public String getMessage()
   {
      if (name == "")
      {
         return "Hello!";
      }
      else
      {
         return "Hello " + name + "!";
      }
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public static void main(String[] args)throws FileNotFoundException {
       Model model = new Model();
       View viewer = new View();
       model.readCSV(args[0]);

       ArrayList<TripLeg> trips = new ArrayList<>();

       trips = model.calculateDistances();


       try {
           viewer.writeJSON(trips);
           viewer.convertCoordinates(model.breweriesList, args[1]);
       } catch (IOException e) {
           e.printStackTrace();
       }

       System.out.println("Welcome to TripCo");
   }

}
