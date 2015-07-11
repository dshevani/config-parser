package com.twitter.project.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import com.twitter.project.config.parser.ConfigParser;
import com.twitter.project.config.source.ConfigSource;

/**
 * @author deepak.shevani on Jul 11, 2015
 *
 */

public abstract class Config {
    
  protected static List<String> environment;
    
  public static Config load(ConfigSource source, ConfigParser parser, String[] environmentVariables) {
    environment = Arrays.asList(environmentVariables);
    try {
      Reader br = new BufferedReader(new FileReader(source.getFilePath()));
      return parser.parse(br);
    } 
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
      
  public abstract Object get(String key);
  
}
