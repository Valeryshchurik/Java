package com.shchuryk.kickstart.entity;

import com.shchuryk.kickstart.exception.ActionMethodArgumentException;
import com.shchuryk.kickstart.util.CubeProjectConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Point3D {
    private static Logger LOGGER = LogManager.getLogger(Point3D.class);

    private double x;
    private double y;
    private double z;

    public Point3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        LOGGER.debug("Creation of origin point: " + this);
    }

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        LOGGER.debug("Creation of point: " + this);
    }

    public double getCoordinate(CubeProjectConstants.Coordinate coordinate) throws ActionMethodArgumentException {
        double returnValue;
        switch (coordinate) {
            case X:
                returnValue = getX();
                break;
            case Y:
                returnValue = getY();
                break;
            case Z:
                returnValue = getZ();
                break;
            default:
                throw new ActionMethodArgumentException("Argument (coordinate plane) is invalid");
        }
        return returnValue;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setCoordinate(CubeProjectConstants.Coordinate coordinate, double value) throws ActionMethodArgumentException {
        switch (coordinate) {
            case X:
                setX(value);
                break;
            case Y:
                setY(value);
                break;
            case Z:
                setZ(value);
                break;
            default:
                throw new ActionMethodArgumentException("Argument (coordinate plane) is invalid");
        }
    }

    public void setX(double x) {
        LOGGER.debug("Set value to x: " + x);
        this.x = x;
    }

    public void setY(double y) {
        LOGGER.debug("Set value to y: " + y);
        this.y = y;
    }

    public void setZ(double z) {
        LOGGER.debug("Set value to z: " + z);
        this.z = z;
    }

    @Override
    public String toString() {
        return "{" + getX() + "; " + getY() + "; " + getZ() + "}";
    }

    @Override
    public int hashCode() {
        long bits = Double.doubleToLongBits(getX());
        bits ^= Double.doubleToLongBits(getY()) * 31;
        bits ^= Double.doubleToLongBits(getZ()) * 31;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Point3D compared = (Point3D) obj;
        return compared.x == this.x && compared.y == this.y && compared.z == this.z;
    }
}
