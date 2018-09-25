package com.shchuryk.multithreading.entity;

import com.shchuryk.multithreading.exception.GeneratorRandomException;
import com.shchuryk.multithreading.runnable.Client;
import com.shchuryk.multithreading.util.GeneratorRandomInt;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class CashDesk {
    private static Logger LOGGER = LogManager.getLogger(CashDesk.class);
    private final static int DEFAULT_MIN_SERVICE_VALUE = 1;
    private final static int DEFAULT_MAX_SERVICE_VALUE = 2;
    private Queue<Client> queue = new PriorityQueue<Client>();
    private ReentrantLock queueLock = new ReentrantLock(true);
    private ReentrantLock enterLock = new ReentrantLock(true);
    private int timeToService;

    CashDesk() {
        try {
            timeToService = GeneratorRandomInt.generateRandomInt(DEFAULT_MIN_SERVICE_VALUE, DEFAULT_MAX_SERVICE_VALUE);
        } catch (GeneratorRandomException e) {
            LOGGER.catching(Level.ERROR, e);
        }
    }

    public int getTimeToService() {
        return timeToService;
    }

    public void setTimeToService(int timeToService) {
        this.timeToService = timeToService;
    }

    Queue<Client> getQueue() {
        return queue;
    }

    ReentrantLock getQueueLock() {
        return queueLock;
    }

    ReentrantLock getEnterLock() {
        return enterLock;
    }
}
