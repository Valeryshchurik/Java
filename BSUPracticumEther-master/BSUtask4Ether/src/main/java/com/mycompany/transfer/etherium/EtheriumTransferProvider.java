/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.transfer.etherium;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import rx.Subscription;

/**
 *
 * @author user
 */
public class EtheriumTransferProvider implements TransferProvider {

    private Web3j web3;

    public EtheriumTransferProvider(Web3j web3) {
        this.web3 = web3;
    }

    @Override
    public void transfer(String contractName, Credentials credentials, TransferInfo transfer)
            throws Exception {
        HumanStandardToken contract = HumanStandardToken.load(
                contractName, web3, credentials, BigInteger.valueOf(transfer.getGasPrice()), BigInteger.valueOf(transfer.getGasLimit()));
        contract.transfer(transfer.getDestination(), BigInteger.valueOf(transfer.getToken()));
    }
    @Override
    public BigInteger checkBalance(String contractName, Credentials credentials, TransferInfo transfer, Address owner)
    {
        HumanStandardToken contract = HumanStandardToken.load(
                contractName, web3, credentials, BigInteger.valueOf(transfer.getGasPrice()), BigInteger.valueOf(transfer.getGasLimit()));
                RemoteCall<BigInteger> num = contract.balanceOf(owner.getValue());
            try {
                return contract.balanceOf(owner.getValue()).send();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        return null;
    }
}
