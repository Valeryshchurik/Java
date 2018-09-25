package com.shchuryk.kickstart.repository;

import com.shchuryk.kickstart.entity.Cube;
import com.shchuryk.kickstart.exception.CubeDataException;
import com.shchuryk.kickstart.observer.CubeDataObserver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CubeSimpleRepository implements CubeRepository {
    private final static Logger LOGGER = LogManager.getLogger(Cube.class);
    private List<Cube> cubesList = new ArrayList<>();
    private static CubeSimpleRepository ourInstance = new CubeSimpleRepository();

    public static CubeSimpleRepository getInstance() {
        return ourInstance;
    }

    private CubeSimpleRepository() {
    }

    public List<Cube> getCubesList() {
        return cubesList;
    }

    public void initialiseRepositoryByList(List<Cube> cubes) {
        removeAll();
        addCubes(cubes);
    }

    @Override
    public void addCube(Cube cube) {
        cubesList.add(cube);
        new CubeDataObserver(cube);
    }

    public void addCubes(List<Cube> cubes) {
        for (Cube cube : cubes) {
            addCube(cube);
        }
    }

    @Override
    public void removeCube(Cube cube) {
        cubesList.remove(cube);
        cube.deleteObservers();
    }

    public void removeAll() {
        Iterator<Cube> it = cubesList.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    @Override
    public void updateCube(Cube currentCube, Cube newCube) {
        removeCube(currentCube);
        addCube(newCube);
    }

    @Override
    public void updateCubeLight(Cube currentCube, Cube newCube) {
        try {
            currentCube.setEdgeLength(newCube.getEdgeLength());
            currentCube.setCenter(newCube.getCenter());
        } catch (CubeDataException e) {
            LOGGER.catching(Level.FATAL, e);
        }
    }

    @Override
    public List<Cube> query(CubeSpecification specification) {
        List<Cube> outputCubeList = new ArrayList<>();
        for (Cube cube : cubesList) {
            if (specification.specified(cube)) {
                outputCubeList.add(cube);
            }
        }
        return outputCubeList;
    }

    public List<Cube> queryWithRemoving(CubeSpecification specification) {
        List<Cube> outputCubeList = new ArrayList<>();
        for (Cube cube : cubesList) {
            if (specification.specified(cube)) {
                outputCubeList.add(cube);
            }
        }
        for (Cube cube : outputCubeList) {
            ourInstance.removeCube(cube);
        }
        return outputCubeList;
    }

    public List<Cube> queryWithSorting(CubeSpecification specification, Comparator<Cube> comparator) {
        List<Cube> outputListCube = query(specification);
        outputListCube.sort(comparator);
        return outputListCube;
    }
}
