package com.shchuryk.command;

import com.shchuryk.receiver.TravelAgencyController;
import com.shchuryk.resource.ConfigurationManager;
import com.shchuryk.util.AttributeParameterEnum;

import javax.servlet.http.HttpServletRequest;

public class EditTourCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {
        int id;
        id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute(AttributeParameterEnum.RESULT.toString().toLowerCase(), TravelAgencyController.updateClientOrder(id));
        return ConfigurationManager.getProperty("path.page.order");
    }
}
