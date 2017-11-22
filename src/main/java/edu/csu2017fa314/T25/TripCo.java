package edu.csu2017fa314.T25;

import edu.csu2017fa314.T25.Model.Database;
import edu.csu2017fa314.T25.View.Server;

import java.io.FileNotFoundException;

public class TripCo {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        boolean isTest = false;
        if (args.length > 0 && args[args.length - 1].equals("test")) {
            isTest = true;
        }

        System.out.println("Welcome to TripCo");

        if (isTest) {
            Database db = new Database("", "", "");
            Server s = new Server(db);
            s.serveTest();
        } else {
            Database db = new Database("cedward", "829875838", "jdbc:mysql://faure.cs.colostate.edu/cs314?useLegacyDatetimeCode=false&serverTimezone=UTC");
            Server s = new Server(db);
            s.serve();
        }
    }
}
