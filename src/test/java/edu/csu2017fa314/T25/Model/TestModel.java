package edu.csu2017fa314.T25.Model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestModel 
{
    private Model model;

    @Before
    public void setUp() throws Exception
    {
        model = new Model();
    }

    @Test
    public void testDistance() {
        Point a1 = new Point("37°20'32.9\" N","108°35'10.5\" W");
        Point a2 = new Point("40°34'18.9\" N", "105°07'18.4\" W");
        assertEquals(291, model.computeDistance(a1, a2));

        Point b1 = new Point("28°43'45.0\" N","81°08'43.3\" W");
        Point b2 = new Point("60°03'54.7\" N", "151°30'34.6\" W");
        assertEquals(3848, model.computeDistance(b1, b2));


    }

}
