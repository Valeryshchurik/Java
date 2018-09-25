package com.shchuryk.multithreading.view;

import com.shchuryk.multithreading.creator.MacSystemCreator;
import com.shchuryk.multithreading.exception.McdonaldsException;
import com.shchuryk.multithreading.runnable.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static Logger LOGGER = LogManager.getLogger(Main.class);
    private final static int THROUGHPUT = 5;
    private final static int PAUSE_TIME = 900;

    public static void main(String[] args) throws McdonaldsException {
        MacSystemCreator macSystemCreator = MacSystemCreator.getInstance();
        macSystemCreator.createMacSystemFromFile("data\\input.txt");
        List<Client> clients = macSystemCreator.getClients();
        LOGGER.debug("Mcdonalds was created");
        LOGGER.debug("Starting client's threads");
        for (int i = 0; i < clients.size(); i++) {
            if (i % THROUGHPUT == 0)
                try {
                    Thread.sleep(PAUSE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            new Thread(clients.get(i)).start();
        }
    }
}
