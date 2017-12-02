package edu.csu2017fa314.T25;

import edu.csu2017fa314.T25.Model.Database;
import edu.csu2017fa314.T25.View.Server;

import java.io.FileNotFoundException;

public class TripCo {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        System.out.println("Welcome to TripCo");

        Database database = new Database("cedward", "829875838", "jdbc:mysql://faure.cs.colostate.edu/cs314?useLegacyDatetimeCode=false&serverTimezone=UTC");
        Server s = new Server(database);
        s.serve();
    }
}
