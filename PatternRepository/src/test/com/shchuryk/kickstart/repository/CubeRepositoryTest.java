package test.com.shchuryk.kickstart.repository;

import com.shchuryk.kickstart.creator.CubeCreator;
import com.shchuryk.kickstart.entity.Cube;
import com.shchuryk.kickstart.entity.Point3D;
import com.shchuryk.kickstart.exception.CubeDataException;
import com.shchuryk.kickstart.observer.CubeParameterStorage;
import com.shchuryk.kickstart.repository.CubeSimpleRepository;
import com.shchuryk.kickstart.repository.CubeSpecificationByContactingPlane;
import com.shchuryk.kickstart.repository.CubeSpecificationByCoordinateValue;
import com.shchuryk.kickstart.repository.CubeSpecificationByVolume;
import com.shchuryk.kickstart.validator.CubeValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.shchuryk.kickstart.util.CubeProjectConstants.Coordinate;
import static com.shchuryk.kickstart.util.CubeProjectConstants.CoordinatePlane;
import static com.shchuryk.kickstart.util.comparator.CubeComparator.cubeDistanceToOriginComparator;
import static com.shchuryk.kickstart.util.comparator.CubeComparator.cubeSizeComparator;

public class CubeRepositoryTest {

    private List<Cube> cubes = new ArrayList<>();
    private CubeParameterStorage cubeDataMap = CubeParameterStorage.getInstance();
    private CubeCreator cubeCreator = CubeCreator.getInstance();
    private CubeSimpleRepository cubeSimpleRepository = CubeSimpleRepository.getInstance();
    private List<String> testStrings = new java.util.ArrayList<>(java.util.Arrays.asList(
            "1.0 2.0 15.0 edge 6.2",
            "1.0 2.0 3.0 edge 7.1",
            "1.0 2.0 3.0 edge 13.0",
            "-1.0 2.4 3.2 edge 1",
            "4 4 4 edge 8",
            "-10 2 3 edge 17",
            "0 0 0 edge 1",
            "0.1 0 0 edge 0.2",
            "25 16 11.1 edge 0.5"
    ));

    @BeforeSuite
    public void initializeCubes() {
        CubeValidator cubeValidator = CubeValidator.getInstance();
        testStrings.removeIf(line -> !cubeValidator.validateCubeLine(line));
        cubes = cubeCreator.createCubes(testStrings);
    }

    @BeforeMethod
    public void reloadRepository() {
        cubeSimpleRepository.initialiseRepositoryByList(cubes);
    }

    @Test
    public void addCubeRepositoryTest() {
        Cube cube = new Cube(new Point3D(1.2, 3, 3), 4);
        cubeSimpleRepository.addCube(cube);
        List<Cube> actualList = cubeSimpleRepository.getCubesList();
        List<Cube> expectedList = Arrays.asList(
                new Cube(new Point3D(1.0, 2.0, 15.0), 6.2),
                new Cube(new Point3D(1.0, 2.0, 3.0), 7.1),
                new Cube(new Point3D(1.0, 2.0, 3.0), 13.0),
                new Cube(new Point3D(-1.0, 2.4, 3.2), 1),
                new Cube(new Point3D(4, 4, 4), 8),
                new Cube(new Point3D(-10, 2.0, 3), 17),
                new Cube(new Point3D(0, 0, 0), 1),
                new Cube(new Point3D(0.1, 0, 0), 0.2),
                new Cube(new Point3D(25, 16, 11.1), 0.5),
                new Cube(new Point3D(1.2, 3, 3), 4));
        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void removeCubeRepositoryTest() {
        Cube cubeToDelete = new Cube(new Point3D(0, 0, 0), 1);
        cubeSimpleRepository.removeCube(cubeToDelete);
        List<Cube> actualList = cubeSimpleRepository.getCubesList();
        List<Cube> expectedList = Arrays.asList(
                new Cube(new Point3D(1.0, 2.0, 15.0), 6.2),
                new Cube(new Point3D(1.0, 2.0, 3.0), 7.1),
                new Cube(new Point3D(1.0, 2.0, 3.0), 13.0),
                new Cube(new Point3D(-1.0, 2.4, 3.2), 1),
                new Cube(new Point3D(4, 4, 4), 8),
                new Cube(new Point3D(-10, 2.0, 3), 17),
                new Cube(new Point3D(0.1, 0, 0), 0.2),
                new Cube(new Point3D(25, 16, 11.1), 0.5));
        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void updateCubeRepositoryTest() {
        List<Cube> cubesListBefore = cubeSimpleRepository.getCubesList();
        List<Cube> cubesToChange = cubeSimpleRepository.query(new CubeSpecificationByContactingPlane(CoordinatePlane.XY, CoordinatePlane.XZ, CoordinatePlane.YZ));
        for (Cube cube : cubesToChange) {
            cubeSimpleRepository.updateCube(cube, new Cube(cube.getCenter(), cube.getEdgeLength() * 2));
        }
        List<Cube> actualList = cubeSimpleRepository.getCubesList();
        List<Cube> expectedList = Arrays.asList(
                new Cube(new Point3D(1.0, 2.0, 15.0), 6.2),
                new Cube(new Point3D(1.0, 2.0, 3.0), 7.1),
                new Cube(new Point3D(1.0, 2.0, 3.0), 13.0),
                new Cube(new Point3D(-1.0, 2.4, 3.2), 2),
                new Cube(new Point3D(-10, 2.0, 3), 17),
                new Cube(new Point3D(0, 0, 0), 1),
                new Cube(new Point3D(0.1, 0, 0), 0.2),
                new Cube(new Point3D(25, 16, 11.1), 0.5),
                new Cube(new Point3D(4, 4, 4), 16));
        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void updateCubeLightRepositoryTest() {
        int testIndexCube = 3;
        Cube cube = cubes.get(testIndexCube);
        try {
            cube.setEdgeLength(2);
        } catch (CubeDataException e) {
            e.printStackTrace();
        }
        double expected = 24;
        double actual = cubeDataMap.get(testIndexCube).getCubeSurfaceArea();
        double delta = 0.001;
        Assert.assertEquals(actual, expected, delta);
    }

    @Test
    public void queryContactingPlaneTest() {
        List<Cube> actualList = cubeSimpleRepository.query(new CubeSpecificationByContactingPlane(CoordinatePlane.YZ));
        List<Cube> expectedList = Arrays.asList(
                new Cube(new Point3D(4.0, 4.0, 4.0), 8.0),
                new Cube(new Point3D(0.1, 0, 0), 0.2));
        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void querySizeSortedContactingPlaneTest() {
        List<Cube> actualList = cubeSimpleRepository.queryWithSorting(new CubeSpecificationByContactingPlane(CoordinatePlane.YZ), cubeSizeComparator);
        List<Cube> expectedList = Arrays.asList(
                new Cube(new Point3D(0.1, 0, 0), 0.2),
                new Cube(new Point3D(4.0, 4.0, 4.0), 8.0));
        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void queryDistanceSortedVolumeTest() {
        List<Cube> actualList = cubeSimpleRepository.queryWithSorting(new CubeSpecificationByVolume(2, 500), cubeDistanceToOriginComparator);
        List<Cube> expectedList = Arrays.asList(
                new Cube(new Point3D(1.0, 2.0, 3.0), 7.1),
                new Cube(new Point3D(1.0, 2.0, 15.0), 6.2));
        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void queryCoordinateValueTest() {
        List<Cube> actualList = cubeSimpleRepository.query(new CubeSpecificationByCoordinateValue(Coordinate.Y, 2.4, 3));
        List<Cube> expectedList = Arrays.asList(
                new Cube(new Point3D(-1.0, 2.4, 3.2), 1));
        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void removeQueryTest() {
        cubeSimpleRepository.queryWithRemoving(new CubeSpecificationByVolume(1, 1));
        List<Cube> actualList = cubeSimpleRepository.getCubesList();
        List<Cube> expectedList = Arrays.asList(
                new Cube(new Point3D(1.0, 2.0, 15.0), 6.2),
                new Cube(new Point3D(1.0, 2.0, 3.0), 7.1),
                new Cube(new Point3D(1.0, 2.0, 3.0), 13.0),
                new Cube(new Point3D(4, 4, 4), 8),
                new Cube(new Point3D(-10, 2.0, 3), 17),
                new Cube(new Point3D(0.1, 0, 0), 0.2),
                new Cube(new Point3D(25, 16, 11.1), 0.5));
        Assert.assertEquals(actualList, expectedList);
    }
}
