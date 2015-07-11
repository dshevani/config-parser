package com.twitter.project.config.source;


/**
 * 
 * Just a wrapper right now around path.
 * Class can be used for optimization while initial loading
 * 
 * @author deepak.shevani on Jul 11, 2015
 *
 */

public class ConfigSource {
  
  private String path;
  
  public ConfigSource(String path) {
    this.path = path;  
  }
  
  public String getFilePath() {
    return path;
  }
  
}
