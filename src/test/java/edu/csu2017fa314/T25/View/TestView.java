package edu.csu2017fa314.T25.View;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;


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
    public void testCoordinateConversion(){

        //test southeast
        double[] southEastValue = v.hemisphereValue(-17.878868,19.782762);
        double[] southEastActual = {568.2709674666667,306.85544675555553};
        assertTrue(Arrays.equals(southEastActual , southEastValue));
        //test north east
        double[] northEastValue = v.hemisphereValue(24.264999389648438,116.0999984741211);
        double[] northEastActual = {842.2399956597222,186.97955729166668};
        assertTrue(Arrays.equals(northEastActual , northEastValue));
        //test north west
        double[] northWestValue = v.hemisphereValue(43.813499450683594,-115.13500213623047);
        double[] northWestActual = {184.5048828125,131.37493489583332};
        assertTrue(Arrays.equals(northWestActual , northWestValue));
        //test south west
        double[] southWestValue = v.hemisphereValue(-22.14109992980957,-70.06289672851562);
        double[] southWestActual = {312.7099826388889,318.9791286892361};
        assertTrue(Arrays.equals(southWestActual , southWestValue));
        //check south pole

        double[] southPoleValue = v.hemisphereValue(-90.0,0.0);
        double[] southPoleActual = {512.0,512.0};
        assertTrue(Arrays.equals(southPoleActual , southPoleValue));


    }


    @Test
    public void testIDL(){

        String testNENW = "L860.23898 105.48930 L1024.00000 116.25237 M0.00000 116.25237 L95.04996 83.96316 ";
        String resultofNENW = v.internationalDL(860.2389845333333,105.48930275555556,95.0499565966222,83.96316189240889);
        assertEquals(testNENW,resultofNENW);

        String testSESW = "L933.17291 282.05113 L1024.00000 293.22226 M0.00000 293.22226 L86.50807 304.39339 ";
        String resultSESQ = v.internationalDL(933.1729066666667,282.0511288888889,86.50807291666666,304.39338650173613);
        assertEquals(testSESW,resultSESQ);

        String testSESWalt = "L106.61545 76.44444 L0.00000 85.77835 M1024.00000 85.77835 L766.09138 95.11225 ";
        String resultSESQalt = v.internationalDL(106.61545138888889,76.44444444444444,766.0913845486111,95.11225043402777);
        assertEquals(testSESWalt,resultSESQalt);

        String testNENWalt = "L591.92397 97.11690 L1024.00000 88.52446 M0.00000 88.52446 L52.79857 79.93202 ";
        String resultNENWalt = v.internationalDL(591.9239680000001,97.11689955555555,52.7985678222222,79.93202039466667);
        assertEquals(testNENWalt,resultNENWalt);




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
