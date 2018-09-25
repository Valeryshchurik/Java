package com.shchuryk.kickstart.repository;

import com.shchuryk.kickstart.entity.Cube;

public interface CubeSpecification {
    boolean specified(Cube cube);
}
