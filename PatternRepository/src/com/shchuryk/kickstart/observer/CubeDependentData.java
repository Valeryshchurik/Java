package com.shchuryk.kickstart.observer;

import com.shchuryk.kickstart.action.CubeAction;
import com.shchuryk.kickstart.entity.Cube;

public class CubeDependentData {
    private double cubeSurfaceArea;
    private double cubeVolume;

    public CubeDependentData(Cube cube) {
        this.cubeSurfaceArea = CubeAction.countCubeSurfaceArea(cube);
        this.cubeVolume = CubeAction.countCubeVolume(cube);
    }

    public void setCubeSurfaceArea(double cubeSurfaceArea) {
        this.cubeSurfaceArea = cubeSurfaceArea;
    }

    public void setCubeVolume(double cubeVolume) {
        this.cubeVolume = cubeVolume;
    }

    public double getCubeVolume() {
        return cubeVolume;
    }

    public double getCubeSurfaceArea() {
        return cubeSurfaceArea;
    }
}
