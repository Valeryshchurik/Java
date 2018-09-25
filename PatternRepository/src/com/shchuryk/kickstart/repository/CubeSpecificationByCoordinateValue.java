package com.shchuryk.kickstart.repository;

import com.shchuryk.kickstart.action.CubeAction;
import com.shchuryk.kickstart.entity.Cube;

import static com.shchuryk.kickstart.util.CubeProjectConstants.Coordinate;

public class CubeSpecificationByCoordinateValue implements CubeSpecification {

    private Coordinate coordinate;
    private double minValue;
    private double maxValue;

    public CubeSpecificationByCoordinateValue(Coordinate coordinate, double minValue, double maxValue) {
        this.coordinate = coordinate;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public boolean specified(Cube cube) {
        return CubeAction.checkCenterInTheRanges(cube, coordinate, minValue, maxValue);
    }
}
