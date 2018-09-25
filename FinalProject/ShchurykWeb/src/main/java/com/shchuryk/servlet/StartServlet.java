package com.shchuryk.servlet;

import com.shchuryk.actionfactory.ActionFactory;
import com.shchuryk.command.ActionCommand;
import com.shchuryk.util.AttributeParameterEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@WebServlet("/pages/controller")
public class StartServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(StartServlet.class);
    private static final String UPLOAD_DIR = "uploads";

    @Override
    public void init() throws ServletException {
        super.init();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        sendSessionCookies(request, response);
        String page;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
//        } else {
//            page = ConfigurationManager.getProperty("path.page.index");
//            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
//            response.sendRedirect(request.getContextPath() + page);
//        }
    }
    protected void sendSessionCookies(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Date creationDate = new Date(session.getCreationTime());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateString = df.format(creationDate) + " GMT";
        String oldDateString = getCookieValue(request, AttributeParameterEnum.LAST_SESSION_DATE.toString().toLowerCase(), null);
        setCookieValue(response, AttributeParameterEnum.LAST_SESSION_DATE.toString().toLowerCase(), dateString);
        int visitCount = Integer.parseInt(getCookieValue(request, AttributeParameterEnum.VISIT_COUNT.toString().toLowerCase(), "0"));
        if (!dateString.equals(oldDateString)) {
            setCookieValue(response, AttributeParameterEnum.VISIT_COUNT.toString().toLowerCase(), Integer.toString(++visitCount));
        }
    }

    protected void setCookieValue(HttpServletResponse response, String key, String value) {
        Cookie cookie = null;
        try {
            cookie = new Cookie(key, URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addCookie(cookie);
    }

    protected String getCookieValue(HttpServletRequest request, String key, String defaultValue) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && key != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    try {
                        return URLDecoder.decode(cookie.getValue(),"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return defaultValue;
    }

}
