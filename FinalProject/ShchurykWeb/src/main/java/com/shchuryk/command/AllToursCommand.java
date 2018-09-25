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
public class AllToursCommand implements ActionCommand {

    
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(AttributeParameterEnum.RESULT.toString().toLowerCase(), TravelAgencyController.getAllTours());
        return ConfigurationManager.getProperty("path.page.all_tours");
    }
    
}
