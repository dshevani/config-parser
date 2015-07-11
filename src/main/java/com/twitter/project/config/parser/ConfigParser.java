package com.twitter.project.config.parser;

import java.io.Reader;

import com.twitter.project.config.Config;

/**
 * @author deepak.shevani on Jul 11, 2015
 *
 */

public interface ConfigParser {

  // Parse the configuration in given source and return Config object
  Config parse(Reader source);
  
}
