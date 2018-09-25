package com.shchuryk.multithreading.entity;

import com.shchuryk.multithreading.exception.ClientStateException;
import com.shchuryk.multithreading.exception.McdonaldsException;
import com.shchuryk.multithreading.runnable.Client;
import com.shchuryk.multithreading.state.BeingServicedState;
import com.shchuryk.multithreading.state.ClientInSystemState;
import com.shchuryk.multithreading.state.ClientServicedState;
import com.shchuryk.multithreading.state.StayingInQueueState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Mcdonalds {
    private static final Logger LOGGER = LogManager.getLogger(Mcdonalds.class);
    private Map<Integer, CashDesk> queues;
    private static Mcdonalds instance = null;
    private static ReentrantLock lock = new ReentrantLock();

    private Mcdonalds() {
        queues = new HashMap<>();
    }

    public void addCashDesk(int numberOfDesk) {
        lock.lock();
        queues.put(numberOfDesk, new CashDesk());
        lock.unlock();
    }

    public void removeCashDesk(int numberOfDesk) {
        lock.lock();
        queues.remove(numberOfDesk);
        lock.unlock();
    }

    public int getMinQueue() {
        int minQueueSize = Integer.MAX_VALUE;
        int minQueueIndex = Integer.MAX_VALUE;
        lock.lock();
        for (Map.Entry<Integer, CashDesk> macQueue : this.queues.entrySet()) {
            int queueSize = macQueue.getValue().getQueue().size();
            if (queueSize < minQueueSize) {
                minQueueSize = queueSize;
                minQueueIndex = macQueue.getKey();
            }
        }
        lock.unlock();
        return minQueueIndex;
    }

    public static Mcdonalds getInstance() {
        lock.lock(); // блокировка
        try {
            if (instance == null) {
                instance = new Mcdonalds();
            }
        } finally {
            lock.unlock(); // снятие блокировки
        }
        return instance;
    }

    public void receiveClient(Client client, int queueNumber) {
        LOGGER.debug(client.getFIO() + " come to the queue number " + queueNumber);
        CashDesk cashDesk = queues.get(queueNumber);
        ReentrantLock queueLock = cashDesk.getQueueLock();
        ReentrantLock enterLock = cashDesk.getEnterLock();
        enterLock.lock();
        Queue<Client> queue = cashDesk.getQueue();
        queue.add(client);
        try {
            client.changeState(new StayingInQueueState(queueNumber));
        } catch (ClientStateException e) {
            LOGGER.catching(Level.ERROR, e);
        }
        enterLock.unlock();
        while (client.getState().getClass().getSuperclass() == ClientInSystemState.class) {
            queueLock.lock();
            if (cashDesk.getQueue().peek() == client) {
                service(client, queueNumber);
                client.leave();
            } else {
                client.makeAngrier();
                if (client.getAngry() > Client.getNormalValue()) {
                    client.leave();
                } else
                    queueLock.unlock();
            }
        }
    }

    public void leaveQueue(Client client, int queueNumber) throws McdonaldsException {
        LOGGER.debug(client.getFIO() + " leaving the queue number " + queueNumber);
        CashDesk cashDesk = queues.get(queueNumber);
        cashDesk.getEnterLock().lock();
        if (!cashDesk.getQueue().contains(client))
            throw new McdonaldsException("trying to leave queue " + queueNumber +
                    "which has not been taken by the client " + client.getFIO());
        cashDesk.getQueue().remove(client);
        cashDesk.getEnterLock().unlock();
        cashDesk.getQueueLock().unlock();
    }

    private void service(Client client, int queueNumber) {
        try {
            CashDesk cashDesk = queues.get(queueNumber);
            client.changeState(new BeingServicedState(queueNumber));
            System.out.println(client.getFIO() + " is servicing at the cash desk number: " + queueNumber);
            TimeUnit.SECONDS.sleep(cashDesk.getTimeToService());
            System.out.println(client.getFIO() + " finished");
            client.changeState(new ClientServicedState(queueNumber));
        } catch (InterruptedException | ClientStateException e) {
            LOGGER.catching(Level.ERROR, e);
        }
    }
}
