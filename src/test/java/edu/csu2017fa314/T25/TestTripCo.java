package edu.csu2017fa314.T25;

import edu.csu2017fa314.T25.Model.Database;
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
      Database db = new Database();
      Server s  = new Server(db);
      s.serveTest();
   }

}
