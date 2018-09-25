package com.shchuryk.kickstart.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleObservable {
    private boolean changed = false;
    private List<CubeObserver> observers;

    public SimpleObservable() {
        observers = new ArrayList<>();
    }

    protected synchronized void addObserver(CubeObserver o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    public synchronized void deleteObserver(CubeObserver o) {
        o.cleanUp(this);
        observers.remove(o);
    }

    public synchronized void deleteObservers() {
        Iterator<CubeObserver> it = observers.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public void notifyObservers() {
        /*
         * a temporary array buffer, used as a snapshot of the state of
         * current Observers.
         */
        Object[] arrLocal;

        synchronized (this) {
            /* We don't want the Observer doing callbacks into
             * arbitrary code while holding its own Monitor.
             * The code where we extract each Observable from
             * the List and store the state of the Observer
             * needs synchronization, but notifying observers
             * does not (should not).  The worst result of any
             * potential race-condition here is that:
             * 1) a newly-added Observer will miss a
             *   notification in progress
             * 2) a recently unregistered Observer will be
             *   wrongly notified when it doesn't care
             */
            if (!changed)
                return;
            arrLocal = observers.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length - 1; i >= 0; i--)
            ((CubeObserver) arrLocal[i]).update(this);
    }

    /**
     * Marks this <tt>Observable</tt> object as having been changed; the
     * <tt>hasChanged</tt> method will now return <tt>true</tt>.
     */
    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }

    public synchronized boolean hasChanged() {
        return changed;
    }

    public synchronized int countObservers() {
        return observers.size();
    }
}
