package com.shchuryk.kickstart.action;

import com.shchuryk.kickstart.entity.Cube;
import com.shchuryk.kickstart.entity.Point3D;
import com.shchuryk.kickstart.exception.ActionMethodArgumentException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.shchuryk.kickstart.util.CubeProjectConstants.Coordinate;
import static com.shchuryk.kickstart.util.CubeProjectConstants.CoordinatePlane;

public class CubeAction {
    private final static Logger LOGGER = LogManager.getLogger(CubeAction.class);

    private CubeAction() {
    }

    public static double countCubeSurfaceArea(Cube cube) {
        double cubeSurfaceArea = Math.pow(cube.getEdgeLength(), 2) * 6;
        LOGGER.debug("Cube surface area: " + cubeSurfaceArea);
        return cubeSurfaceArea;
    }

    public static double countCubeVolume(Cube cube) {
        double cubeVolume = Math.pow(cube.getEdgeLength(), 3);
        LOGGER.debug("Cube volume: " + cubeVolume);
        return cubeVolume;
    }

    public static double countDistanceToOrigin(Cube cube) {
        return PointAction.countDistance(cube.getCenter(), new Point3D());
    }

    public static double countVolumeRatios(Cube cube, CoordinatePlane coordinatePlane) throws ActionMethodArgumentException {
        double halfEdgeLength = cube.getEdgeLength() / 2;
        double primaryCoordinateAbsoluteValue;
        primaryCoordinateAbsoluteValue = Math.abs(cube.getCenter().getCoordinate(coordinatePlane.getNullCoordinate()));
        if (primaryCoordinateAbsoluteValue >= halfEdgeLength) {
            LOGGER.debug("plane " + coordinatePlane + " doesn't cross the cube " + cube);
            return 0;
        }
        double ratio = Math.abs(primaryCoordinateAbsoluteValue - halfEdgeLength) / (primaryCoordinateAbsoluteValue + halfEdgeLength);
        LOGGER.debug("Volume ratio of parts after having section by coordinate plane " + coordinatePlane + " of the cube " + cube + ":" + ratio);
        return ratio;
    }

    public static boolean isInstanceOfCube(Object object) {
        boolean isInstanceOfCube = object instanceof Cube;
        LOGGER.debug("Is object instance of cube.txt: " + isInstanceOfCube);
        return isInstanceOfCube;
    }

    public static boolean isCubeFaceOnAnyCoordinatePlane(Cube cube) {
        try {
            boolean isCubeFaceOnPlane = isCubeFaceOnCoordinatePlane(cube, CoordinatePlane.XY) ||
                    isCubeFaceOnCoordinatePlane(cube, CoordinatePlane.XZ) ||
                    isCubeFaceOnCoordinatePlane(cube, CoordinatePlane.YZ);
            LOGGER.debug("Is cube face on coordinate plane: " + isCubeFaceOnPlane);
            return isCubeFaceOnPlane;
        } catch (ActionMethodArgumentException e) {
            LOGGER.catching(Level.ERROR, e);
            return false;
        }
    }

    public static boolean isCubeFaceOnCoordinatePlane(Cube cube, CoordinatePlane coordinatePlane) throws ActionMethodArgumentException {
        double halfEdgeLength = cube.getEdgeLength() / 2;
        double primaryCoordinateAbsoluteValue;
        primaryCoordinateAbsoluteValue = Math.abs(cube.getCenter().getCoordinate(coordinatePlane.getNullCoordinate()));
        boolean isCubeFaceOnCoordinatePlane = primaryCoordinateAbsoluteValue == halfEdgeLength;
        LOGGER.debug("Is cube face on plane " + coordinatePlane + ": " + isCubeFaceOnCoordinatePlane);
        return isCubeFaceOnCoordinatePlane;
    }

    public static boolean checkCenterInTheRanges(Cube cube, Coordinate coordinate, double minValue, double maxValue) {
        try {
            return (cube.getCenter().getCoordinate(coordinate) >= minValue) && (cube.getCenter().getCoordinate(coordinate) <= maxValue);
        } catch (ActionMethodArgumentException e) {
            LOGGER.catching(Level.ERROR, e);
            return false;
        }
    }
}
