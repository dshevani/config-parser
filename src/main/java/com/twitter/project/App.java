package com.twitter.project;

import com.twitter.project.config.Config;
import com.twitter.project.config.parser.IniConfigParser;
import com.twitter.project.config.source.ConfigSource;

/**
 * 
 * Lets assume a driver class Config that has load function which takes a file location and a parser.
 * It returns a Config object that has other functions to fetch confg values
 * There will be a class that will read the file from some path and store a BufferedInputStream 
 * Lets call it "ConfigSource"
 * Config.load will create ConfigSource first and then then call a function 
 * Config.parse(ConfigSource, SpecificParser) => SpecificConfig
 * 
 * 
 * @author deepak.shevani on Jul 11, 2015
 *
 */

public class App {
  
    public static void main( String[] args ) {
      
      // Load Config File
      Config firstConfig = Config.load (
              new ConfigSource("sample.ini"), 
              new IniConfigParser(),
              new String[] { "ubuntu", "production" }
      );
      
      // Get specific key
      System.out.println("common.paid_users_size_limit" + " = " + firstConfig.get("common.paid_users_size_limit"));
      System.out.println("ftp.name" + " = " + firstConfig.get("ftp.name"));
      System.out.println("http.params" + " = " + firstConfig.get("http.params"));
      System.out.println("ftp.lastname" + " = " + firstConfig.get("ftp.lastname"));
      System.out.println("ftp.enabled" + " = " + firstConfig.get("ftp.enabled"));
      System.out.println("ftp.path" + " = " + firstConfig.get("ftp.path"));
      
      // Get all keys in a group
      System.out.println("ftp" + " = " + firstConfig.get("ftp"));
      
    }
}
