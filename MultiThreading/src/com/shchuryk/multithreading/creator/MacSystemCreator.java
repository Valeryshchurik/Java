package com.shchuryk.multithreading.creator;

import com.shchuryk.multithreading.reader.ClientFileReader;
import com.shchuryk.multithreading.runnable.Client;

import java.util.List;

public class MacSystemCreator {
    private static MacSystemCreator ourInstance = new MacSystemCreator();

    public static MacSystemCreator getInstance() {
        return ourInstance;
    }

    private MacSystemCreator() {
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    private List<Client> clients;

    public void createMacSystemFromFile(String filePath) {
        ClientFileReader reader = new ClientFileReader(filePath);
        ClientCreator creator = ClientCreator.getInstance();
        CashDeskCreator cashDeskCreator = CashDeskCreator.getInstance();
        List<String> inputInfo = reader.read();
        cashDeskCreator.createDesks(inputInfo.get(0));
        inputInfo.remove(0);
        clients = creator.createClients(inputInfo);
    }
}
