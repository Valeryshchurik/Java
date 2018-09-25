package com.shchuryk.multithreading.state;

public abstract class ClientInSystemState implements State {
    protected int queueNumber;

    public ClientInSystemState(int queueNumber) {
        this.queueNumber = queueNumber;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }
}
