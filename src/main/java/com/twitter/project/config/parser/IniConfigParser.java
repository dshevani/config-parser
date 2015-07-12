package com.twitter.project.config.parser;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.twitter.project.config.Config;
import com.twitter.project.config.IniConfig;

public class IniConfigParser implements ConfigParser {
  
  private static Pattern group  = Pattern.compile( "\\[([^]]*)\\]" );
  private static Pattern mappingWithOverride = Pattern.compile( "(.*?)\\s*<(.*)>\\s*=\\s*(.*)" );
  private static Pattern mapping = Pattern.compile( "\\s*([^=]*)=(.*)" );

  public Config parse(Reader source) {
    IniConfig config = new IniConfig();
    try {
      BufferedReader br = (BufferedReader) source;
      String currentLine, currentGroup = null;
      String currentKey, currentOverride, currentValue;
      while ((currentLine = br.readLine()) != null) {
        currentLine = removeComment(currentLine).trim();
        if (currentLine.length() > 0) {
          Matcher m = group.matcher(currentLine);
          if (m.matches()) {
            currentGroup = m.group(1).trim();
            config.addGroup(currentGroup);
            continue;
          }
          m = mappingWithOverride.matcher(currentLine);
          if (m.matches()) {
            currentKey = m.group(1).trim();
            currentOverride = m.group(2).trim();
            currentValue = m.group(3).trim();
            config.addKeyValue(currentGroup, currentOverride, currentKey, currentValue);
            continue;
          }
          m = mapping.matcher(currentLine);
          if (m.matches()) {
            currentKey = m.group(1).trim();
            currentValue = m.group(2).trim();
            config.addKeyValue(currentGroup, null, currentKey, currentValue);
          }
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return config;
  }
  
  public static String removeComment(String line) {
    int index = line.indexOf(";");
    if (index > 0) {
      line = line.substring(0, index);
    }
    return line;
  }

}
