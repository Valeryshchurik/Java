package com.shchuryk.multithreading.state;

import com.shchuryk.multithreading.entity.Mcdonalds;
import com.shchuryk.multithreading.exception.McdonaldsException;
import com.shchuryk.multithreading.runnable.Client;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StayingInQueueState extends ClientInSystemState {
    private static final Logger LOGGER = LogManager.getLogger(StayingInQueueState.class);

    public StayingInQueueState(int queueNumber) {
        super(queueNumber);
    }

    @Override
    public int stayInQueue(Client client) {
        Mcdonalds mcdonalds = Mcdonalds.getInstance();
        int minQueue = mcdonalds.getMinQueue();
        mcdonalds.receiveClient(client, minQueue);
        return minQueue;
    }

    @Override
    public void leaveQueue(Client client) {
        Mcdonalds mcdonalds = Mcdonalds.getInstance();
        try {
            mcdonalds.leaveQueue(client, queueNumber);
        } catch (McdonaldsException e) {
            LOGGER.catching(Level.ERROR, e);
        }
    }
}
