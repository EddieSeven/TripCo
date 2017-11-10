package edu.csu2017fa314.T25;

import edu.csu2017fa314.T25.Model.DatabaseDriver;
import edu.csu2017fa314.T25.Model.TripLeg;
import edu.csu2017fa314.T25.View.View;
import edu.csu2017fa314.T25.View.Server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import static spark.Spark.*;

public class TripCo {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        /*Model model = new Model();
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
		*/
		boolean isTest = false;

		if (args.length > 0 && args[args.length-1].equals("test")) {
			isTest = true;
		}

        System.out.println("Welcome to TripCo");
		
		if (isTest) {
			DatabaseDriver db = new DatabaseDriver("", "", "");
			Server s  = new Server(db);
			s.serveTest();
		}
		else {
			DatabaseDriver db = new DatabaseDriver("cedward", "829875838" , "jdbc:mysql://faure.cs.colostate.edu/cs314?useLegacyDatetimeCode=false&serverTimezone=UTC");
			Server s  = new Server(db);
			s.serve();
		}
    }
}
