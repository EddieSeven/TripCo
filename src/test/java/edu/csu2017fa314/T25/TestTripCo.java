package edu.csu2017fa314.T25;
import static org.junit.Assert.*;

import edu.csu2017fa314.T25.Model.DatabaseDriver;
import edu.csu2017fa314.T25.View.Server;
import org.junit.Before;
import org.junit.Test;


public class TestTripCo {

   private TripCo t;

   @Before
   public void setUp() throws Exception {
      t = new TripCo();
   }

   @Test
   public void testTripCo() throws ClassNotFoundException {
      DatabaseDriver db = new DatabaseDriver();
      Server s  = new Server(db);
      s.serveTest();
   }

}
