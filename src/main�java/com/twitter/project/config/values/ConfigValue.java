package com.twitter.project.config.values;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConfigValue<T> {
    
  private T defaultValue;
  
  private Map<String, T> overrides = new LinkedHashMap<String, T>(); 
  
  public ConfigValue(String override, T value) {
    if (override == null) {
      this.defaultValue = value;
    }
    else {
      this.defaultValue = null;
      add(override, value);
    }
  }
  
  /**
   * Inserts default config value for given key
   * 
   * @param value : default value
   */
  
  public void add(T value) {
    this.defaultValue = value;
  }
  
  /**
   * Inserts config value for given override, while maintaining order of insertion
   * 
   * @param override : override name
   * @param value : override value
   */
  
  public void add(String override, T value) {
    if (overrides.containsKey(override)) {
      overrides.remove(override);
    }
    overrides.put(override, value);
  }
  
  public T get(List<String> environmentValues) {
    T finalValue = defaultValue;
    for (Map.Entry<String, T> entry : overrides.entrySet()) {
      if (environmentValues.contains(entry.getKey())) {
        finalValue = entry.getValue();
      }
    }
    return finalValue;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("{");
    sb.append("\"default\"=" + defaultValue);
    for (Map.Entry<String, T> entry : overrides.entrySet()) {
      sb.append(", \"" + entry.getKey() + "\"=" + entry.getValue());
    }
    sb.append("}");
    return sb.toString();
  }
  
  
}
