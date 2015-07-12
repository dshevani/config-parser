package com.twitter.project.config.source;

public class ConfigSource {
  
  private String path;
  
  public ConfigSource(String path) {
    this.path = path;  
  }
  
  public String getFilePath() {
    return path;
  }
  
}
