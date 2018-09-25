/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shchuryk.command;

import com.shchuryk.model.User;
import com.shchuryk.receiver.TravelAgencyController;
import com.shchuryk.resource.ConfigurationManager;
import com.shchuryk.util.AttributeParameterEnum;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author NotePad.by
 */
public class BurningToursCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        User activeUser = (User)request.getSession().getAttribute(AttributeParameterEnum.USER.toString().toLowerCase());
        request.setAttribute(AttributeParameterEnum.RESULT.toString().toLowerCase(), TravelAgencyController.getBurningTours());
        return ConfigurationManager.getProperty("path.page.burning_tours");
    }

}
