package com.mycompany.transfer.etherium;

import java.io.BufferedReader;
import java.io.FileReader;

import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import rx.Subscription;

import java.io.IOException;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Hello world!
 *
 */
public class App {

    public static final String CONTRACT_NAME = "0x97cE6CE1ddb481a9B0fb8e2cE7316Cfd9FF6f534";
    public static final String ADDRESS="0x8cb1ef44c11bfe313318b104292a96ae39285da6";
    public static final String WALLET_PASSWORD = "80292713802";
    //    private static final String WALLET_FILE =
//            "C:\\Users\\Darth\\AppData\\Roaming\\Ethereum\\keystore\\UTC--2018-07-12T14-27-30.667364500Z--62a59f6a6fb6c9fa17642d973d42be26d1e00f75";
    public static final String WALLET_FILE = "c:\\Users\\msi\\AppData\\Roaming\\Ethereum\\testnet\\keystore\\UTC--2018-07-12T08-33-15.526000000Z--8cb1ef44c11bfe313318b104292a96ae39285da6.json";

    public static final Long GAS_PRICE = 20_000_000_000L;
    public static final Long GAS_LIMIT = 4_300_000L;
    public static final String INPUT = "c:\\Users\\msi\\AppData\\Roaming\\Ethereum\\testnet\\keystore\\in.txt";
    public static Web3j web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/https://rinkeby.infura.io/zpigYMgXvD8dCL3gpITZ"));  // defaults to http://localhost:8545/
    public static TransferProvider transferProvider = new EtheriumTransferProvider(web3);

    public static Credentials credentials;

    {
        try {
            credentials = WalletUtils.loadCredentials(WALLET_PASSWORD, WALLET_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
    }
    public static TransferInfo transferInfo = new TransferInfo();
    {
        transferInfo.setGasPrice(GAS_PRICE);
        transferInfo.setGasLimit(GAS_LIMIT);
    }
    public static Address address = new Address("0x8cb1ef44c11bfe313318b104292a96ae39285da6");
    public static void main(String[] args) {
        new App().run();
    }
    public void run() {
            Checker checker=new Checker();
            checker.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String to = "0xb5fd8a970206287c5c8f0ff0c500b2e2ef1e7be7";
            String amount = "1";
            transferInfo.setDestination(to);
            transferInfo.setToken(Long.valueOf(amount));
        try {
            transferProvider.transfer(CONTRACT_NAME, credentials, transferInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log(String.format("SUCCESS: %s tokens has been transfered to %s",
                    amount, to), false);
        }

    public static void log(String message, boolean isError) {
        if (isError) {
            System.err.println(message);
        } else {
            System.out.println(message);
        }
    }

    public static void mail(BigInteger tokens) {

        final String username = "arkadiyhonored@gmail.com";
        final String password = "80292713802Mama";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("arkadiyhonored@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("Valeryschurik@gmail.com"));
            message.setSubject("TOKENZZZZZZZZZZ");
            message.setText("AAAAAAAAAAAAAAAAh! Only "+ tokens+ " remained");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}