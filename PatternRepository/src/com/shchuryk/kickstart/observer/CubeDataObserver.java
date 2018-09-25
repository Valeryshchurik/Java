package com.shchuryk.kickstart.observer;

import com.shchuryk.kickstart.action.CubeAction;
import com.shchuryk.kickstart.entity.Cube;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CubeDataObserver implements CubeObserver {
    private static final Logger LOGGER = LogManager.getLogger(CubeDataObserver.class);
    private SimpleMap<CubeDependentData> dataStorage;

    public CubeDataObserver(Cube cube) {
        dataStorage = CubeParameterStorage.getInstance();
        CubeDependentData cubeData = new CubeDependentData(cube);
        dataStorage.put(cube.getId(), cubeData);
        cube.addObserver(this);
        cubeData.setCubeSurfaceArea(CubeAction.countCubeSurfaceArea(cube));
        cubeData.setCubeVolume(CubeAction.countCubeVolume(cube));
    }

    @Override
    public void update(SimpleObservable observable) {
        if (observable.getClass() != Cube.class) {
            LOGGER.error("Incorrect argument type. Invoked update has been ignored.");
            return;
        }
        Cube cube = (Cube) observable;
        CubeDependentData cubeData = dataStorage.get(cube.getId());
        cubeData.setCubeSurfaceArea(CubeAction.countCubeSurfaceArea(cube));
        cubeData.setCubeVolume(CubeAction.countCubeVolume(cube));
    }

    @Override
    public void cleanUp(SimpleObservable observable) {
        if (observable.getClass() != Cube.class) {
            LOGGER.error("Incorrect argument type. Invoked cleanUP has been ignored.");
            return;
        }
        Cube cube = (Cube) observable;
        dataStorage.remove(cube.getId());
    }
}
