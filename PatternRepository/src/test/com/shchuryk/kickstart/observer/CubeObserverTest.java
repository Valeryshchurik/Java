package test.com.shchuryk.kickstart.observer;

import com.shchuryk.kickstart.creator.CubeCreator;
import com.shchuryk.kickstart.entity.Cube;
import com.shchuryk.kickstart.observer.CubeDataObserver;
import com.shchuryk.kickstart.observer.CubeParameterStorage;
import com.shchuryk.kickstart.exception.CubeDataException;
import com.shchuryk.kickstart.validator.CubeValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.fail;

public class CubeObserverTest {

    private List<Cube> cubes = new ArrayList<>();
    private CubeParameterStorage cubeDataMap = CubeParameterStorage.getInstance();
    private int testIndexCube;
    private CubeCreator cubeCreator = CubeCreator.getInstance();
    private double delta = 0.001;
    private List<String> testStrings = Arrays.asList(
            "1.0 2.0 3.0 edge 6.1",
            "1.0 2.0 3.0 edge 13.0",
            "-1.0 2.4 3.2 edge 1.3",
            "1.0 2.0 3.0 edge 6.2");

    @BeforeClass
    public void initializeTools() {
        CubeValidator cubeValidator = CubeValidator.getInstance();
        testStrings.removeIf(line -> !cubeValidator.validateCubeLine(line));
        cubes = cubeCreator.createCubes(testStrings);
        for (Cube cube : cubes) {
            new CubeDataObserver(cube);
        }
    }

    @Test
    public void observerSurfaceAreaTest() {
        testIndexCube = 3;
        Cube cube = cubes.get(testIndexCube);
        try {
            cube.setEdgeLength(2);
        } catch (CubeDataException e) {
            e.printStackTrace();
        }
        double expected = 24;
        double actual = cubeDataMap.get(testIndexCube).getCubeSurfaceArea();
        Assert.assertEquals(actual, expected, delta);
    }

    @Test
    public void observerVolumeTest() {
        testIndexCube = 2;
        Cube cube = cubes.get(testIndexCube);
        try {
            cube.setEdgeLength(10);
        } catch (CubeDataException e) {
            fail();
        }
        double expected = 1000;
        double actual = cubeDataMap.get(testIndexCube).getCubeVolume();
        Assert.assertEquals(actual, expected, delta);
    }
}
