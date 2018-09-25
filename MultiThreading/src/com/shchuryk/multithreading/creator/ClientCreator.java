package com.shchuryk.multithreading.creator;

import com.shchuryk.multithreading.runnable.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ClientCreator {
    private static ClientCreator ourInstance = new ClientCreator();

    public static ClientCreator getInstance() {
        return ourInstance;
    }

    private ClientCreator() {
    }

    private static final Logger LOGGER = LogManager.getLogger(ClientCreator.class);

    public List<Client> createClients(List<String> inputLines) {
        List<Client> clients = new ArrayList<>();
        for (String line : inputLines) {
            String[] parametersPair = line.split(" ");
            Client client = new Client(parametersPair[0], Integer.parseInt(parametersPair[1]));
            clients.add(client);
        }
        if (!clients.isEmpty()) {
            LOGGER.info("Cubes were created successfully");
        } else {
            LOGGER.warn("No cube was created");
        }
        return clients;
    }
}
