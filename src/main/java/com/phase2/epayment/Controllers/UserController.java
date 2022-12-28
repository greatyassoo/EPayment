package com.phase2.epayment.Controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.sound.sampled.Line;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.phase2.epayment.AccountsDB.*;
import com.phase2.epayment.ServicesDB.*;

@RestController
public class UserController extends ServicesController {

    UserController(ServicesDB servicesDB) {
        this.servicesDB = servicesDB;
        Account test = null;
        try {
            getAccount("test", "test");
            //System.out.println(test.getEmail() + " " + test.getUserName());
        } catch (Exception e) {
            System.out.println("exception");
        }
        ;
        // this.discountController = discountController;
    }

    
    public Account getAccount(String userName,String password) throws Exception{
        Account account = null;
        URL url = new URL(String.format("http://localhost:8080/accountID/?userName=%s&password=%s", userName, password));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        System.out.println(content);
        String result = content.toString();
        Gson gson = new Gson();
        account = gson.fromJson(result, Account.class);
        System.out.println(account.getAllInfo());
        return account;
    }
    
    // TODO parse json into account object
    public Account tgetAccount(String userName, String password)
            throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/accountID/?userName=test&password=test")).build();
        String out = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenApply(UserController::parse)
        .join();
        System.out.println("==========================================");
        System.out.println(out);
            return null;
    }

    public static String parse(String response){
        System.out.println(response);
        return response;
    }

    // public boolean fundAccount(String userName, String password, String CCN,
    // String PIN, double amount) {
    // //Account account = getAccount(userName, password);
    // try {
    // if (!CCN.matches("-?\\d+(\\.\\d+)?") || CCN.length() != 16 || PIN.length() !=
    // 4
    // || !PIN.matches("-?\\d+(\\.\\d+)?"))
    // return false;
    // account.setWalletBalance(account.getWalletBalance() + amount);
    // account.addTransaction(
    // new Transaction(Transaction.TYPE.TOP_UP, "Recharge", "CreditCard",
    // "CreditCard", "", amount, 0));
    // return true;
    // } catch (Exception e) {
    // return false;
    // }
    // }

    // public Service getService(String serviceName) {
    // Service temp = null;
    // for (int i = 0; i < servicesDB.size(); i++) {
    // if (servicesDB.get(i).getName() == serviceName)
    // temp = servicesDB.get(i);
    // }
    // return temp;
    // }

    // @GetMapping(value = "/transactions")
    // public LinkedList<String> getTransactions(@RequestParam("userName") String
    // userName,
    // @RequestParam("password") String password) {
    // Account account = getAccount(userName, password);
    // LinkedList<Transaction> transactions = account.getTransactions();
    // LinkedList<String> transactionsList = new LinkedList<String>();
    // for (int i = 0; i < transactions.size(); i++)
    // transactionsList.addLast(transactions.get(i).getAllInfo());
    // return transactionsList;
    // }

    // @GetMapping(value = "/refundRequests")
    // public LinkedList<String> getRefundRequests(@RequestParam("userName") String
    // userName,
    // @RequestParam("password") String password) {
    // //Account account = getAccount(userName, password);
    // LinkedList<Integer> refundRequestsIndx = account.getRefundRequests();
    // LinkedList<String> refundRequests = new LinkedList<String>();
    // Transaction transaction;
    // for (int i = 0; i < refundRequestsIndx.size(); i++) {
    // transaction = account.getTransaction(refundRequestsIndx.get(i));
    // String transactionString = transaction.getAllInfo();
    // refundRequests.addLast(transactionString);
    // }
    // return refundRequests;
    // }

    // @GetMapping(value = "/refund")
    // public boolean addRefundRequest(@RequestParam("userName") String userName,
    // @RequestParam("password") String password, @RequestParam("transactionIndx")
    // int transactionIndx) {
    // //Account account = getAccount(userName, password);
    // if (account.getRefundRequests().contains(transactionIndx)) {
    // return false;
    // }
    // account.addRefundRequest(transactionIndx);
    // return true;
    // }
}
