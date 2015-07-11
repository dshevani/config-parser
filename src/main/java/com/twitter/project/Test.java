package com.twitter.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author deepak.shevani on Jul 11, 2015
 *
 */

public class Test {
  
  private static Pattern group  = Pattern.compile( "\\[([^]]*)\\]" );
  //private static Pattern mapping = Pattern.compile( "\\s*([^<=>]*)=(.*)" );
  private static Pattern mapping = Pattern.compile( "(.*?)\\s*<(.*)>\\s*=\\s*(.*)" );

  public static void main(String[] args) {
    String line = "a<b> = c   ";
    line = removeComment(line);
    Matcher m = mapping.matcher(line.trim());
    if(m.matches()) {
      System.out.println(m.group(0));
      System.out.println(m.group(1).trim());
      System.out.println(m.group(2).trim());
      System.out.println(m.group(3).trim());
    }
  }
  
  public static String removeComment(String line) {
    int index = line.indexOf(";");
    if (index > 0) {
      line = line.substring(0, index);
    }
    return line;
  }
  
}
