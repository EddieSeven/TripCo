package edu.csu2017fa314.T25;

import edu.csu2017fa314.T25.Model.Model;
import edu.csu2017fa314.T25.Model.TripLeg;
import edu.csu2017fa314.T25.View.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TripCo {

    public static void main(String[] args) throws FileNotFoundException {
        Model model = new Model();
        View viewer = new View();
        model.readCSV(args[0]);

        ArrayList<TripLeg> trips;

        trips = model.calculateDistances();


        try {
            viewer.writeJSON(trips);
            viewer.getCoordinates(args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Welcome to TripCo");
    }

}
