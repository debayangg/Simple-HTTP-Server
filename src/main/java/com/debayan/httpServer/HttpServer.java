package com.debayan.httpServer;


import com.debayan.httpServer.config.ConfigurationManager;
import com.debayan.httpServer.config.Configuration;
import com.debayan.httpServer.core.ServerSocketThread;

import java.io.IOException;


public class HttpServer {
    public static void main(String[] args){
        System.out.println("Hello World!");
        ConfigurationManager cm = ConfigurationManager.getInstance();
        cm.loadConfiguration("src/main/resources/http.json");

        Configuration currentConfiguration = cm.getCurrentConfiguration();
        System.out.println(currentConfiguration.getPort());
        System.out.println(currentConfiguration.getWebroot());


        try {
            new Thread(new ServerSocketThread(currentConfiguration.getPort())).start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
