package com.shchuryk.kickstart.observer;

public interface SimpleMap<T> {
    T get(int key);

    void put(int key, T t);

    void remove(int key);
}
