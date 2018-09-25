package com.shchuryk.kickstart.creator;

import com.shchuryk.kickstart.entity.Cube;
import com.shchuryk.kickstart.entity.Point3D;
import com.shchuryk.kickstart.exception.CubeDataException;
import com.shchuryk.kickstart.parser.CubeParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CubeCreator {
    private static Logger LOGGER = LogManager.getLogger(Cube.class);

    private static CubeCreator ourInstance = new CubeCreator();

    public static CubeCreator getInstance() {
        return ourInstance;
    }

    private CubeCreator() {
        String a;
    }

    public List<Cube> createCubes(List<String> inputLines) {
        List<Cube> cubes = new ArrayList<>();
        for (String line : inputLines) {
            try {
                Cube cube = createCube(CubeParser.parseCubeLine(line));
                cubes.add(cube);
            } catch (CubeDataException e) {
                LOGGER.catching(Level.ERROR, e);
            }
        }
        if (!cubes.isEmpty()) {
            LOGGER.info("Cubes were created successfully");
        } else {
            LOGGER.warn("No cube was created");
        }
        return cubes;
    }

    private Cube createCube(double[] cubeParams) {
        Point3D point = new Point3D(cubeParams[0], cubeParams[1], cubeParams[2]);
        Cube cube = new Cube(point, cubeParams[3]);
        LOGGER.debug("Generated cube: " + cube);
        return cube;
    }

}
