package com.shchuryk.multithreading.state;

import com.shchuryk.multithreading.entity.Mcdonalds;
import com.shchuryk.multithreading.runnable.Client;

public class NewClientState implements State {
    @Override
    public int stayInQueue(Client client) {
        Mcdonalds mcdonalds = Mcdonalds.getInstance();
        int minQueue = mcdonalds.getMinQueue();
        mcdonalds.receiveClient(client, mcdonalds.getMinQueue());
        return minQueue;
    }

    @Override
    public void leaveQueue(Client client) {
        throw new UnsupportedOperationException(
                "Client is not in mac!");
    }
}
