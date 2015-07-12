package com.twitter.project.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.twitter.project.config.key.ConfigKey;
import com.twitter.project.config.values.ConfigValue;


public class IniConfig extends Config {
  
  // Holds INI configuration object : Map < group , Map < key , value(s) > >
  private Map<String, Map<ConfigKey, ConfigValue<Object>>> configuration = new HashMap<String, Map<ConfigKey, ConfigValue<Object>>>();
  
  private Pattern keyPattern = Pattern.compile( "(.*?)\\.(.*)" );
  
  @Override
  public Object get(String input) {
    String groupName, keyName;
    Matcher matcher = keyPattern.matcher(input);
    if (matcher.find()) {
      groupName = matcher.group(1);
      keyName = matcher.group(2);
      if (configuration.get(groupName) != null && configuration.get(groupName).get(ConfigKey.get(keyName)) != null) {
        return configuration.get(groupName).get(ConfigKey.get(keyName)).get(environment);
      }
      return null;
    }
    groupName = input;
    return getGroupConfig(groupName);
  }

  public void addGroup(String groupName) {
    if (!configuration.containsKey(groupName)) {
      configuration.put(groupName, new HashMap<ConfigKey, ConfigValue<Object>>());
    }
  }

  public void addKeyValue(String groupName, String override, String key, String value) {
    if (StringUtils.isNotEmpty(groupName) && StringUtils.isNotEmpty(key) &&  StringUtils.isNotEmpty(value)) {
      if (configuration.get(groupName) == null) {
        addGroup(groupName);
      }
      if (configuration.get(groupName).get(ConfigKey.get(key)) == null) {
        ConfigValue<Object> configValue = new ConfigValue<Object>(override, value);
        if (value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("true") || value.equalsIgnoreCase("1")) {
          configValue = new ConfigValue<Object>(override, true);
        }
        else if (value.equalsIgnoreCase("no") || value.equalsIgnoreCase("false") || value.equalsIgnoreCase("0")) {
          configValue = new ConfigValue<Object>(override, false);
        }
        else if (!value.startsWith("\"") && !value.endsWith("\"") && value.contains(",")) {
          String[] values = value.split(",");
          configValue = new ConfigValue<Object>(override, Arrays.asList(values));
        }
        configuration.get(groupName).put(new ConfigKey(key), configValue);
        return;
      }
      configuration.get(groupName).get(ConfigKey.get(key)).add(override, value);
    }
  }
  
  public Map<ConfigKey, Object> getGroupConfig(String groupName) { 
    Map<ConfigKey, Object> groupConfigSettings = new HashMap<ConfigKey, Object>();
    if (configuration.get(groupName) != null) {
      for (Map.Entry<ConfigKey, ConfigValue<Object>> entry : configuration.get(groupName).entrySet()) {
        groupConfigSettings.put(entry.getKey(), entry.getValue().get(environment));
      }
    }
    return groupConfigSettings;
  }
  
}
