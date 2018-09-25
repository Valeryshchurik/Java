/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shchuryk.lab10.servlet.businessLogic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Abstract class for Command pattern.
 */
public interface Command {
    /**
     * Main execution method of Command pattern.
     * @return 
     */
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
