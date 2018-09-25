package view;


import soapservice.daoClient.DaoClientBeanService;
import soapservice.daoTour.DaoTourBeanService;
import soapservice.daoTour.TourEntity;

import javax.xml.ws.WebServiceRef;
import java.util.List;

/**
 */
public class Main {

    private static TravelAgencyController travelAgencyController = new TravelAgencyController();

    @WebServiceRef(wsdlLocation = "http://localhost:8080/DaoTourBeanService/DaoTourBean?wsdl")
    private static DaoTourBeanService daoTourService;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/DaoClientBeanService/DaoClientBean?wsdl")
    private static DaoClientBeanService daoClientService;

    public static void main(String[] args) {
        List<TourEntity> allTours =  daoTourService.getDaoTourBeanPort().findAllTours();
        for (TourEntity tour : allTours) {
            System.out.println(tour);
        }
//        travelAgencyController.setDaoTour(daoTourService.getDaoTourBeanPort());
//        travelAgencyController.setDaoClient(daoClientService.getDaoClientBeanPort());
//
//        ArrayList<TourEntity> allTours = travelAgencyController.getAllTours();
//        for (TourEntity tour : allTours) {
//            System.out.println(tour);
//    }
//        System.out.println();
//        ArrayList<TourEntity> allBurningTours = travelAgencyController.getAllBurningTours();
//        for (TourEntity burningTour : allBurningTours) {
//            System.out.println(burningTour);
//        }
//        System.out.println();
//        ArrayList<TourEntity> allClientsTours = travelAgencyController.getAllClientOrders("Петров Пётр Петрович");
//        for (TourEntity clientsTour : allClientsTours) {
//            System.out.println(clientsTour);
//        }
    }
}
