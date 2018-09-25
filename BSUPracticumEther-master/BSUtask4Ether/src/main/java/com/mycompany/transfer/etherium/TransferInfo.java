/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.transfer.etherium;

/**
 *
 * @author user
 */
public class TransferInfo {
    
    private String destination;
    private Long token;
    private Long gasPrice;
    private Long gasLimit;
    
    public String getDestination() {
        return destination;
    }

    public Long getToken() {
        return token;
    }

    public Long getGasPrice() {
        return gasPrice;
    }

    public Long getGasLimit() {
        return gasLimit;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setToken(Long token) {
        this.token = token;
    }

    public void setGasPrice(Long gasPrice) {
        this.gasPrice = gasPrice;
    }

    public void setGasLimit(Long gasLimit) {
        this.gasLimit = gasLimit;
    }
    
}
