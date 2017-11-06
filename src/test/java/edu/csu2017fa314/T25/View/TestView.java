package edu.csu2017fa314.T25.View;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;


public class TestView
{
    private View v;

    String SVGPath = "/Data/col.svg";

    @Before
    public void setUp() throws Exception 
    {
        v = new View();
    }


    @Test
    public void testCoordinateConversion() throws FileNotFoundException{

        //test southeast
        assertEquals( new double[]{568.2709674666667,306.85544675555553} , v.hemisphereValue(-17.878868,19.782762 ));
        //test north east
        assertEquals(new double[]{842.2399956597222,186.97955729166668}, v.hemisphereValue(24.264999389648438,116.0999984741211 ));
        //test north west
        assertEquals(new double[]{184.5048828125,131.37493489583332}, v.hemisphereValue(43.813499450683594,-115.13500213623047 ));
        //test south west
        assertEquals(new double[]{312.7099826388889,318.9791286892361}, v.hemisphereValue(-22.14109992980957, -70.06289672851562));
        //check south pole
        assertEquals(new double[]{512.0,512.0}, v.hemisphereValue(-90.0, 0.0));







    }


    @Test
    public void testSetDistance()
    {
        v.setTotalDistance(4);
        assertTrue(v.getTotalDistance() == 4);
        v.setTotalDistance(-4);
        assertTrue(v.getTotalDistance() == -4);
    }

}
