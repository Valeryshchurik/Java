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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *
 * @author NotePad.by
 */
public class OrderCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String name = null;
            name = request.getParameter(AttributeParameterEnum.ORDERS.toString().toLowerCase());
        request.setAttribute(AttributeParameterEnum.RESULT.toString().toLowerCase(), TravelAgencyController.getClientOrders(name));
        return ConfigurationManager.getProperty("path.page.order");
    }
}
