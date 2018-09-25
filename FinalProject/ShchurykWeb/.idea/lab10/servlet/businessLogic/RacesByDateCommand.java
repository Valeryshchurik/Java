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
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NotePad.by
 */
public class RacesByDateCommand implements Command {

    private BusinessLogicManager manager;
    
    public RacesByDateCommand(BusinessLogicManager manager) {
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
        String dateString = request.getParameter(AttributeParameterEnum.RACE_DATE_INPUT.toString().toLowerCase());
        String[] tokens = dateString.split("-");
        int year = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]) - 1;
        int day = Integer.parseInt(tokens[2]);
        request.setAttribute(AttributeParameterEnum.RESULT.toString().toLowerCase(),
                manager.getRunsByDate(new GregorianCalendar(year, month, day).getTime())); 
        request.getRequestDispatcher("/" + Navigation.RACES_BY_DATE + ".jsp")
                .forward(request, response);
    }
    
}
