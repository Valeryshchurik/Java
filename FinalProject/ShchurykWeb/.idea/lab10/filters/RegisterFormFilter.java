/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shchuryk.lab10.filters;

import com.struchkov.lab10.util.AttributeParameterEnum;
import com.struchkov.lab10.util.Navigation;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NotePad.by
 */
public class RegisterFormFilter implements Filter {
    
    private FilterConfig filterConfig = null;
    
    public RegisterFormFilter() {
    }    

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        String contextPath = req.getContextPath();
        try {
            if (Navigation.REGISTER.equals(req.getParameter(AttributeParameterEnum.PAGE_NAME.toString().toLowerCase()))) {
                String username = req.getParameter(AttributeParameterEnum.USERNAME_INPUT_FOR_REGISTER.toString().toLowerCase());
                String clientName = req.getParameter(AttributeParameterEnum.CLIENT_NAME_INPUT_FOR_REGISTER.toString().toLowerCase());
                String password = req.getParameter(AttributeParameterEnum.PASSWORD_INPUT_FOR_REGISTER.toString().toLowerCase());
                String confirmPassword = req.getParameter(AttributeParameterEnum.CONFIRM_PASSWORD_INPUT_FOR_REGISTER.toString().toLowerCase());
                if (username == null || username.isEmpty() ||
                      clientName == null || clientName.isEmpty() ||
                      password == null || password.isEmpty()) {
                    req.setAttribute(AttributeParameterEnum.RESULT.toString().toLowerCase(), false);
                    req.setAttribute(AttributeParameterEnum.ERR.toString().toLowerCase(), "Необходимо заполнить все поля.");
                    req.getRequestDispatcher("/" + Navigation.REGISTER + ".jsp").forward(req, res);
                }
                else if (!password.equals(confirmPassword)) {
                    req.setAttribute(AttributeParameterEnum.RESULT.toString().toLowerCase(), false);
                    req.setAttribute(AttributeParameterEnum.ERR.toString().toLowerCase(), "Пароли не совпадают.");
                    req.getRequestDispatcher("/" + Navigation.REGISTER + ".jsp").forward(req, res);
                }
            }
            chain.doFilter(request, response);
        } catch (Throwable t) {
            t.printStackTrace();
        }   
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("RegisterFormFilter()");
        }
        StringBuffer sb = new StringBuffer("RegisterFormFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
