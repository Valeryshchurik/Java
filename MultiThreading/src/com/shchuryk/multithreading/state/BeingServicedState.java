package com.shchuryk.multithreading.state;

import com.shchuryk.multithreading.runnable.Client;

public class BeingServicedState extends ClientInSystemState {
    public BeingServicedState(int queueNumber) {
        super(queueNumber);
    }

    @Override
    public int stayInQueue(Client client) {
        throw new UnsupportedOperationException(
                "Client is already being serviced!");
    }

    @Override
    public void leaveQueue(Client client) {
        throw new UnsupportedOperationException(
                "Client is servicing right now. Can not interrupt servicing!");
    }
}
