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
public class WinnerClientsCommand implements Command {

    private BusinessLogicManager manager;
    
    public WinnerClientsCommand(BusinessLogicManager manager) {
        this.manager = manager;
    }
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClientsEntity activeUser = (ClientsEntity)request.getSession().getAttribute(AttributeParameterEnum.USER.toString().toLowerCase());
        if (activeUser == null) {
            request.getRequestDispatcher("/" + Navigation.LOGIN + ".jsp")
                .forward(request, response);
            return;
        }
        String raceName = request.getParameter(AttributeParameterEnum.RACE_NAME_INPUT_FOR_BETS.toString().toLowerCase());
        request.setAttribute(AttributeParameterEnum.RESULT.toString().toLowerCase(),
                manager.getWinnerClients(raceName)); 
        request.getRequestDispatcher("/" + Navigation.WINNER_CLIENTS + ".jsp")
                .forward(request, response);
    }
    
}
