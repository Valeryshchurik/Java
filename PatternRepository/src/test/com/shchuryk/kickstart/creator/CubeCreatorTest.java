package test.com.shchuryk.kickstart.creator;

import com.shchuryk.kickstart.creator.CubeCreator;
import com.shchuryk.kickstart.entity.Cube;
import com.shchuryk.kickstart.entity.Point3D;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CubeCreatorTest {
    private CubeCreator cubeCreator = CubeCreator.getInstance();
    private List<String> testStrings = Arrays.asList(
            "1.0 2.0 3.0 edge 6.1",
            "1.0 2.0 3.0 edge 13.0",
            "-1.0 2.4 3.2 edge 1.3",
            "1.0 2.0 3.0 edge 6.2");

    @Test
    public void createCubesTest() {
        List<Cube> actualCubes = cubeCreator.createCubes(testStrings);
        List<Cube> expectedCubes = Arrays.asList(
                new Cube(new Point3D(1.0, 2.0, 3.0), 6.1),
                new Cube(new Point3D(1.0, 2.0, 3.0), 13.0),
                new Cube(new Point3D(-1.0, 2.4, 3.2), 1.3),
                new Cube(new Point3D(1.0, 2.0, 3.0), 6.2));
        Assert.assertEquals(expectedCubes, actualCubes);
    }
}
