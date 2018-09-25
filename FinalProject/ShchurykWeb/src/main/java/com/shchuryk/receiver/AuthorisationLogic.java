package com.shchuryk.receiver;

import com.shchuryk.dao.DaoUser;
import com.shchuryk.exception.DaoException;
import com.shchuryk.model.User;
import com.shchuryk.model.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthorisationLogic {
//    private final static String ADMIN_LOGIN = "admin";
//    private final static String ADMIN_PASS = "Qwe123";
    private static Logger logger = LogManager.getLogger(AuthorisationLogic.class);

    public static User logIn(String enterLogin, String enterPass) {
        DaoUser daoUser = null;
        try {
            daoUser = new DaoUser();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        User user = null;
        try {
            user = daoUser.read(enterLogin);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        if (user == null || !user.getPassword().equals(enterPass)){
            return null;
        }
        return user;
    }

    public static User registerUser(String name, String login, String mail, String password) {
        DaoUser daoUser = null;
        try {
            daoUser = new DaoUser();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        User client = new User();
        client.setName(name);
        client.setLogin(login);
        client.setPassword(password);
        client.setEmail(mail);
        client.setUserType(UserType.getClient());
        try {
            daoUser.create(client);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try {
            client = daoUser.read(login);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        if (client == null) {
            logger.error("Registration failed.");
        }
        return client;
    }
}
