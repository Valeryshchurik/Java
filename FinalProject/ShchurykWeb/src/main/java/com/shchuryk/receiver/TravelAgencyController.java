package com.shchuryk.receiver;


import com.shchuryk.dao.DaoOrder;
import com.shchuryk.dao.DaoTour;
import com.shchuryk.dao.DaoUser;
import com.shchuryk.exception.DaoException;
import com.shchuryk.model.Tour;
import com.shchuryk.model.User;

import java.util.ArrayList;

public class TravelAgencyController {

    public static void insertTour(Tour tour){
        DaoTour daoTour = null;
        try {
            daoTour = new DaoTour();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try {
            daoTour.create(tour);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Tour> getAllTours(){
        DaoTour daoTour = null;
        try {
            daoTour = new DaoTour();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try {
            return daoTour.readAllTours();
        } catch (DaoException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static ArrayList<Tour> getBurningTours() {
        DaoTour daoTour = null;
        try {
            daoTour = new DaoTour();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try {
            return daoTour.readBurningTours();
        } catch (DaoException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ArrayList<Tour> updateClientOrder(int id){
        DaoOrder daoOrder = null;
        DaoTour daoTour = null;
        DaoUser daoClient = null;
        try {
            daoOrder = new DaoOrder();
            daoTour = new DaoTour();
            daoClient = new DaoUser();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try {
            daoOrder.update(id);
            return daoClient.readClientOrders("Pit");
        } catch (DaoException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ArrayList<Tour> getClientOrders(String name) {
        DaoUser daoClient = null;
        try {
            daoClient = new DaoUser();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try {
            return daoClient.readClientOrders(name);
        } catch (DaoException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static ArrayList<User> getAllUsers() {
//        DaoUser daoClient = null;
//        try {
//            daoClient = new DaoUser();
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }
//        try {
//            return daoClient.readAllUsers();
//        } catch (DaoException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
