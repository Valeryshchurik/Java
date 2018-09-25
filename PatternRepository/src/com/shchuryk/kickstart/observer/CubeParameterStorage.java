package com.shchuryk.kickstart.observer;

import java.util.HashMap;
import java.util.Map;

public class CubeParameterStorage implements SimpleMap<CubeDependentData> {
    private Map<Integer, CubeDependentData> cubeDependentDataMap;
    private static CubeParameterStorage ourInstance = new CubeParameterStorage();

    public static CubeParameterStorage getInstance() {
        return ourInstance;
    }

    private CubeParameterStorage() {
        cubeDependentDataMap = new HashMap<>();
    }

    @Override
    public CubeDependentData get(int key) {
        return cubeDependentDataMap.get(key);
    }

    @Override
    public void put(int key, CubeDependentData cubeDependentData) {
        cubeDependentDataMap.put(key, cubeDependentData);
    }

    @Override
    public void remove(int key) {
        cubeDependentDataMap.remove(key);
    }
}
