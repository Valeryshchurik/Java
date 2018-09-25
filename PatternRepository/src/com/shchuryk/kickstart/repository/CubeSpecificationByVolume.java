package com.shchuryk.kickstart.repository;

import com.shchuryk.kickstart.entity.Cube;
import com.shchuryk.kickstart.observer.CubeParameterStorage;

public class CubeSpecificationByVolume implements CubeSpecification {

    private double minValue;
    private double maxValue;

    public CubeSpecificationByVolume(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public boolean specified(Cube cube) {
        CubeParameterStorage cubeParameterStorage = CubeParameterStorage.getInstance();
        double volume = cubeParameterStorage.get(cube.getId()).getCubeVolume();
        return volume >= minValue && volume <= maxValue;
    }
}
