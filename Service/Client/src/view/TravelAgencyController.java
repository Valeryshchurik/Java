package view;

import entity.TourEntity;
import soapservice.daoClient.DaoClientBean;
import soapservice.daoTour.DaoTourBean;

import java.util.ArrayList;

public class TravelAgencyController {

    private DaoTourBean daoTour;

    private DaoClientBean daoClient;

    public void setDaoTour(DaoTourBean daoTour) {
        this.daoTour = daoTour;
    }

    public void setDaoClient(DaoClientBean daoClient) {
        this.daoClient = daoClient;
    }



    @SuppressWarnings("Duplicates")
    public ArrayList<TourEntity> getAllClientOrders(String name) {
        ArrayList<TourEntity> result = new ArrayList(daoClient.findClientOrders(name));
//        logger.info("Orders of " + name + " retrieved.");
        return result;
    }

    @SuppressWarnings("Duplicates")
    public ArrayList<TourEntity> getAllTours() {
        ArrayList<TourEntity> result = new ArrayList(daoTour.findAllTours());
//        logger.info("All tours retrieved." );
        return result;
    }

    @SuppressWarnings("Duplicates")
    public ArrayList<TourEntity> getAllBurningTours() {
        ArrayList<TourEntity> result = new ArrayList(daoTour.findAllBurningTours());
//        logger.info("All burning tours retrieved." );
        return result;
    }
}
