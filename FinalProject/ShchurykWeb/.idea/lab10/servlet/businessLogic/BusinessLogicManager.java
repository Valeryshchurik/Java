/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shchuryk.lab10.servlet.businessLogic;

import com.struchkov.lab10.dao.*;
import com.struchkov.lab10.entities.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author NotePad.by
 */
public class BusinessLogicManager {
    private static Logger logger = LogManager.getLogger(BusinessLogicManager.class);

    private DaoBetRemote daoBet;

    private DaoRunRemote daoRun;

    private DaoHorseRemote daoHorse;

    private DaoClientRemote daoClient;

    public BusinessLogicManager(DaoBetRemote daoBet, DaoRunRemote daoRun, DaoHorseRemote daoHorse, DaoClientRemote daoClient) {
        this.daoBet = daoBet;
        this.daoRun = daoRun;
        this.daoHorse = daoHorse;
        this.daoClient = daoClient;
    }

    /**
     * Gets list of horses participating in a race.
     * @param raceName Race name.
     * @return List of horses participating in a race.
     */
    @SuppressWarnings("Duplicates")
    public ArrayList<HorsesRacesEntity> getHorsesInRace(String raceName) {
        int raceId;
        raceId = daoRun.readRaceIdByName(raceName);
        if (raceId == -1) {
            logger.error("Race " + raceName + " not found.");
            return null;
        }
        ArrayList<HorsesRacesEntity> result = new ArrayList<>(daoHorse.readHorsesInRun(raceId));
        logger.info("Race " + raceName + " horses retrieved." );
        return result;
    }

    /**
     * Gets list of races set for a date.
     * @param date Date.
     * @return List of races set for a date.
     */
    @SuppressWarnings("Duplicates")
    public ArrayList<RacesEntity> getRunsByDate(Date date) {
        ArrayList<RacesEntity> result = new ArrayList<>(daoRun.readRacesByDate(date));
        logger.info("Races on " + new SimpleDateFormat("yyyy-MM-dd").format(date) + " retrieved.");
        return result;
    }

    /**
     * Gets race winners.
     * @param raceName Race name.
     * @return Clients that made successful bets for race.
     */
    @SuppressWarnings("Duplicates")
    public ArrayList<BetsEntity> getWinnerClients(String raceName) {
        int raceId = daoRun.readRaceIdByName(raceName);
        if (raceId == -1) {
            logger.error("Race " + raceName + " not found.");
            return null;
        }
        ArrayList<BetsEntity> result = new ArrayList<>(daoClient.readWinners(raceId));
        logger.info("Race " + raceName + " winner clients retrieved." );
        return result;
    }

    /**
     * Registers horses for a race.
     * @param horses Horses to register.
     * @return <b>true</b> if successful.
     */
    @SuppressWarnings("Duplicates")
    public boolean registerHorse(String raceName, String horseName) {
        if (horseName == null || raceName == null)
            return false;
        int raceId = daoRun.readRaceIdByName(raceName);
        if (raceId == -1){
            logger.error("Race " + raceName + " not found.");
            return false;
        }
        int horseId = daoHorse.readHorseIdByName(horseName);
        if (horseId == -1){
            logger.error("Horse " + horseName + " not found.");
            return false;
        }
        HorsesRacesEntity horse = new HorsesRacesEntity();
        horse.setHorseId(horseId);
        horse.setRaceId(raceId);
        horse = daoHorse.registerHorse(horse);
        if (horse == null) {
            logger.error("Horse failed to register for race.");
            return false;
        }
        logger.info("Horse registered.");
        return true;
    }

    /**
     * Sets race results.
     * @param horses Participating horses.
     * @return <b>true</b> if successful.
     */
    @SuppressWarnings("Duplicates")
    public boolean setHorseResult(String raceName, String horseName, int place) {
        if (raceName == null || horseName == null || place < 1)
            return false;
        int raceId = daoRun.readRaceIdByName(raceName);
        if (raceId == -1){
            logger.error("Race " + raceName + " not found.");
            return false;
        }
        int horseId = daoHorse.readHorseIdByName(horseName);
        if (horseId == -1){
            logger.error("Horse " + horseName + " not found.");
            return false;
        }
        HorsesRacesEntity horse = daoHorse.readHorseRaceByIds(raceId, horseId);
        horse.setPlace(place);
        horse = daoHorse.setHorsePlace(horse);
        if (horse == null) {
            logger.error("Failed to set horse result for race.");
            return false;
        }
        logger.info("Race results set.");
        return true;
    }
    
    /**
     * Registers user in system.
     * @param name Name of client.
     * @param username Username.
     * @param password Password.
     * @param confirmPassword Password confirmation.
     * @return Created user.
     */
    public ClientsEntity registerUser(String name, String username, String password) {
        ClientsEntity client = new ClientsEntity();
        client.setClientName(name);
        client.setUsername(username);
        client.setPassword(password);
        client.setRoleId(UserRoles.USER);
        client = daoClient.create(client);
        if (client == null) {
            logger.error("Registration failed.");
        }
        return client;
    }
    
    /**
     * Logs user into system.
     * @param username Username.
     * @param password Password.
     * @return Logged in user.
     */
    public ClientsEntity loginUser(String username, String password) {
        ClientsEntity client = daoClient.loginUser(username, password);
        if (client == null) {
            logger.error("User not found.");
        }
        return client;
    }
}

