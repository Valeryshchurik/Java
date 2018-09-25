package com.shchuryk.kickstart.util;

public class CubeIdGenerator {
    private static int globalCubeID = 0;

    public static int getNextID() {
        return globalCubeID++;
    }
}
