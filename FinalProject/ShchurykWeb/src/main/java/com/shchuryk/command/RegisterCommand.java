/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shchuryk.command;

import com.shchuryk.model.User;
import com.shchuryk.receiver.AuthorisationLogic;
import com.shchuryk.resource.ConfigurationManager;
import com.shchuryk.util.AttributeParameterEnum;
import com.shchuryk.util.Navigation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author NotePad.by
 */
public class RegisterCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        User activeUser = (User)request.getSession().getAttribute(AttributeParameterEnum.USER.toString().toLowerCase());
        if (activeUser != null) {
            return ConfigurationManager.getProperty("path.page.login");
        }
        String username = request.getParameter(AttributeParameterEnum.USERNAME_INPUT_FOR_REGISTER.toString().toLowerCase());
        String clientName = request.getParameter(AttributeParameterEnum.CLIENT_NAME_INPUT_FOR_REGISTER.toString().toLowerCase());
        String password = request.getParameter(AttributeParameterEnum.PASSWORD_INPUT_FOR_REGISTER.toString().toLowerCase());
        String mail = request.getParameter(AttributeParameterEnum.MAIL_INPUT_FOR_REGISTER.toString().toLowerCase());
        User client = AuthorisationLogic.registerUser(clientName, username, mail, password);
        request.setAttribute(AttributeParameterEnum.RESULT.toString().toLowerCase(),
                client != null); 
        if (client == null)
            request.setAttribute(AttributeParameterEnum.ERR.toString().toLowerCase(), "Регистрация прошла неудачно.");
        return  ConfigurationManager.getProperty("path.page.register");
    }
}