package com.shchuryk.model;

public final class UserType {
    public static final int ADMIN = 1;
    public static final int GUEST = 2;
    public static final int CLIENT = 3;
    public static final int PROVIDER = 4;
    public enum Role{
        ADMIN, GUEST, CLIENT, PROVIDER
    }


    /**
     * Getter to be used inside JSTL tags.
     * @return
     */

    static public int getADMIN() {
        return 1;
    }

    /**
     * Getter to be used inside JSTL tags.
     * @return
     */
    static public int getGUEST() {
        return 2;
    }

    static public int getClient() {
        return 3;
    }
}