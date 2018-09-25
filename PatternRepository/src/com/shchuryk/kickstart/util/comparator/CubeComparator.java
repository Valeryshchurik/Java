package com.shchuryk.kickstart.util.comparator;

import com.shchuryk.kickstart.action.CubeAction;
import com.shchuryk.kickstart.entity.Cube;

import java.util.Comparator;

public class CubeComparator {
    public static final Comparator<Cube> cubeSizeComparator = Comparator.comparingDouble(Cube::getEdgeLength);
    public static final Comparator<Cube> cubeDistanceToOriginComparator = Comparator.comparingDouble(CubeAction::countDistanceToOrigin);
}
