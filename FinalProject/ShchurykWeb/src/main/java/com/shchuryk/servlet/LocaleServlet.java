package com.shchuryk.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class LocaleServlet extends HttpServlet {

    private static Logger logger = LogManager.getLogger(LocaleServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }
    public static void setLocale(HttpServletRequest req){
        logger.info("called doPost() from LocaleServlet");
        String locale = req.getParameter("localisation");
        String language = locale.substring(0, 2);
        String country = locale.substring(3, locale.length());
        logger.info("lang: " + language + ", country: " + country);
        Locale loc = new Locale(language, country);
        req.getSession().setAttribute("locale", loc);
        logger.info("session.getAttr: " + req.getSession().getAttribute("locale"));
        ResourceBundle rb = ResourceBundle.getBundle("text", loc);
        for (String key : rb.keySet()) {
            String value = rb.getString(key);
            req.setAttribute(key, value);
        }
    }
}
