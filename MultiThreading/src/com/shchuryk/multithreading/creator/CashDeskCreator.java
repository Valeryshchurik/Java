package com.shchuryk.multithreading.creator;

import com.shchuryk.multithreading.entity.Mcdonalds;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CashDeskCreator {
    private static CashDeskCreator ourInstance = new CashDeskCreator();

    public static CashDeskCreator getInstance() {
        return ourInstance;
    }

    private CashDeskCreator() {
    }

    private static final Logger LOGGER = LogManager.getLogger(CashDeskCreator.class);
    private final static int DEFAULT_NUMBER_OF_DESKS = 1;

    public void createDesks(String inputLine) {
        int cashDeskNumber;
        try {
            cashDeskNumber = Integer.parseInt(inputLine);
        } catch (NumberFormatException e) {
            LOGGER.error("There was an error in input data:");
            LOGGER.catching(Level.ERROR, e);
            LOGGER.warn("Number of desks will be set to default value: " + DEFAULT_NUMBER_OF_DESKS);
            cashDeskNumber = DEFAULT_NUMBER_OF_DESKS;
        }
        Mcdonalds mcdonalds = Mcdonalds.getInstance();
        for (int i=0;i<cashDeskNumber;i++) {
            mcdonalds.addCashDesk(i);
        }
    }
}
