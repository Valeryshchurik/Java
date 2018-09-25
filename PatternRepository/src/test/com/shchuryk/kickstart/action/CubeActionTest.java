package test.com.shchuryk.kickstart.action;

import com.shchuryk.kickstart.action.CubeAction;
import com.shchuryk.kickstart.entity.Cube;
import com.shchuryk.kickstart.entity.Point3D;
import com.shchuryk.kickstart.exception.ActionMethodArgumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.shchuryk.kickstart.util.CubeProjectConstants.CoordinatePlane;
import static org.testng.Assert.fail;

public class CubeActionTest {
    private double delta = 0.00001;

    @Test
    public void countCubeSurfaceAreaTest() {
        Cube cube = new Cube(new Point3D(0, -1.11, 2.2), 3.3);
        double expected = 65.34;
        double actual = CubeAction.countCubeSurfaceArea(cube);
        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void countCubeVolumeTest() {
        Cube cube = new Cube(new Point3D(3.2, 4, 6.2), 4.2);
        double expected = 74.088;
        double actual = CubeAction.countCubeVolume(cube);
        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void countVolumeRatiosTest() {
        Cube cube = new Cube(new Point3D(7.2, 2, 6.2), 5.2);
        double expected = 0.1304347826;
        try {
            double actual = CubeAction.countVolumeRatios(cube, CoordinatePlane.XZ);
            Assert.assertEquals(expected, actual, delta);
        } catch (ActionMethodArgumentException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isCubeFaceOnOneOfTheCoordinatePlaneTest() {
        Cube cube = new Cube(new Point3D(0, 1, 2), 2);
        Assert.assertTrue(CubeAction.isCubeFaceOnAnyCoordinatePlane(cube));
    }
}