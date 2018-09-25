package com.shchuryk.multithreading.state;

import com.shchuryk.multithreading.runnable.Client;

/**
 * Created by msi on 15.03.2018.
 */
public interface State {
    int stayInQueue(Client client);
    void leaveQueue(Client client);
}
