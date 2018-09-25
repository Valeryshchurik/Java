package com.shchuryk.multithreading.runnable;

import com.shchuryk.multithreading.exception.ClientStateException;
import com.shchuryk.multithreading.state.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client implements Runnable, Comparable<Client> {
    private final static int HAPPY_VALUE = 0;
    private final static int NORMAL_VALUE = 2;
    private final static int ANGRY_VALUE = 4;
    private static Logger LOGGER = LogManager.getLogger(Client.class);
    private String FIO;
    private State state;
    private int angry = 0;
    private int queueId;
    private int priority;

    public Client(String FIO, int priority) {
        this.FIO = FIO;
        this.priority = priority;
        state = new NewClientState();
    }

    public static int getHappyValue() {
        return HAPPY_VALUE;
    }

    public static int getNormalValue() {
        return NORMAL_VALUE;
    }

    public static int getAngryValue() {
        return ANGRY_VALUE;
    }

    public int getAngry() {
        return angry;
    }

    public void setAngry(int angry) {
        this.angry = angry;
    }

    public void makeAngrier() {
        angry++;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getFIO() {
        return FIO;
    }

    public void chooseQueue() {
        state.stayInQueue(this);
    }

    public State getState() {
        return state;
    }

    public void leave() {
        try {
            if (state.getClass() == ClientServicedState.class) {
                state.leaveQueue(this);
                angry = 0;
                changeState(new ClientHappyQuitState());
            }
            if (state.getClass() == StayingInQueueState.class) {
                if (angry < ANGRY_VALUE) {
                    LOGGER.debug("client " + FIO + " is a little bit angry. He wants to change his queue.");
                    state.leaveQueue(this);
                    state.stayInQueue(this);
                } else {
                    state.leaveQueue(this);
                    changeState(new ClientRageQuitState());
                }
            }
        } catch (ClientStateException e) {
            LOGGER.catching(Level.ERROR, e);
        }
    }

    public void changeState(State state) throws ClientStateException {
        Class stateClass = this.state.getClass();
        if (stateClass == ClientHappyQuitState.class || stateClass == ClientRageQuitState.class)
            throw new ClientStateException("Can not change state from one of final states. Calling client was: " + this.getFIO());
        this.state = state;
    }

    public void enterTheMac() {
        chooseQueue();
    }

    @Override
    public void run() {
        enterTheMac();
        LOGGER.debug(this.getFIO() + " quits the mcdonalds. His state is " + state.getClass().getSimpleName());
    }

    @Override
    public int compareTo(Client o) {
        return Integer.compare(priority, o.getPriority());
    }

}
