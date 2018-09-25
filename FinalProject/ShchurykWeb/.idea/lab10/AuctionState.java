package com.shchuryk.model;

public final class AuctionState {
    public enum State{
        PLANNED, RUNNING, FINISHED
    }
//    public static final int ADMIN = 1;
//    public static final int GUEST = 2;
//    public static final int CLIENT = 3;
//    public static final int PROVIDER = 4;

    /**
     * Getter to be used inside JSTL tags.
     * @return
     */

    public int getPLANNED() {
        return State.PLANNED.ordinal()+1;
    }

    /**
     * Getter to be used inside JSTL tags.
     * @return
     */
    public int getGUEST() {
        return State.RUNNING.ordinal()+1;
    }

}
