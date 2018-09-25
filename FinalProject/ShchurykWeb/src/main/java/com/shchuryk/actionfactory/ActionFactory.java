package com.shchuryk.actionfactory;

import com.shchuryk.command.ActionCommand;
import com.shchuryk.command.CommandEnum;
import com.shchuryk.command.EmptyCommand;
import com.shchuryk.resource.MessageManager;
import com.shchuryk.util.AttributeParameterEnum;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
        public ActionCommand defineCommand(HttpServletRequest request) {
            ActionCommand current = new EmptyCommand();
            String action = request.getParameter(AttributeParameterEnum.COMMAND.toString().toLowerCase());
            if (action == null || action.isEmpty()) {
                return current;
            }
            try {
                CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
                current = currentEnum.getCurrentCommand();
            } catch (IllegalArgumentException e) {
                request.setAttribute("wrongAction", action
                        + MessageManager.getProperty("message.wrongaction"));
            }
            return current;
        }
}
