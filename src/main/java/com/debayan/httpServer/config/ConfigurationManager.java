package com.debayan.httpServer.config;

import com.debayan.httpServer.util.Json;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConfigurationManager {
   private static ConfigurationManager myconfigurationManager;
   private Configuration currentconfiguration;

    private ConfigurationManager() {}

    public static ConfigurationManager getInstance() {
        if (myconfigurationManager == null) {
            myconfigurationManager = new ConfigurationManager();
        }
        return myconfigurationManager;
    }

    public void loadConfiguration(String filepath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i ;
        try {
            while ( ( i = fileReader.read()) != -1) {
                sb.append((char)i);
            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
        String content = sb.toString();
        try {
            currentconfiguration = Json.fromJson(Json.parse(content), Configuration.class);
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
    }
    public Configuration getCurrentConfiguration() {
        if ( currentconfiguration == null) {
            throw new HttpConfigurationException("No Current Configuration Set.");
        }
        return currentconfiguration;
    }

}
