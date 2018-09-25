package com.shchuryk.kickstart.action;

import com.shchuryk.kickstart.entity.Point3D;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PointAction {
    private static Logger LOGGER = LogManager.getLogger(PointAction.class);

    private PointAction() {
    }

    public static double countDistance(Point3D a, Point3D b) {
        double distance = Math.hypot(Math.hypot(a.getX() - b.getX(), a.getY() - b.getY()), a.getZ() - b.getZ());
        LOGGER.debug("Distance between points " + a + " and " + b + " is " + distance);
        return distance;
    }
}
