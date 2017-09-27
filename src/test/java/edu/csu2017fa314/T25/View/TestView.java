package edu.csu2017fa314.T25.View;
import static org.junit.Assert.*;

import edu.csu2017fa314.T25.Model.Model;
import javafx.scene.shape.SVGPath;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;


public class TestView
{
    Model model;
    private View v;

    String SVGPath = "/Data/col.svg";

    @Before
    public void setUp() throws Exception 
    {
        model = new Model();
        v = new View();
    }


    @Test
    public void testCoordinateConversion() throws FileNotFoundException{
//        assertEquals(1066.6073 , v.convertCoordinates(model.breweriesList, SVGPath));
//        assertEquals(779.5144, v.convertCoordinates(model.breweriesList, SVGPath));
//        assertEquals(34.74561, v.convertCoordinates(model.breweriesList, SVGPath));
//        assertEquals(34.90332, v.convertCoordinates(model.breweriesList, SVGPath));

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
