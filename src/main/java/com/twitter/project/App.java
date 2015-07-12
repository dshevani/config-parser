package com.twitter.project;

import com.twitter.project.config.Config;
import com.twitter.project.config.parser.IniConfigParser;
import com.twitter.project.config.source.ConfigSource;

/**
 * 
 * └── com
 *     └── twitter
 *        └── project
 *            ├── App.java  (Main Driver Class)
 *            └── config
 *                ├── Config.java (Abstract Class representing Config Object)
 *                ├── IniConfig.java (IniConfig instantiation)
 *                ├── exception
 *                │   └── InvalidFileFormatException.java (Exception Class - Not used right now)
 *                ├── key
 *                │   └── ConfigKey.java (Class representing key)
 *                ├── parser
 *                │   ├── ConfigParser.java (Parser Interface)
 *                │   └── IniConfigParser.java (IniConfig Parser implementation)
 *                ├── source
 *                │   └── ConfigSource.java (Class representing source read from disk)
 *                └── values
 *                    └── ConfigValue.java (Class representing ordered value(s) associated with key)
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
      
      // Get specific key in group
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
