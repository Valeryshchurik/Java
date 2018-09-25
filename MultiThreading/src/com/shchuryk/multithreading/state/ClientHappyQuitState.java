package com.shchuryk.multithreading.state;

import com.shchuryk.multithreading.runnable.Client;

public class ClientHappyQuitState implements State {
    @Override
    public int stayInQueue(Client client) {
        throw new UnsupportedOperationException(
                "Client has already serviced!");
    }

    @Override
    public void leaveQueue(Client client) {
        throw new UnsupportedOperationException(
                "Client has already leave Mcdonald's!");
    }
}
