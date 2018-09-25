/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shchuryk.lab10.servlet.businessLogic;

import com.struchkov.lab10.entities.ClientsEntity;
import com.struchkov.lab10.util.AttributeParameterEnum;
import com.struchkov.lab10.util.Navigation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NotePad.by
 */
public class LoginCommand implements Command {

    private BusinessLogicManager manager;
    
    public LoginCommand(BusinessLogicManager manager) {
        this.manager = manager;
    }
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClientsEntity activeUser = (ClientsEntity)request.getSession().getAttribute(AttributeParameterEnum.USER.toString().toLowerCase());
        if (activeUser != null) {
            request.getRequestDispatcher("/" + Navigation.INDEX + ".jsp")
                .forward(request, response);
            return;
        }
        String username = request.getParameter(AttributeParameterEnum.USERNAME_INPUT_FOR_LOGIN.toString().toLowerCase());
        String password = request.getParameter(AttributeParameterEnum.PASSWORD_INPUT_FOR_LOGIN.toString().toLowerCase());
        ClientsEntity client = manager.loginUser(username, password);
        request.setAttribute(AttributeParameterEnum.RESULT.toString().toLowerCase(), client != null);
        request.getSession().setAttribute(AttributeParameterEnum.USER.toString().toLowerCase(), client);
        request.getRequestDispatcher(client == null ? "/" + Navigation.LOGIN + ".jsp" : "/" + Navigation.INDEX + ".jsp")
                .forward(request, response);
    }
}
