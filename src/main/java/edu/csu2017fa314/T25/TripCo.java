package edu.csu2017fa314.T25;

import edu.csu2017fa314.T25.Model.Model;

import java.io.FileNotFoundException;

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

       model.readCSV(args[0]);

//      System.out.println(model.breweriesList.size() + " Length of brew list");
//      for(int i = 0;i < model.breweriesList.size();i++) {
//         System.out.println(model.breweriesList.get(i).elevation + " STUDENT");
//      }

       System.out.println(args[0]);
       System.out.println("Welcome to TripCo");
   }

}
