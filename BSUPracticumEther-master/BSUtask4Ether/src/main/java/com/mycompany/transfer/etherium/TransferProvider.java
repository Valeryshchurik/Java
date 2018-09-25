/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.transfer.etherium;

import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;

import java.math.BigInteger;

/**
 *
 * @author user
 */
public interface TransferProvider {
    
    public void transfer(String contractName, Credentials credentials, TransferInfo transfer) throws Exception;
    public BigInteger checkBalance(String contractName, Credentials credentials, TransferInfo transfer, Address owner);

}
