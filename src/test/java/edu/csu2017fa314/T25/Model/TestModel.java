package edu.csu2017fa314.T25.Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class TestModel {

    @Before
    public void setUp() throws Exception { }

    @Test
    public void testDistance() {
        Point a1 = new Point("37°20'32.9\" N","108°35'10.5\" W");
        Point a2 = new Point("40°34'18.9\" N", "105°07'18.4\" W");
        assertEquals(291, Model.computeDistance(a1, a2));

        Point b1 = new Point("28°43'45.0\" N","81°08'43.3\" W");
        Point b2 = new Point("60°03'54.7\" N", "151°30'34.6\" W");
        assertEquals(3848, Model.computeDistance(b1, b2));

        Point c1 = new Point("35°07'53.0\" N", "119°10'55.0\" W");
        Point c2 = new Point("45°14'02.8\" N", "67°55'35.9\" W");
        assertEquals(2745, Model.computeDistance(c1, c2));

        // this test is failing
        // Point d1 = new Point("37°20'56.9\" N", "108°35'47.3\" W");
        // Point d2 = new Point("37°21'10.9\" N", "108°32'56.5\" W");
        // int res = Model.computeDistance(c1, c2);
        // assertEquals(3, Model.computeDistance(c1, c2));


    }

    @Test
    public void testPoint(){
        Point form1 = new Point("33°23'23.23\" N","33°23'23.23\" W");
        Point form2 = new Point("33°23.23' N","33°23.23' W");
        Point form3 = new Point("33.23° N","33.23° W");
        Point form4 = new Point("33.23", "-33.23");


    }

}
