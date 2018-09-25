package com.shchuryk.multithreading.state;

import com.shchuryk.multithreading.runnable.Client;

public class ClientRageQuitState implements State {
    @Override
    public int stayInQueue(Client client) {
        throw new UnsupportedOperationException(
                "Client has already run out from your Mcdonald's!");
    }

    @Override
    public void leaveQueue(Client client) {
        throw new UnsupportedOperationException(
                "Client has already run out from your Mcdonald's!");
    }
}
