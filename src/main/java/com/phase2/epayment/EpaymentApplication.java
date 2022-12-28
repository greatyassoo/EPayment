package com.phase2.epayment;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.phase2.epayment.AccountsDB.Account;
import com.phase2.epayment.Controllers.UserController;

@SpringBootApplication
public class EpaymentApplication {
	public static void main(String[] args) throws Exception{
		SpringApplication.run(EpaymentApplication.class, args);

		
	}
	
}