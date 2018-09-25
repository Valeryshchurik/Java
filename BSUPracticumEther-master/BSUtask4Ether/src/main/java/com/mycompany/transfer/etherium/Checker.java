package com.mycompany.transfer.etherium;

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.Log;

import java.io.*;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.transfer.etherium.App.*;

public class Checker extends Thread{
    public BigInteger tokens;
    public EthFilter filter;
    @Override
    public void run() {
        filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, "0x97cE6CE1ddb481a9B0fb8e2cE7316Cfd9FF6f534");
        HumanStandardToken contract = new HumanStandardToken(CONTRACT_NAME, web3, credentials, BigInteger.valueOf(GAS_PRICE), BigInteger.valueOf(GAS_LIMIT));
        List<HumanStandardToken.TransferEventResponse> list = contract.getTransferEvents(again().getResult());
        List<HumanStandardToken.TransferEventResponse> loggedList;
        loggedList = list;
        while (true) {
            list = contract.getTransferEvents(again().getResult());
            for (HumanStandardToken.TransferEventResponse event : list) {
                System.out.println(MessageFormat.format("{0}: {1} -> {2} {3} BYX ({4})", event.log.getBlockNumber(), event.from, event.to, event.value, event.log.getTransactionHash()));
                if (event.log.getTransactionHash().equals(loggedList.get(loggedList.size() - 1).log.getTransactionHash()))
                    continue;
                loggedList=list;
                if (event.from.equals(ADDRESS) || event.to.equals(ADDRESS)) {
                    if (event.value.intValue() > 0) {
                        BigInteger newTokens = transferProvider.checkBalance(CONTRACT_NAME, credentials, transferInfo, address);
                        mail(newTokens);
                    }
                }
            }
        }
    }
    public void check() throws IOException {
        BigInteger newTokens=transferProvider.checkBalance(CONTRACT_NAME, credentials, transferInfo, address);
        if (!tokens.equals(newTokens))
        {
            tokens=newTokens;
            mail(newTokens);
        }
    }
    public EthGetTransactionReceipt again(){
        EthLog ethLog = null;
        try {
            ethLog = web3.ethGetLogs(filter).send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<EthLog.LogResult> logResultList = ethLog.getLogs();
        EthLog.LogResult logResult = logResultList.get(logResultList.size() - 1);
        Log log= (Log) logResult.get();
        EthGetTransactionReceipt transactionReceipt = null;
        try {
            transactionReceipt = web3.ethGetTransactionReceipt(log.getTransactionHash()).send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionReceipt;
    }
}
