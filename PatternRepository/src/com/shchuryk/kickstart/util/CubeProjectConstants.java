package com.shchuryk.kickstart.util;

public class CubeProjectConstants {
    public static final String POINT3D_PATTERN = "\\s*(\\-?\\d+(?>\\.\\d+)?\\s+)(\\-?\\d+(?>\\.\\d+)?\\s+)(\\-?\\d+(?>\\.\\d+)?\\s*)";
    public static final String CUBE_PATTERN = "^" + POINT3D_PATTERN + "\\s+edge\\s*(\\d+(?>\\.\\d+)?\\s*)$";
    public static final int NUMBER_OF_CUBE_PARAMS = 4;
    public static final int NUMBER_OF_COORDINATE_PLANES = 3;

    public enum Coordinate {X, Y, Z}

    public enum CoordinatePlane {
        XY(Coordinate.Z), XZ(Coordinate.Y), YZ(Coordinate.X);
        Coordinate nullCoordinate;

        CoordinatePlane(Coordinate coordinate) {
            nullCoordinate = coordinate;
        }

        public Coordinate getNullCoordinate() {
            return nullCoordinate;
        }
    }
}
