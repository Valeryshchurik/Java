/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shchuryk.lab10.filters;

import com.struchkov.lab10.entities.ClientsEntity;
import com.struchkov.lab10.entities.UserRoles;
import com.struchkov.lab10.util.AttributeParameterEnum;
import com.struchkov.lab10.util.Navigation;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NotePad.by
 */
public class LoginFilter implements Filter {

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public LoginFilter() {
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
        HttpSession session = req.getSession(false);
        String path = req.getRequestURI();
        boolean loginPagesOpened = path.equals(contextPath + "/" + Navigation.REGISTER + ".jsp") ||
                 path.equals(contextPath + "/" + Navigation.LOGIN + ".jsp");
        boolean adminPagesOpened = path.equals(contextPath + "/" + Navigation.REGISTER_HORSE + ".jsp") ||
                 path.equals(contextPath + "/" + Navigation.SET_RESULTS + ".jsp");
        ClientsEntity activeUser = (ClientsEntity)session.getAttribute(AttributeParameterEnum.USER.toString().toLowerCase());
        boolean userLoggedIn = session != null && 
                    activeUser != null;
        try {
            if (!loginPagesOpened && !userLoggedIn)
                res.sendRedirect(contextPath + "/" + Navigation.LOGIN + ".jsp");
            else if (userLoggedIn && (loginPagesOpened || (adminPagesOpened && activeUser.getRoleId() != UserRoles.ADMIN)))
                res.sendRedirect(contextPath + "/" + Navigation.INDEX + ".jsp");
            else
                chain.doFilter(request, response);
        } catch (IOException | ServletException t) {
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
            return ("LoginFilter()");
        }
        StringBuffer sb = new StringBuffer("LoginFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
