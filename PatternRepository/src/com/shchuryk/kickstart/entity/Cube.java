package com.shchuryk.kickstart.entity;

import com.shchuryk.kickstart.exception.CubeDataException;
import com.shchuryk.kickstart.observer.SimpleObservable;
import com.shchuryk.kickstart.util.CubeIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cube extends SimpleObservable {
    private final static Logger LOGGER = LogManager.getLogger(Cube.class);
    private double edgeLength;
    private Point3D center;
    private int id;

    public int getId() {
        return id;
    }

    public Cube(Point3D center, double edge_length) {
        this.center = center;
        this.edgeLength = edge_length;
        LOGGER.debug("Cube: " + this);
        id = CubeIdGenerator.getNextID();
    }

    public double getEdgeLength() {
        return edgeLength;
    }

    public void setEdgeLength(double edgeLength) throws CubeDataException {
        if (edgeLength <= 0) {
            throw new CubeDataException("Length of the edge should be positive!");
        }
        LOGGER.debug("Set edge_length value to: " + edgeLength);
        this.edgeLength = edgeLength;
        setChanged();
        notifyObservers();
    }

    public Point3D getCenter() {
        return center;
    }

    public void setCenter(Point3D center) {
        LOGGER.debug("Set center value to: " + center);
        this.center = center;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Edge length = " + getEdgeLength() + ", " + getCenter();
    }

    @Override
    public int hashCode() {
        long bits = center.hashCode();
        bits ^= Double.doubleToLongBits(getEdgeLength()) * 31;
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
        Cube compared = (Cube) obj;
        return compared.edgeLength == this.edgeLength && compared.center.equals(this.center);
    }
}
