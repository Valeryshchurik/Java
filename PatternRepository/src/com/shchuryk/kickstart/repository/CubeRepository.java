package com.shchuryk.kickstart.repository;

import com.shchuryk.kickstart.entity.Cube;

import java.util.List;

public interface CubeRepository {
    void addCube(Cube cube);

    void removeCube(Cube cube);

    void updateCube(Cube currentCube, Cube newCube);

    void updateCubeLight(Cube currentCube, Cube newCube);

    List<Cube> query(CubeSpecification specification);
}
