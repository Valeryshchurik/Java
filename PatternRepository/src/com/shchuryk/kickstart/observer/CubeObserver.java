package com.shchuryk.kickstart.observer;

public interface CubeObserver {
    void update(SimpleObservable o);

    void cleanUp(SimpleObservable o);
}
