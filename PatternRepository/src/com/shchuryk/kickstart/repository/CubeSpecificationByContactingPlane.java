package com.shchuryk.kickstart.repository;

import com.shchuryk.kickstart.action.CubeAction;
import com.shchuryk.kickstart.entity.Cube;
import com.shchuryk.kickstart.exception.ActionMethodArgumentException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.shchuryk.kickstart.util.CubeProjectConstants.CoordinatePlane;
import static com.shchuryk.kickstart.util.CubeProjectConstants.NUMBER_OF_COORDINATE_PLANES;

public class CubeSpecificationByContactingPlane implements CubeSpecification {
    private final static Logger LOGGER = LogManager.getLogger(CubeSpecificationByContactingPlane.class);
    private List<CoordinatePlane> coordinatePlanes = new ArrayList<>(NUMBER_OF_COORDINATE_PLANES);

    public CubeSpecificationByContactingPlane(CoordinatePlane coordinatePlane) {
        coordinatePlanes.add(coordinatePlane);
    }

    public CubeSpecificationByContactingPlane(CoordinatePlane firstCoordinatePlane, CoordinatePlane secondCoordinatePlane) {
        coordinatePlanes.add(firstCoordinatePlane);
        coordinatePlanes.add(secondCoordinatePlane);
    }

    public CubeSpecificationByContactingPlane(CoordinatePlane firstCoordinatePlane, CoordinatePlane secondCoordinatePlane, CoordinatePlane thirdCoordinatePlane) {
        coordinatePlanes.add(firstCoordinatePlane);
        coordinatePlanes.add(secondCoordinatePlane);
        coordinatePlanes.add(thirdCoordinatePlane);
    }

    @Override
    public boolean specified(Cube cube) {
        Iterator<CoordinatePlane> it = coordinatePlanes.iterator();
        do {
            CoordinatePlane coordinatePlane = it.next();
            try {
                if (!CubeAction.isCubeFaceOnCoordinatePlane(cube, coordinatePlane)) {
                    return false;
                }
            } catch (ActionMethodArgumentException e) {
                LOGGER.catching(Level.ERROR, e);
            }
        }
        while (it.hasNext());
        return true;
    }
}
