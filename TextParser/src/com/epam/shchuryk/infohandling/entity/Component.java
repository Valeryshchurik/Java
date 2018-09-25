package com.epam.shchuryk.infohandling.entity;

import com.epam.shchuryk.infohandling.exception.UnavailableComponentOperation;

public interface Component {
    void add(Component component) throws UnavailableComponentOperation;

    void remove(Component component) throws UnavailableComponentOperation;

    Component getElement(int index);

    public int countSizeContext();
}