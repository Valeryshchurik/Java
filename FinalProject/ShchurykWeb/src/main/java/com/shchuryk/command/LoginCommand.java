package com.shchuryk.command;

import com.shchuryk.model.User;
import com.shchuryk.receiver.AuthorisationLogic;
import com.shchuryk.resource.ConfigurationManager;
import com.shchuryk.resource.MessageManager;
import com.shchuryk.util.AttributeParameterEnum;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "logIn";
    private static final String PARAM_NAME_PASSWORD = "password";
    @Override
    public String execute(HttpServletRequest request) {
        User activeUser = (User)request.getSession().getAttribute(AttributeParameterEnum.USER.toString().toLowerCase());
        String page = null;
        if (activeUser != null) {
            return ConfigurationManager.getProperty("path.page.login");
        }
// извлечение из запроса логина и пароля
        String login = request.getParameter(AttributeParameterEnum.USERNAME_INPUT_FOR_LOGIN.toString().toLowerCase());
        String pass = request.getParameter(AttributeParameterEnum.PASSWORD_INPUT_FOR_LOGIN.toString().toLowerCase());
// проверка логина и пароля
        User user = AuthorisationLogic.logIn(login, pass);
        if (user!= null) {
            request.setAttribute("user", login);
// определение пути к main.jsp
            request.setAttribute(AttributeParameterEnum.RESULT.toString().toLowerCase(), true);
            request.getSession().setAttribute(AttributeParameterEnum.USER.toString().toLowerCase(), user);
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("result", false);
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
