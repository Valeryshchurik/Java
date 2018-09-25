package com.shchuryk.xml.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebFilter(urlPatterns = {"/index.jsp"},
           initParams = {@WebInitParam(name = "startLanguage", value = "ru", description = "default language localisation"),
                         @WebInitParam(name = "startCountry", value = "RU", description = "default country localisation")} )
public class StartPageFilter implements Filter {

    private static Logger logger = LogManager.getLogger(StartPageFilter.class);
    private String language;
    private String country;

    @Override
    public void init(FilterConfig filterConfig) {
        language = filterConfig.getInitParameter("startLanguage");
        country = filterConfig.getInitParameter("startCountry");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        logger.info("called doFilter() in StartPageFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        logger.info("before check session.getAttr: " + session.getAttribute("locale"));
        Locale locale;
        if (session.getAttribute("locale") == null) {
            locale = new Locale(language, country);
            session.setAttribute("locale", locale);
            logger.info("loc from setAttr: " + locale);
        } else {
            locale = (Locale) session.getAttribute("locale");
            logger.info("loc from getAttr: " + locale);
        }
        logger.info("after check session.getAttr: " + session.getAttribute("locale"));

        ResourceBundle rb = ResourceBundle.getBundle("text", locale);
        for (String key : rb.keySet()) {
            String value = rb.getString(key);
            servletRequest.setAttribute(key, value);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
