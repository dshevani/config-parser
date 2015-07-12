package com.twitter.project.config.key;

public class ConfigKey {
  
  String name;
  
  public ConfigKey(String key) {
    this.name = key;
  }
  
  public static ConfigKey get(String key) {
    return new ConfigKey(key);
  }
  
  @Override
  public int hashCode() {
    return name.hashCode();
  }
  
  @Override
  public boolean equals(Object obj) {
      if (this == obj) {
          return true;
      }
      if (obj == null) {
          return false;
      }
      if (getClass() != obj.getClass()) {
          return false;
      }
      ConfigKey other = (ConfigKey) obj;
      if (!name.equalsIgnoreCase(other.name)) {
          return false;
      }
      return true;
  }

  @Override
  public String toString() {
    return name;
  }
  
}
