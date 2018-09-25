/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shchuryk.lab10.servlet;


import com.struchkov.lab10.dao.*;
import com.struchkov.lab10.servlet.businessLogic.*;
import com.struchkov.lab10.util.AttributeParameterEnum;
import com.struchkov.lab10.util.Navigation;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NotePad.by
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @EJB
    protected DaoBetRemote daoBet;
    
    @EJB
    protected DaoRunRemote daoRun;
    
    @EJB
    protected DaoClientRemote daoClient;
    
    @EJB
    protected DaoHorseRemote daoHorse;
    
    protected BusinessLogicManager manager;
    
    protected Map<String, Command> commands;
    
    protected void initializeBusinessLogicManager()
    {
        manager = new BusinessLogicManager(daoBet, daoRun, daoHorse, daoClient);
        commands = new HashMap<String, Command>();
        commands.put(Navigation.HORSES_BY_RACE, new HorsesByRaceCommand(manager));
        commands.put(Navigation.RACES_BY_DATE, new RacesByDateCommand(manager));
        commands.put(Navigation.WINNER_CLIENTS, new WinnerClientsCommand(manager));
        commands.put(Navigation.REGISTER_HORSE, new RegisterHorseCommand(manager));
        commands.put(Navigation.SET_RESULTS, new SetResultCommand(manager));
        commands.put(Navigation.REGISTER, new RegisterCommand(manager));
        commands.put(Navigation.LOGIN, new LoginCommand(manager));
        commands.put(Navigation.LOGOUT, new LogoutCommand());
    }

    @Override
    public void init() throws ServletException {
        super.init(); 
        initializeBusinessLogicManager();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //throw new RuntimeException();
        sendSessionCookies(request, response);
        String pageName = request.getParameter(AttributeParameterEnum.PAGE_NAME.toString().toLowerCase());
        commands.get(pageName).execute(request, response);
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
        Cookie cookie = new Cookie(key, value);
        response.addCookie(cookie);
    }
    
    protected String getCookieValue(HttpServletRequest request, String key, String defaultValue) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && key != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return defaultValue;
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
